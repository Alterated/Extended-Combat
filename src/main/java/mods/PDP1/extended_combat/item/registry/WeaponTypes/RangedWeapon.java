package mods.PDP1.extended_combat.item.registry.WeaponTypes;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
public class RangedWeapon extends BowItem {

    private final int maxDrawDuration;
    private final float bonusDamage;
    private final float projectileVelocity;

    RangedWeapon(int maxDamage, int maxDrawDuration, float projectileVelocity, float bonusDamage, Properties properties) {
        super(properties.durability(maxDamage));

        this.bonusDamage = bonusDamage;
        this.projectileVelocity = projectileVelocity;
        this.maxDrawDuration = maxDrawDuration;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            boolean hasInfiniteArrows = player.getAbilities().instabuild;
            ItemStack ammoStack = player.getProjectile(stack);

            int chargeTicks = this.getUseDuration(stack) - timeLeft;

            // Calculate pull progress based on custom draw duration
            float pullProgress = getCustomPowerForTime(chargeTicks);

            if (!ammoStack.isEmpty() || hasInfiniteArrows) {
                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(Items.ARROW);
                }

                if (!((double) pullProgress < 0.1D)) {
                    boolean isFreeArrow = hasInfiniteArrows || (ammoStack.getItem() instanceof ArrowItem arrowItem && arrowItem.isInfinite(ammoStack, stack, player));

                    if (!level.isClientSide) {
                        ArrowItem arrowItem = (ArrowItem) (ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
                        AbstractArrow arrow = arrowItem.createArrow(level, ammoStack, player);

                        arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, pullProgress * this.projectileVelocity, 0.4F);

                        if (pullProgress == 1.0F) {
                            arrow.setCritArrow(true);
                        }

                        arrow.setBaseDamage(arrow.getBaseDamage() + this.bonusDamage);

                        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));

                        level.addFreshEntity(arrow);
                    }

                    level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);

                    if (!isFreeArrow && !player.getAbilities().instabuild) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            player.getInventory().removeItem(ammoStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public float getCustomPowerForTime(int chargeTicks) {
        // Divides held time by maxDrawDuration (60)
        float progress = (float) chargeTicks / (float) this.maxDrawDuration;

        // Bow pull curve math
        progress = (progress * progress + progress * 2.0F) / 3.0F;

        if (progress > 1.0F) {
            progress = 1.0F;
        }
        return progress;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
                if (!entity.isUsingItem()) {
                    return HumanoidModel.ArmPose.ITEM;
                }
                return HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
        });
    }
}
