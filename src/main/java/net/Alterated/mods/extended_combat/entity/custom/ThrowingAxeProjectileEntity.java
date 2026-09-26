package net.Alterated.mods.extended_combat.entity.custom;

import net.Alterated.mods.extended_combat.entity.ModEntities;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ThrowingAxeProjectileEntity extends ThrowableItemProjectile {
    public ThrowingAxeProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowingAxeProjectileEntity(Level pLevel) {
        super(ModEntities.THROWING_AXE.get(), pLevel);
    }

    public ThrowingAxeProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.THROWING_AXE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWING_AXE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();
        Entity owner = this.getOwner();

        if (target instanceof LivingEntity livingTarget) {
            if (!this.level().isClientSide && this.level() instanceof ServerLevel serverLevel) {

                float damageAmount = 6.0F;

                DamageSource damageSource = serverLevel.damageSources().thrown(this, owner != null ? owner : this);

                livingTarget.hurt(damageSource, damageAmount);
            }
        }

        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.discard();
    }
}