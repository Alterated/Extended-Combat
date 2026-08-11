package mods.PDP1.extended_combat.event;

import mods.PDP1.extended_combat.Extendedcombat;
import mods.PDP1.extended_combat.item.init.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Extendedcombat.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
class PlayerEvents {

    private static final Set<RegistryObject<Item>> KNUCKLE_ITEMS = Set.of(
            ModItems.IRON_KNUCKLES,
            ModItems.GOLDEN_KNUCKLES,
            ModItems.DIAMOND_KNUCKLES
    );

    private static final Set<RegistryObject<Item>> NAGINATA_ITEMS = Set.of(
            ModItems.IRON_NAGINATA,
            ModItems.DIAMOND_NAGINATA
    );

    private static final Set<RegistryObject<Item>> LONGBOW_ITEMS = Set.of(
            ModItems.LONGBOW
    );

    @SubscribeEvent
    public static void onPlayerDamage(LivingHurtEvent event) {

        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        LivingEntity target = event.getEntity();
        if (target == null) {
            return;
        }
        boolean targetHasArmor = target.getArmorValue() > 0;
        boolean targetIsBlocking = target.isBlocking();
        float currentDamage = event.getAmount();

        ItemStack weapon = attacker.getMainHandItem();

        boolean isKnuckle =
                KNUCKLE_ITEMS.stream().anyMatch(reg -> weapon.is(reg.get()));

        boolean isNaginata =
                NAGINATA_ITEMS.stream().anyMatch(reg -> weapon.is(reg.get()));

        boolean isLongbow =
                LONGBOW_ITEMS.stream().anyMatch(reg -> weapon.is(reg.get()));

        if (isKnuckle) {
            if (targetIsBlocking || targetHasArmor) {
                float damageMultiplier = 1.5f;

                event.setAmount(currentDamage * damageMultiplier);
            }
        } else if (isNaginata) {
            if (targetIsBlocking || targetHasArmor) {
                float damageMultiplier = 0.75f;

                event.setAmount(currentDamage * damageMultiplier);
            }
        } else if (isLongbow) {

            float armorDamageMultiplier = 0.90f;
            float blockingDamageMultiplier = 1.20f;

            float activearmorMultiplierProduct = 1.0f;
            float activeblockingMultiplierProduct = 1.0f;

            if (targetIsBlocking) {
                activeblockingMultiplierProduct = blockingDamageMultiplier;
            }
            if (targetHasArmor) {
                activearmorMultiplierProduct = armorDamageMultiplier;
            }

            event.setAmount((activearmorMultiplierProduct * activeblockingMultiplierProduct) * currentDamage);
        }
    }
}
