package baguchi.tofucraft.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * <p>Cake-based blocks with candles and related utils.</p>
 * <p>Structure based on <a href=https://github.com/team-abnormals/neapolitan/blob/1.19.x/src/main/java/com/teamabnormals/neapolitan/common/block/FlavoredCakeBlock.java>FlavoredCakeBlock</a></p>
 *
 * @author bagu_chan
 * @since 3.0.0.0
 */
public class CandleTofuCakeBlock extends AbstractCandleBlock {
	public static final MapCodec<CandleTofuCakeBlock> CODEC = RecordCodecBuilder.mapCodec(
			p_308809_ -> p_308809_.group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("cake").forGetter((p_304363_) -> p_304363_.baseCake), BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter(p_304363_ -> p_304363_.candle), propertiesCodec())
					.apply(p_308809_, CandleTofuCakeBlock::new)
	);

	public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
	protected static final VoxelShape CAKE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
	protected static final VoxelShape CANDLE_SHAPE = Block.box(7.0D, 8.0D, 7.0D, 9.0D, 14.0D, 9.0D);
	protected static final VoxelShape SHAPE = Shapes.or(CAKE_SHAPE, CANDLE_SHAPE);
	private static final Map<Pair<Block, TofuCakeBlock>, CandleTofuCakeBlock> BY_CANDLE_AND_CAKE = Maps.newHashMap();
	private static final Iterable<Vec3> PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5D, 1.0D, 0.5D));

	private final Block baseCake;
	private final Block candle;

	public CandleTofuCakeBlock(Block baseCake, Block candle, Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE));
		this.baseCake = baseCake;
		this.candle = candle;

		BY_CANDLE_AND_CAKE.put(Pair.of(candle, (TofuCakeBlock) baseCake), this);
	}

	@Override
	protected MapCodec<? extends AbstractCandleBlock> codec() {
		return CODEC;
	}

	@Override
	protected Iterable<Vec3> getParticleOffsets(BlockState p_152868_) {
		return PARTICLE_OFFSETS;
	}

	@Override
	public VoxelShape getShape(BlockState p_152875_, BlockGetter p_152876_, BlockPos p_152877_, CollisionContext p_152878_) {
		return SHAPE;
	}


	@Override
	protected InteractionResult useItemOn(
			ItemStack p_316571_, BlockState p_316514_, Level p_316171_, BlockPos p_316112_, Player p_316172_, InteractionHand p_316257_, BlockHitResult p_316286_
	) {
		if (p_316571_.is(Items.FLINT_AND_STEEL) || p_316571_.is(Items.FIRE_CHARGE)) {
			return InteractionResult.PASS;
		} else if (candleHit(p_316286_) && p_316571_.isEmpty() && p_316514_.getValue(LIT)) {
			extinguish(p_316172_, p_316514_, p_316171_, p_316112_);
			return InteractionResult.SUCCESS;
		} else {
			return super.useItemOn(p_316571_, p_316514_, p_316171_, p_316112_, p_316172_, p_316257_, p_316286_);
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_316519_, Level p_316226_, BlockPos p_316122_, Player p_316438_, BlockHitResult p_316849_) {
		InteractionResult interactionresult = TofuCakeBlock.eat(p_316226_, p_316122_, getCake().defaultBlockState(), p_316438_);
		if (interactionresult.consumesAction()) {
			dropResources(p_316519_, p_316226_, p_316122_);
		}

		return interactionresult;
	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
		return itemAbility == ItemAbilities.FIRESTARTER_LIGHT && canBeLit(state) ? state.setValue(LIT, true) : super.getToolModifiedState(state, context, itemAbility, simulate);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
		return new ItemStack(baseCake);
	}

	private static boolean candleHit(BlockHitResult result) {
		return result.getLocation().y - (double) result.getBlockPos().getY() > 0.5D;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152905_) {
		p_152905_.add(LIT);
	}

	@Override
	protected BlockState updateShape(BlockState p_51032_, LevelReader p_374532_, ScheduledTickAccess p_374466_, BlockPos p_51036_, Direction p_51033_, BlockPos p_51037_, BlockState p_51034_, RandomSource p_374272_) {
		return p_51033_ == Direction.DOWN && !p_51032_.canSurvive(p_374532_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_374532_, p_374466_, p_51036_, p_51033_, p_51037_, p_51034_, p_374272_);
	}

	@Override
	public boolean canSurvive(BlockState p_152891_, LevelReader p_152892_, BlockPos p_152893_) {
		return p_152892_.getBlockState(p_152893_.below()).isSolid();
	}

	@Override
	public int getAnalogOutputSignal(BlockState p_152880_, Level p_152881_, BlockPos p_152882_) {
		return CakeBlock.FULL_CAKE_SIGNAL;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState p_152909_) {
		return true;
	}

	@Override
	protected boolean isPathfindable(BlockState p_60475_, PathComputationType p_60478_) {
		return false;
	}

	public static boolean hasEntry(Block candle, TofuCakeBlock cake) {
		return BY_CANDLE_AND_CAKE.get(Pair.of(candle, cake)) != null;
	}

	public static BlockState byCandle(Block candle, TofuCakeBlock cake) {
		return BY_CANDLE_AND_CAKE.get(Pair.of(candle, cake)).defaultBlockState();
	}

	public Block getCandle() {
		return candle;
	}

	public Block getCake() {
		return baseCake;
	}
}