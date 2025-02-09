package baguchi.tofucraft.api.entity;

import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;

public class TofunianVariant {
	public static final Codec<TofunianVariant> DIRECT_CODEC = RecordCodecBuilder.create(
			p_332779_ -> p_332779_.group(
							ResourceLocation.CODEC.fieldOf("texture").forGetter(p_335261_ -> p_335261_.texture),
							RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(TofunianVariant::biomes)
					)
					.apply(p_332779_, TofunianVariant::new)
	);
	public static final Codec<Holder<TofunianVariant>> CODEC = RegistryFileCodec.create(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, DIRECT_CODEC);
	public static final StreamCodec<RegistryFriendlyByteBuf, Holder<TofunianVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY);
	private final ResourceLocation texture;
	private final ResourceLocation textureFull;
	private final HolderSet<Biome> biomes;

	public TofunianVariant(ResourceLocation p_332712_, HolderSet<Biome> p_332717_) {
		this.texture = p_332712_;
		this.textureFull = fullTextureId(p_332712_);
		this.biomes = p_332717_;
	}

	private static ResourceLocation fullTextureId(ResourceLocation p_336042_) {
		return p_336042_.withPath(p_335262_ -> "textures/" + p_335262_ + ".png");
	}

	public ResourceLocation texture() {
		return this.textureFull;
	}

	public HolderSet<Biome> biomes() {
		return this.biomes;
	}

	@Override
	public boolean equals(Object p_332811_) {
		if (p_332811_ == this) {
			return true;
		} else {
			return !(p_332811_ instanceof TofunianVariant tofunianVariant)
					? false
					: Objects.equals(this.texture, tofunianVariant.texture)
					&& Objects.equals(this.biomes, tofunianVariant.biomes);
		}
	}

	@Override
	public int hashCode() {
		int i = 1;
		i = 31 * i + this.texture.hashCode();
		return 31 * i + this.biomes.hashCode();
	}
}