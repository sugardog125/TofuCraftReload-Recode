package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(EntityTypeTags.ARROWS).add(TofuEntityTypes.ZUNDA_ARROW.get());
		this.tag(Tags.EntityTypes.BOSSES).add(TofuEntityTypes.SHUDOFUSPIDER.get());
		this.tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(TofuEntityTypes.ZUNDAMITE.get());

		this.tag(TofuTags.EntityTypes.FUKUMAME).add(TofuEntityTypes.FUKUMAME.get()).add(TofuEntityTypes.SOUL_FUKUMAME.get()).add(TofuEntityTypes.NETHER_FUKUMAME.get());

		this.tag(EntityTypeTags.FROG_FOOD).add(TofuEntityTypes.TOFUSLIME.get());
		this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(TofuEntityTypes.SHUDOFUSPIDER.get(), TofuEntityTypes.TOFU_GANDLEM.get(), TofuEntityTypes.TOFU_GOLEM.get());
		this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(TofuEntityTypes.SHUDOFUSPIDER.get(), TofuEntityTypes.TOFU_GANDLEM.get());
		this.tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(TofuEntityTypes.SHUDOFUSPIDER.get(), TofuEntityTypes.TOFU_GANDLEM.get(), TofuEntityTypes.TOFU_GOLEM.get()).add(TofuEntityTypes.TOFUFISH.get());
		this.tag(EntityTypeTags.ARTHROPOD).add(TofuEntityTypes.SHUDOFUSPIDER.get()).add(TofuEntityTypes.TOFUSPIDER.get());
		this.tag(EntityTypeTags.AQUATIC).add(TofuEntityTypes.TOFUFISH.get());
		this.tag(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA).addTag(EntityTypeTags.UNDEAD).add(EntityType.ENDER_DRAGON).add(EntityType.ENDERMAN).add(EntityType.ENDERMITE).add(EntityType.END_CRYSTAL);
		this.tag(TofuTags.EntityTypes.WALKABLE_WITHOUT_TRIGGER).addTag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS);
	}
}