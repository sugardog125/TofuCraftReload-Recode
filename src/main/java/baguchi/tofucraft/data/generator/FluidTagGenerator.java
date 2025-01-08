package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;

import java.util.concurrent.CompletableFuture;

public class FluidTagGenerator extends FluidTagsProvider {
	public FluidTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_) {
		super(p_256095_, p_256572_, TofuCraftReload.MODID);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_255894_) {
		this.tag(TofuTags.Fluids.SOYMILK).add(TofuFluids.SOYMILK.get(), TofuFluids.SOYMILK_HELL.get(), TofuFluids.SOYMILK_SOUL.get())
				.add(TofuFluids.SOYMILK_FLOW.get(), TofuFluids.SOYMILK_HELL_FLOW.get(), TofuFluids.SOYMILK_SOUL_FLOW.get());
	}
}