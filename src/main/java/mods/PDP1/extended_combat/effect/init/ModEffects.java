package mods.PDP1.extended_combat.effect.init;

import mods.PDP1.extended_combat.effect.registry.BleedingEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, "extended_combat");

    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register(
            "bleeding",() -> new BleedingEffect()
    );
}
