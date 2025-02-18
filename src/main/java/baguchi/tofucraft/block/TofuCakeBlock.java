package baguchi.tofucraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TofuCakeBlock extends CakeBlock {
	public static final int MAX_BITES = 6;
	public static final IntegerProperty BITES = BlockStateProperties.BITES;

	public final int foodHeal;
	public final float foodSaturation;

	protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)};

	public TofuCakeBlock(Properties p_51184_, int foodHeal, float foodSaturation) {
		super(p_51184_);
		this.foodHeal = foodHeal;
		this.foodSaturation = foodSaturation;
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
	}

	@Override
	public VoxelShape getShape(BlockState p_51222_, BlockGetter p_51223_, BlockPos p_51224_, CollisionContext p_51225_) {
		return SHAPE_BY_BITE[p_51222_.getValue(BITES)];
	}

	@Override
	protected InteractionResult useItemOn(ItemStack p_316238_, BlockState p_316837_, Level p_316766_, BlockPos p_316227_, Player p_316853_, InteractionHand p_316422_, BlockHitResult p_316869_) {
		Item item = p_316238_.getItem();
		if (p_316238_.is(ItemTags.CANDLES) && (Integer) p_316837_.getValue(BITES) == 0) {
			Block var10 = Block.byItem(item);
			if (var10 instanceof CandleBlock) {
				CandleBlock candleblock = (CandleBlock) var10;
				p_316238_.consume(1, p_316853_);
				p_316766_.playSound((Player) null, p_316227_, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
				p_316766_.setBlockAndUpdate(p_316227_, CandleTofuCakeBlock.byCandle(candleblock, this));
				p_316766_.gameEvent(p_316853_, GameEvent.BLOCK_CHANGE, p_316227_);
				p_316853_.awardStat(Stats.ITEM_USED.get(item));
				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState p_316481_, Level p_316406_, BlockPos p_316218_, Player p_316212_, BlockHitResult p_316525_) {
		if (p_316406_.isClientSide) {
			if (eat(p_316406_, p_316218_, p_316481_, p_316212_).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (p_316212_.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return eat(p_316406_, p_316218_, p_316481_, p_316212_);
	}

	protected static InteractionResult eat(LevelAccessor p_51186_, BlockPos p_51187_, BlockState p_51188_, Player p_51189_) {
		if (!p_51189_.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			p_51189_.awardStat(Stats.EAT_CAKE_SLICE);
			p_51189_.getFoodData().eat(2, 0.1F);
			int i = (Integer) p_51188_.getValue(BITES);
			p_51186_.gameEvent(p_51189_, GameEvent.EAT, p_51187_);
			if (i < 6) {
				p_51186_.setBlock(p_51187_, (BlockState) p_51188_.setValue(BITES, i + 1), 3);
			} else {
				p_51186_.removeBlock(p_51187_, false);
				p_51186_.gameEvent(p_51189_, GameEvent.BLOCK_DESTROY, p_51187_);
			}

			return InteractionResult.SUCCESS;
		}
	}

	@Override
	protected BlockState updateShape(BlockState p_51032_, LevelReader p_374532_, ScheduledTickAccess p_374466_, BlockPos p_51036_, Direction p_51033_, BlockPos p_51037_, BlockState p_51034_, RandomSource p_374272_) {
		return p_51033_ == Direction.DOWN && !p_51032_.canSurvive(p_374532_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_374532_, p_374466_, p_51036_, p_51033_, p_51037_, p_51034_, p_374272_);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51220_) {
		p_51220_.add(BITES);
	}
}