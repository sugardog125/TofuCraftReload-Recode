package baguchi.tofucraft.blockentity.tfenergy;

import baguchi.tofucraft.api.tfenergy.IEnergyExtractable;
import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchi.tofucraft.block.tfenergy.TFStorageBlock;
import baguchi.tofucraft.blockentity.tfenergy.base.SenderBaseBlockEntity;
import baguchi.tofucraft.inventory.TFStorageMenu;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import java.util.Map;

public class TFStorageBlockEntity extends SenderBaseBlockEntity implements StackedContentsCompatible, Container, MenuProvider {

	private static final int POWER = 20;
	private FluidTank tank = new TFStorageTank(2000);
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	private int workload = 0;
	private int current_workload = 0;
	private int prevFluid;

	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return TFStorageBlockEntity.this.workload;
				case 1:
					return TFStorageBlockEntity.this.current_workload;
				case 2:
					return TFStorageBlockEntity.this.energy;
				case 3:
					return TFStorageBlockEntity.this.energyMax;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					TFStorageBlockEntity.this.workload = p_221477_2_;
					break;
				case 1:
					TFStorageBlockEntity.this.current_workload = p_221477_2_;
					break;
				case 2:
					TFStorageBlockEntity.this.energy = p_221477_2_;
					break;
				case 3:
					TFStorageBlockEntity.this.energyMax = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public TFStorageBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(TofuBlockEntitys.TF_STORAGE.get(), p_155229_, p_155230_, 5000);
	}

	@Override
	public boolean canReceive(BlockEntity from) {
		return from instanceof TFCollectorBlockEntity || super.canReceive(from);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState blockState, TFStorageBlockEntity tfStorageBlockEntity) {
		if (level.isClientSide()) return;
		SenderBaseBlockEntity.senderUpdate(tfStorageBlockEntity);

		boolean worked = false;

		//Transform workload to power
		if (tfStorageBlockEntity.workload > 0 && tfStorageBlockEntity.getEnergyStored() < tfStorageBlockEntity.getMaxEnergyStored()) {
			tfStorageBlockEntity.workload -= tfStorageBlockEntity.receive(Math.min(tfStorageBlockEntity.workload, POWER), false);
			worked = true;
		}
		if (blockState.getValue(TFStorageBlock.LIT) != worked) {
			level.setBlock(blockPos, blockState.setValue(TFStorageBlock.LIT, worked), 2);
		}

		ItemStack to = tfStorageBlockEntity.inventory.get(1);


		if (to.getItem() instanceof IEnergyInsertable symbol) {
			if (tfStorageBlockEntity.getEnergyStored() >= POWER * 5) {
				tfStorageBlockEntity.drain(symbol.fill(to, POWER, false), false);
				tfStorageBlockEntity.setChanged();
			}
		}

		ItemStack from = tfStorageBlockEntity.inventory.get(0);

		//Consume beans inside machine
		if (tfStorageBlockEntity.workload == 0) {
			FluidStack milk = tfStorageBlockEntity.getTank().getFluid();
			if (from.getItem() instanceof IEnergyExtractable symbol && !(from.getItem() instanceof IEnergyInsertable)) {
				tfStorageBlockEntity.workload += symbol.drain(from, POWER * 20, false);
				tfStorageBlockEntity.setChanged();
			} else if (TofuEnergyMap.getFuel(from) != -1) {
				tfStorageBlockEntity.workload += TofuEnergyMap.getFuel(from);
				from.shrink(1);
			}

			if (!milk.isEmpty()) {
				Map.Entry<FluidStack, Integer> recipe = TofuEnergyMap.getLiquidFuel(milk);
				if (recipe != null) {
					tfStorageBlockEntity.tank.drain(recipe.getValue(), IFluidHandler.FluidAction.EXECUTE);
					tfStorageBlockEntity.workload += recipe.getValue();
					tfStorageBlockEntity.setChanged();
				}
			}
			tfStorageBlockEntity.current_workload = tfStorageBlockEntity.workload;
		}

		/*if (tfStorageBlockEntity.prevFluid != tfStorageBlockEntity.tank.getFluidAmount()) {
			LevelChunk chunk = level.getChunkAt(blockPos);
			if (level instanceof ServerLevel serverLevel) {
				PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new TFStorageSoymilkPacket(blockPos, tfStorageBlockEntity.tank.getFluid()));
				tfStorageBlockEntity.prevFluid = tfStorageBlockEntity.tank.getFluidAmount();
			}
		}*/
	}

	@Override
	public void startOpen(Player p_18955_) {
		/*if (!this.level.isClientSide() && this.level instanceof ServerLevel serverLevel) {
			LevelChunk chunk = this.level.getChunkAt(this.getBlockPos());
			PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new TFStorageSoymilkPacket(this.getBlockPos(), this.tank.getFluid()));
			this.prevFluid = this.tank.getFluidAmount();
		}*/
	}

	public FluidTank getTank() {
		return this.tank;
	}

	@Override
	public int getContainerSize() {
		return 2;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		return inventory.get(index);
	}

	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ContainerHelper.removeItem(this.inventory, p_70298_1_, p_70298_2_);
	}

	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ContainerHelper.takeItem(this.inventory, p_70304_1_);
	}


	@Override
	public void setItem(int index, ItemStack stack) {
		inventory.set(index, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return p_18946_.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressScaled(int par1) {
		return this.current_workload == 0 ? 0 : ((this.current_workload - this.workload) * par1 / this.current_workload);
	}

	public NonNullList<ItemStack> getInventory() {
		return inventory;
	}

	@Override
	public void saveAdditional(CompoundTag cmp, HolderLookup.Provider provider) {
		super.saveAdditional(cmp, provider);
		ContainerHelper.saveAllItems(cmp, this.inventory, provider);
		cmp.putInt("workload", this.workload);
		cmp.putInt("current", this.current_workload);

		CompoundTag tankTag = this.tank.writeToNBT(this.level.registryAccess(), new CompoundTag());

		cmp.put("Tank", tankTag);
	}

	@Override
	protected void loadAdditional(CompoundTag cmp, HolderLookup.Provider provider) {
		super.loadAdditional(cmp, provider);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(cmp, this.inventory, provider);

		this.workload = cmp.getInt("workload");
		this.current_workload = cmp.getInt("current");

		this.tank = this.tank.readFromNBT(provider, cmp.getCompound("Tank"));
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider p_324313_) {
		return this.saveCustomOnly(p_324313_);
	}

	@Override
	protected void applyImplicitComponents(BlockEntity.DataComponentInput p_338855_) {
		super.applyImplicitComponents(p_338855_);
		p_338855_.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getInventory());
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder p_338252_) {
		super.collectImplicitComponents(p_338252_);
		p_338252_.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getInventory()));
	}

	@Override
	public void removeComponentsFromTag(CompoundTag p_331127_) {
		super.removeComponentsFromTag(p_331127_);
		p_331127_.remove("Items");
		p_331127_.remove("current");
		p_331127_.remove("workload");
		p_331127_.remove("Tank");
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}


	@Override
	public Component getDisplayName() {
		return Component.translatable("container.tofucraft.tf_storage");
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		return new TFStorageMenu(p_39954_, p_39955_, this, this.dataAccess);
	}

	@Override
	public void fillStackedContents(StackedItemContents p_364629_) {
		for (ItemStack itemstack : this.inventory) {
			p_364629_.accountSimpleStack(itemstack);
		}
	}


	private class TFStorageTank extends FluidTank {
		TFStorageTank(int capacity) {
			super(capacity);
		}

		@Override
		protected void onContentsChanged() {
			setChanged();
		}

		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == TofuFluids.SOYMILK.get());
		}

	}

}
