package baguchi.tofucraft;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TofuConfig {
	public static final Common COMMON;
	public static final ModConfigSpec COMMON_SPEC;

	static {
		Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static class Common {

		public final ModConfigSpec.BooleanValue travelerTofunianSpawn;
		public final ModConfigSpec.BooleanValue enableExtra;

		public Common(ModConfigSpec.Builder builder) {
			travelerTofunianSpawn = builder
					.translation(TofuCraftReload.MODID + ".config.travelerTofunianSpawn")
					.comment("Spawn Traveler Tofunian.")
					.define("Traveler Tofunian Spawn", true);
			enableExtra = builder
					.translation(TofuCraftReload.MODID + ".config.enable_extra")
					.comment("Force Enable The Extra Feature(Such as enable Enter the Tofu World).")
					.define("Force Enable Extra Feature", true);
		}
	}
}