package baguchi.tofucraft.effect;

import baguchi.tofucraft.attachment.TofuLivingAttachment;
import baguchi.tofucraft.registry.TofuAttachments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class RecoverEffect extends MobEffect {
	public RecoverEffect(MobEffectCategory mobEffectCategory, int i) {
		super(mobEffectCategory, i);
	}

	@Override
	public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int p_301079_) {
		super.applyEffectTick(serverLevel, livingEntity, p_301079_);
		TofuLivingAttachment attachment = livingEntity.getData(TofuAttachments.TOFU_LIVING.get());
		if (livingEntity instanceof Player player && attachment.getRecoverHealth() >= 1) {
			player.heal(1);
			attachment.setRecoverHealth(attachment.getRecoverHealth() - 1);
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
		int i = 20 >> p_294232_;
		return i > 0 ? p_295368_ % i == 0 : true;
	}
}
