package mods.PDP1.extended_combat.event;

import mods.PDP1.extended_combat.Extendedcombat;
import mods.PDP1.extended_combat.item.KnuckleDamage;
import mods.PDP1.extended_combat.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Extendedcombat.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player target)) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }

        ItemStack weapon = attacker.getMainHandItem();

        // 1. Define the group of items at the top of your class (or inside the method)
        List<RegistryObject<Item>> KNUCKLE_ITEMS = List.of(
                ModItems.IRON_KNUCKLES,
                ModItems.GOLDEN_KNUCKLES,
                ModItems.DIAMOND_KNUCKLES
        );

// Check if the held item matches any item in your list
        boolean isKnuckle = KNUCKLE_ITEMS.stream().anyMatch(regObject -> weapon.is(regObject.get()));

        if (isKnuckle) {
            boolean targetHasArmor = target.getArmorValue() > 0;
            boolean targetIsBlocking = target.isBlocking();

            if (targetIsBlocking || targetHasArmor) {
                float currentDamage = event.getAmount();
                float damageMultiplier = 1.5f;
                event.setAmount(currentDamage * damageMultiplier);
            }
        }

    }
}
