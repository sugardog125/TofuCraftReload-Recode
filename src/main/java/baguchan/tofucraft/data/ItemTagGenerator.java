package baguchan.tofucraft.data;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
	public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper exFileHelper) {
		super(packOutput, lookupProvider, provider, TofuCraftReload.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(TofuTags.Items.DUST_SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SALT).add(TofuItems.SALT.get());
		tag(TofuTags.Items.SOYBEAN).add(TofuItems.SEEDS_SOYBEANS.get());
		tag(TofuTags.Items.SOYMILK).addTag(TofuTags.Items.MILK_SOYMILK);
		tag(TofuTags.Items.MILK_SOYMILK).add(TofuItems.BUCKET_SOYMILK.get(), TofuItems.SOYMILK.get());
		tag(TofuTags.Items.RICE).add(TofuItems.RICE.get());
		tag(ItemTags.LOGS_THAT_BURN).add(TofuBlocks.TOFU_STEM.get().asItem(), TofuBlocks.LEEK_STEM.get().asItem(), TofuBlocks.LEEK_GREEN_STEM.get().asItem());
		tag(ItemTags.LOGS).add(TofuBlocks.TOFU_STEM.get().asItem(), TofuBlocks.LEEK_STEM.get().asItem(), TofuBlocks.LEEK_GREEN_STEM.get().asItem());
		tag(ItemTags.PLANKS).add(TofuBlocks.TOFU_STEM_PLANKS.get().asItem()).add(TofuBlocks.LEEK_PLANKS.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS.get().asItem());
		tag(ItemTags.ARROWS).add(TofuItems.ZUNDA_ARROW.get());
		tag(Tags.Items.SEEDS).add(TofuItems.SEEDS_CHILI.get(), TofuItems.SEEDS_SOYBEANS.get(), TofuItems.SEEDS_SOYBEANS_NETHER.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get(), TofuItems.SEEDS_RICE.get());
		tag(TofuTags.Items.RICE_BLOCK).add(TofuBlocks.RICE_BLOCK.get().asItem());
		tag(ItemTags.SWORDS).add(TofuItems.TOFU_KINU_SWORD.get()).add(TofuItems.TOFU_MOMEN_SWORD.get()).add(TofuItems.TOFU_SOLID_SWORD.get()).add(TofuItems.TOFU_METAL_SWORD.get()).add(TofuItems.TOFU_DIAMOND_SWORD.get());
		tag(ItemTags.PICKAXES).add(TofuItems.TOFU_KINU_PICKAXE.get()).add(TofuItems.TOFU_MOMEN_PICKAXE.get()).add(TofuItems.TOFU_SOLID_PICKAXE.get()).add(TofuItems.TOFU_METAL_PICKAXE.get()).add(TofuItems.TOFU_DIAMOND_PICKAXE.get());
		tag(ItemTags.AXES).add(TofuItems.TOFU_KINU_AXE.get()).add(TofuItems.TOFU_MOMEN_AXE.get()).add(TofuItems.TOFU_SOLID_AXE.get()).add(TofuItems.TOFU_METAL_AXE.get()).add(TofuItems.TOFU_DIAMOND_AXE.get());
		tag(ItemTags.SHOVELS).add(TofuItems.TOFU_KINU_SHOVEL.get()).add(TofuItems.TOFU_MOMEN_SHOVEL.get()).add(TofuItems.TOFU_SOLID_SHOVEL.get()).add(TofuItems.TOFU_METAL_SHOVEL.get()).add(TofuItems.TOFU_DIAMOND_SHOVEL.get());


		tag(ItemTags.TRIMMABLE_ARMOR).add(TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), TofuItems.TOFU_DIAMOND_LEGGINGS.get(), TofuItems.TOFU_DIAMOND_BOOTS.get())
				.add(TofuItems.TOFU_DIAMOND_HELMET.get(), TofuItems.TOFU_METAL_CHESTPLATE.get(), TofuItems.TOFU_METAL_LEGGINGS.get(), TofuItems.TOFU_METAL_BOOTS.get())
				.add(TofuItems.SCULK_BONE_HELMET.get(), TofuItems.SCULK_BONE_CHESTPLATE.get(), TofuItems.SCULK_BONE_LEGGINGS.get(), TofuItems.SCULK_BONE_BOOTS.get());
		tag(TofuTags.Items.NATTO).add(TofuItems.NATTO.get());
		tag(TofuTags.Items.MISO).add(TofuItems.MISO.get());
		tag(TofuTags.Items.SOYSAUCE_SOYSAUCE).add(TofuItems.BOTTLE_SOYSAUSE.get());
		tag(TofuTags.Items.SOYSAUCE).addTag(TofuTags.Items.SOYSAUCE_SOYSAUCE);
		tag(TofuTags.Items.TOFU).add(TofuItems.TOFUKINU.get()).add(TofuItems.TOFUMOMEN.get());
		tag(TofuTags.Items.TOFU_FRIED).add(TofuItems.TOFUFRIED.get());
		tag(ItemTags.PIGLIN_REPELLENTS).add(TofuItems.SOUL_FUKUMAME.get(), TofuItems.SOUL_MANJU.get(), TofuItems.SEEDS_SOYBEANS_SOUL.get());
		tag(ItemTags.TRIM_MATERIALS).add(TofuItems.ZUNDARUBY.get(), TofuItems.TOFUMETAL.get(), TofuItems.TOFUDIAMOND.get());
		tag(ItemTags.BOATS).add(TofuItems.LEEK_BOAT.get(), TofuItems.LEEK_GREEN_BOAT.get(), TofuItems.TOFU_STEM_BOAT.get());
		tag(ItemTags.CHEST_BOATS).add(TofuItems.LEEK_CHEST_BOAT.get(), TofuItems.LEEK_GREEN_CHEST_BOAT.get(), TofuItems.TOFU_STEM_CHEST_BOAT.get());
		this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(TofuBlocks.TOFUSLATE.get().asItem());
		this.tag(ItemTags.STONE_TOOL_MATERIALS).add(TofuBlocks.TOFUSLATE.get().asItem());

		tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(TofuItems.SEEDS_CHILI.get(), TofuItems.SEEDS_SOYBEANS.get());
		tag(ItemTags.WOODEN_SLABS).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get().asItem()).add(TofuBlocks.LEEK_PLANKS_SLAB.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get().asItem());
		tag(ItemTags.WOODEN_STAIRS).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get().asItem()).add(TofuBlocks.LEEK_PLANKS_STAIR.get().asItem()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get().asItem());
		tag(ItemTags.WOODEN_FENCES).add(TofuBlocks.LEEK_FENCE.get().asItem()).add(TofuBlocks.LEEK_GREEN_FENCE.get().asItem()).add(TofuBlocks.TOFU_STEM_FENCE.get().asItem());
		tag(ItemTags.FENCE_GATES).add(TofuBlocks.TOFU_STEM_FENCE_GATE.get().asItem()).add(TofuBlocks.LEEK_FENCE_GATE.get().asItem()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.get().asItem());
		tag(ItemTags.WOODEN_DOORS).add(TofuBlocks.TOFU_STEM_DOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_DOOR.get().asItem());
		tag(ItemTags.DOORS).add(TofuBlocks.TOFU_STEM_DOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_DOOR.get().asItem());
		tag(ItemTags.WOODEN_TRAPDOORS).add(TofuBlocks.TOFU_STEM_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.get().asItem());
		tag(ItemTags.TRAPDOORS).add(TofuBlocks.TOFU_STEM_TRAPDOOR.get().asItem()).add(TofuBlocks.LEEK_GREEN_TRAPDOOR.get().asItem());


		this.tag(ItemTags.SIGNS)
				.add(
						TofuBlocks.LEEK_SIGN.get().asItem(),
						TofuBlocks.LEEK_GREEN_SIGN.get().asItem(),
						TofuBlocks.TOFU_STEM_SIGN.get().asItem()
				);
		this.tag(ItemTags.HANGING_SIGNS)
				.add(
						TofuBlocks.LEEK_HANGING_SIGN.get().asItem(),
						TofuBlocks.LEEK_GREEN_HANGING_SIGN.get().asItem(),
						TofuBlocks.TOFU_STEM_HANGING_SIGN.get().asItem()
				);

		this.tag(TofuTags.Items.STATUE_HAPPY).add(TofuItems.EDAMAME_TEMPLA.get(), TofuItems.ZUNDA_MOCHI.get(), TofuItems.ZUNDA_MANJU.get()
				, TofuItems.KINAKO_MOCHI.get(), TofuItems.KINAKO_MANJU.get(), TofuItems.GOHEIMOCHI.get()
				, TofuItems.RICE_SOBORO_TOFU.get()
				, TofuItems.NANBANTOFU.get(), TofuItems.KOYADOFUSTEW.get(), TofuItems.MOYASHIITAME.get(), TofuItems.MOYASHIOHITASHI.get(), TofuItems.TOFUCOOKIE.get(), TofuItems.TOFUANNIN.get());
		this.tag(ItemTags.SMALL_FLOWERS).add(TofuBlocks.TOFU_FLOWER.get().asItem());
		this.tag(TofuTags.Items.BITTERN).add(TofuItems.BITTERN_BOTTLE.get()).add(TofuItems.WARPED_BOTTLE.get()).add(TofuItems.CRIMSON_BOTTLE.get());
	}
}