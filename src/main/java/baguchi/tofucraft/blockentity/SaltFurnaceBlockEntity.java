package baguchi.tofucraft.blockentity;

import baguchi.tofucraft.block.utils.SaltFurnaceBlock;
import baguchi.tofucraft.inventory.SaltFurnaceMenu;
import baguchi.tofucraft.network.SaltFurnaceBitternPacket;
import baguchi.tofucraft.network.SaltFurnaceWaterPacket;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class SaltFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible {

	private static final int[] SLOTS_FOR_SIDES = new int[]{0};
	private static final int[] SLOTS_FOR_UP = new int[]{2, 4};
	private static final int[] SLOTS_FOR_DOWN = new int[]{1, 3, 4};

	protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

	public FluidTank waterTank = new FluidTank(3000) {

		@Override
		protected void onContentsChanged() {
			setChanged();
		}
		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == Fluids.WATER);
		}
	};

	public FluidTank bitternTank = new FluidTank(2000) {

		@Override
		protected void onContentsChanged() {
			setChanged();
		}
		public boolean isFluidValid(FluidStack stack) {
			return (stack.getFluid() == TofuFluids.BITTERN.get());
		}
	};

	private int litTime;

	private int litDuration;

	private int cookingProgress;

	private int cookingTotalTime;
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
				case 0:
					return SaltFurnaceBlockEntity.this.litTime;
				case 1:
					return SaltFurnaceBlockEntity.this.litDuration;
				case 2:
					return SaltFurnaceBlockEntity.this.cookingProgress;
				case 3:
					return SaltFurnaceBlockEntity.this.cookingTotalTime;
			}
			return 0;
		}

		@Override
		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
				case 0:
					SaltFurnaceBlockEntity.this.litTime = p_221477_2_;
					break;
				case 1:
					SaltFurnaceBlockEntity.this.litDuration = p_221477_2_;
					break;
				case 2:
					SaltFurnaceBlockEntity.this.cookingProgress = p_221477_2_;
					break;
				case 3:
					SaltFurnaceBlockEntity.this.cookingTotalTime = p_221477_2_;
					break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};
	private int prevFluid;
	private int prevBitternFluid;

	public SaltFurnaceBlockEntity(BlockPos p_155545_, BlockState p_155546_) {
		super(TofuBlockEntitys.SALT_FURNACE.get(), p_155545_, p_155546_);
	}

	private boolean isLit() {
		return (this.litTime > 0);
	}

	@Override
	public void loadAdditional(CompoundTag cmp, HolderLookup.Provider provider) {
		super.loadAdditional(cmp, provider);
		if (cmp.contains("WaterTank", 10)) {
			CompoundTag nbt = cmp.getCompound("WaterTank");
			this.waterTank = this.waterTank.readFromNBT(provider, nbt);
		}
		if (cmp.contains("BitternTank", 10)) {
			CompoundTag nbt = cmp.getCompound("BitternTank");
			this.bitternTank = this.bitternTank.readFromNBT(provider, nbt);
		}
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(cmp, this.items, provider);
		this.litTime = cmp.getInt("BurnTime");
		this.cookingProgress = cmp.getInt("CookTime");
		this.cookingTotalTime = cmp.getInt("CookTimeTotal");

		this.litDuration = cmp.getInt("lit_total_time");
	}

	@Override
	public void saveAdditional(CompoundTag p_189515_1_, HolderLookup.Provider provider) {
		super.saveAdditional(p_189515_1_, provider);
		CompoundTag nbt = new CompoundTag();
		CompoundTag nbt2 = new CompoundTag();
		this.waterTank.writeToNBT(provider, nbt);
		this.bitternTank.writeToNBT(provider, nbt2);
		p_189515_1_.put("WaterTank", nbt);
		p_189515_1_.put("BitternTank", nbt2);
		p_189515_1_.putInt("BurnTime", this.litTime);
		p_189515_1_.putInt("CookTime", this.cookingProgress);
		p_189515_1_.putInt("CookTimeTotal", this.cookingTotalTime);
		p_189515_1_.putInt("lit_total_time", this.litDuration);
		ContainerHelper.saveAllItems(p_189515_1_, this.items, provider);
	}

	@Nullable
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}


	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider p_324313_) {
		return this.saveCustomOnly(p_324313_);
	}

	public static void tick(Level level, BlockPos blockPos, BlockState p_155016_, SaltFurnaceBlockEntity saltFurnaceBlock) {

		boolean flag = saltFurnaceBlock.isLit();
		boolean flag1 = false;
		if (saltFurnaceBlock.isLit())
			saltFurnaceBlock.litTime--;
		ItemStack itemstack = saltFurnaceBlock.items.getFirst();
		if (saltFurnaceBlock.isLit() || !saltFurnaceBlock.items.getFirst().isEmpty()) {
			if (!saltFurnaceBlock.isLit() && saltFurnaceBlock.hasWater()) {
				saltFurnaceBlock.litTime = saltFurnaceBlock.getBurnDuration(level, itemstack);
				saltFurnaceBlock.litDuration = saltFurnaceBlock.litTime;
				if (saltFurnaceBlock.isLit()) {
					flag1 = true;
					if (itemstack.get(DataComponents.CONTAINER) != null) {
						saltFurnaceBlock.items.set(0, itemstack.get(DataComponents.CONTAINER).copyOne());
					} else if (!itemstack.isEmpty()) {
						Item item = itemstack.getItem();
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							saltFurnaceBlock.items.set(0, ItemStack.EMPTY);
						}
					}
				}
			}
			if (saltFurnaceBlock.isLit() && saltFurnaceBlock.hasWater()) {
				saltFurnaceBlock.cookingProgress++;
				if (saltFurnaceBlock.cookingProgress == saltFurnaceBlock.cookingTotalTime) {
					saltFurnaceBlock.cookingProgress = 0;
					saltFurnaceBlock.cookingTotalTime = saltFurnaceBlock.getTotalCookTime();
					saltFurnaceBlock.makeSalt();
					flag1 = true;
				}
			} else {
				saltFurnaceBlock.cookingProgress = 0;
			}
		} else if (!saltFurnaceBlock.isLit() && saltFurnaceBlock.cookingProgress > 0) {
			saltFurnaceBlock.cookingProgress = Mth.clamp(saltFurnaceBlock.cookingProgress - 2, 0, saltFurnaceBlock.cookingTotalTime);
		}
		if (flag != saltFurnaceBlock.isLit()) {
			flag1 = true;
			saltFurnaceBlock.level.setBlock(blockPos, saltFurnaceBlock.level.getBlockState(blockPos).setValue(SaltFurnaceBlock.LIT, Boolean.valueOf(saltFurnaceBlock.isLit())), 3);
		}
		saltFurnaceBlock.makeBittern();
		saltFurnaceBlock.putWater();


		if (!level.isClientSide) {
			if (saltFurnaceBlock.prevFluid != saltFurnaceBlock.waterTank.getFluidAmount()) {
				LevelChunk chunk = level.getChunkAt(blockPos);
				if (level instanceof ServerLevel serverLevel) {
					PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new SaltFurnaceWaterPacket(blockPos, saltFurnaceBlock.waterTank.getFluid()));
					saltFurnaceBlock.prevFluid = saltFurnaceBlock.waterTank.getFluidAmount();
				}
			}

			if (saltFurnaceBlock.prevBitternFluid != saltFurnaceBlock.bitternTank.getFluidAmount()) {
				LevelChunk chunk = level.getChunkAt(blockPos);
				if (level instanceof ServerLevel serverLevel) {
					PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new SaltFurnaceBitternPacket(blockPos, saltFurnaceBlock.bitternTank.getFluid()));
					saltFurnaceBlock.prevBitternFluid = saltFurnaceBlock.bitternTank.getFluidAmount();
				}
			}
		}
		if (flag1) {
			saltFurnaceBlock.setChanged();
		}
	}

	@Override
	public void startOpen(Player p_18955_) {
		super.startOpen(p_18955_);
		if (!this.level.isClientSide() && this.level instanceof ServerLevel serverLevel) {
			LevelChunk chunk = this.level.getChunkAt(this.getBlockPos());
			PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new SaltFurnaceBitternPacket(this.getBlockPos(), this.bitternTank.getFluid()));
			this.prevBitternFluid = this.bitternTank.getFluidAmount();
			PacketDistributor.sendToPlayersTrackingChunk(serverLevel, chunk.getPos(), new SaltFurnaceWaterPacket(this.getBlockPos(), this.waterTank.getFluid()));
			this.prevFluid = this.waterTank.getFluidAmount();
		}
	}

	protected boolean hasWater() {
		boolean flag = (this.waterTank.getFluid().getFluid() == Fluids.WATER && this.waterTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = this.items.get(1);
		if (itemstack1.isEmpty())
			return flag;
		if (itemstack1.getCount() + 2 <= getMaxStackSize() && itemstack1.getCount() + 2 <= itemstack1.getMaxStackSize())
			return flag;
		return false;
	}

	protected boolean hasBittern() {
		boolean flag = (this.bitternTank.getFluid().getFluid() == TofuFluids.BITTERN.get() && this.bitternTank.getFluid().getAmount() >= 200);
		ItemStack itemstack1 = this.items.get(3);
		ItemStack itemstack2 = this.items.get(2);
		if (itemstack2.getItem() == Items.GLASS_BOTTLE) {
			if (itemstack1.isEmpty())
				return flag;
			if (itemstack1.getCount() + 1 <= getMaxStackSize() && itemstack1.getCount() + 1 <= itemstack1.getMaxStackSize())
				return flag;
			return false;
		}
		return false;
	}

	protected boolean canPutWater() {
		boolean flag = (this.waterTank.getFluid().getAmount() <= 1000);
		ItemStack itemstack1 = this.items.get(4);
		if (itemstack1.getItem() == Items.WATER_BUCKET) {
			return flag;
		}
		return false;
	}

	private void putWater() {
		if (canPutWater()) {
			ItemStack itemstack1 = new ItemStack(Items.BUCKET, 1);
			ItemStack itemstack2 = this.items.get(4);
			itemstack2.shrink(1);
			if (itemstack2.isEmpty()) {
				this.items.set(4, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			this.waterTank.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
		}
	}

	private void makeBittern() {
		if (hasBittern()) {
			ItemStack itemstack1 = new ItemStack(TofuItems.BITTERN_BOTTLE.get(), 1);
			ItemStack itemstack2 = this.items.get(3);
			ItemStack itemstack3 = this.items.get(2);
			itemstack3.shrink(1);
			if (itemstack2.isEmpty()) {
				this.items.set(3, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			this.bitternTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
			this.setChanged();
		}
	}

	private void makeSalt() {
		if (hasWater()) {
			ItemStack itemstack1 = new ItemStack(TofuItems.SALT.get(), 2);
			ItemStack itemstack2 = this.items.get(1);
			if (itemstack2.isEmpty()) {
				this.items.set(1, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			this.waterTank.drain(200, IFluidHandler.FluidAction.EXECUTE);
			this.bitternTank.fill(new FluidStack(TofuFluids.BITTERN.get(), 200), IFluidHandler.FluidAction.EXECUTE);
			this.setChanged();
		}
	}

	public void popExperience(ServerPlayer p_155004_, ItemStack p_39558_) {
		createExperience(p_155004_.serverLevel(), p_155004_.position(), 1, p_39558_.getCount());
	}


	private static void createExperience(ServerLevel p_154999_, Vec3 p_155000_, int p_155001_, float p_155002_) {
		int i = Mth.floor((float) p_155001_ * p_155002_);
		float f = Mth.frac((float) p_155001_ * p_155002_);
		if (f != 0.0F && Math.random() < (double) f) {
			++i;
		}

		ExperienceOrb.award(p_154999_, p_155000_, i);
	}

	protected int getBurnDuration(Level level, ItemStack p_213997_1_) {
		if (p_213997_1_.isEmpty())
			return 0;
		return p_213997_1_.getBurnTime(RecipeType.SMELTING, level.fuelValues());
	}

	protected int getTotalCookTime() {
		return 200;
	}

	public static boolean isFuel(Level level, ItemStack p_213991_0_) {
		return (p_213991_0_.getBurnTime(null, level.fuelValues()) > 0);
	}

	@Override
	public int[] getSlotsForFace(Direction p_180463_1_) {
		if (p_180463_1_ == Direction.UP) {
			return SLOTS_FOR_UP;
		}

		if (p_180463_1_ == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		}

		return SLOTS_FOR_SIDES;
	}


	@Override
	public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
		return canPlaceItem(p_180462_1_, p_180462_2_);
	}

	public boolean canPlaceItem(int p_94041_1_, ItemStack p_94041_2_) {
		if (p_94041_1_ == 3 || p_94041_1_ == 1) {
			return false;
		}
		if (p_94041_1_ == 2) {
			return (p_94041_2_.getItem() == Items.GLASS_BOTTLE);
		}
		if (p_94041_1_ == 4) {
			return p_94041_2_.getItem() == Items.WATER_BUCKET;
		}
		ItemStack itemstack = this.items.get(0);
		return (isFuel(this.level, p_94041_2_) || (p_94041_2_.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET));
	}

	@Override
	public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		if (p_180461_1_ == 0 || p_180461_1_ == 2) {
			return false;
		}

		if (p_180461_1_ == 4) {
			return p_180461_2_.getItem() == Items.BUCKET;
		}

		return true;
	}

	public int getContainerSize() {
		return 5;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public ItemStack getItem(int p_70301_1_) {
		return this.items.get(p_70301_1_);
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_332640_) {
		this.items = p_332640_;
	}

	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ContainerHelper.removeItem(this.items, p_70298_1_, p_70298_2_);
	}

	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ContainerHelper.takeItem(this.items, p_70304_1_);
	}

	public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
		ItemStack itemstack = this.items.get(p_70299_1_);
		boolean flag = !p_70299_2_.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, p_70299_2_);
		this.items.set(p_70299_1_, p_70299_2_);
		if (p_70299_2_.getCount() > this.getMaxStackSize()) {
			p_70299_2_.setCount(this.getMaxStackSize());
		}

		if (p_70299_1_ == 0 && !flag && this.cookingTotalTime == 0) {
			this.cookingTotalTime = this.getTotalCookTime();
			this.cookingProgress = 0;
		}

	}

	public boolean stillValid(Player p_70300_1_) {
		return Container.stillValidBlockEntity(this, p_70300_1_);
	}

	public void clearContent() {
		this.items.clear();
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.tofucraft.salt_furnace");
	}

	@Override
	public AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_, Player p_58643_) {
		return this.createMenu(p_58627_, p_58628_);
	}

	@Override
	protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
		return new SaltFurnaceMenu(p_58627_, p_58628_, this, this.dataAccess);
	}

	@Override
	public void fillStackedContents(StackedItemContents p_40281_) {
		for (ItemStack itemstack : this.items) {
			p_40281_.accountStack(itemstack);
		}
	}
}
