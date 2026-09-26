package net.Alterated.mods.extended_combat.entity;

import net.Alterated.mods.extended_combat.Extendedcombat;
import net.Alterated.mods.extended_combat.entity.custom.ThrowingAxeProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Extendedcombat.MODID);

    public static final RegistryObject<EntityType<ThrowingAxeProjectileEntity>> THROWING_AXE =
            ENTITY_TYPES.register("throwing_axe_handheld.json", () -> EntityType.Builder.<ThrowingAxeProjectileEntity>of(ThrowingAxeProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("throwing_axe_handheld.json"));
}
