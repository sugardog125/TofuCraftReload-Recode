package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.AbstractTofunian;
import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class TofunianTradeWithPlayerGoal extends Goal {
    private final AbstractTofunian mob;

    public TofunianTradeWithPlayerGoal(AbstractTofunian p_25958_) {
        this.mob = p_25958_;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    public boolean canUse() {
        if (!this.mob.isAlive()) {
            return false;
        } else if (this.mob.isInWater()) {
            return false;
        } else if (!this.mob.onGround()) {
            return false;
        } else if (this.mob.hurtMarked) {
            return false;
        } else {
            Player player = this.mob.getTradingPlayer();
            if (player == null) {
                return false;
            } else if (this.mob.distanceToSqr(player) > 16.0D) {
                return false;
            } else {
                return player.containerMenu != null;
            }
        }
    }

    public void start() {
        this.mob.getNavigation().stop();
		if (this.mob instanceof Tofunian tofunian) {
			tofunian.setAction(Tofunian.Actions.CALL);
		}
    }

    public void stop() {
        this.mob.setTradingPlayer((Player) null);
    }
}