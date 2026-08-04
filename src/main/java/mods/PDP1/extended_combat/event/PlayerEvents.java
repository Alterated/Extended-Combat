package mods.PDP1.extended_combat.event;

import mods.PDP1.extended_combat.Extendedcombat;
import mods.PDP1.extended_combat.item.init.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = Extendedcombat.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {

        LivingEntity target = event.getEntity();
        if (target == null) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }


        ItemStack weapon = attacker.getMainHandItem();

        List<RegistryObject<Item>> KNUCKLE_ITEMS = List.of(
                ModItems.IRON_KNUCKLES,
                ModItems.GOLDEN_KNUCKLES,
                ModItems.DIAMOND_KNUCKLES
        );

        List<RegistryObject<Item>> NAGINATA_ITEMS = List.of(
                ModItems.IRON_NAGINATA,
                ModItems.GOLDEN_NAGINATA,
                ModItems.DIAMOND_NAGINATA
        );

        boolean isKnuckle = KNUCKLE_ITEMS.stream().anyMatch(regObject -> weapon.is(regObject.get()));
        boolean isNaginata = NAGINATA_ITEMS.stream().anyMatch(regObject -> weapon.is(regObject.get()));

        if (isKnuckle) {
            boolean targetHasArmor = target.getArmorValue() > 0;
            boolean targetIsBlocking = target.isBlocking();

            if (targetIsBlocking || targetHasArmor) {
                float currentDamage = event.getAmount();
                float damageMultiplier = 1.5f;
                event.setAmount(currentDamage * damageMultiplier);
            }
        }

        if (isNaginata) {
            boolean targetHasArmor = target.getArmorValue() > 0;
            boolean targetIsBlocking = target.isBlocking();

            if (targetIsBlocking || targetHasArmor) {
                float currentDamage = event.getAmount();
                float damageMultiplier = 0.75f;
                event.setAmount(currentDamage * damageMultiplier);
            }
        }

    }
}
