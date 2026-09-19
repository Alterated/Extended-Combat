package net.Alterated.mods.extended_combat.event;

import net.Alterated.mods.extended_combat.Extendedcombat;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.UUID;

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

    private static final UUID MINUS_BLOCK_REACH_UUID = UUID.fromString("a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d");
    private static final UUID MINUS_ENTITY_REACH_UUID = UUID.fromString("f6e5d4c3-b2a1-0f9e-8d7c-6b5a4f3e2d1c");

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        var blockReachAttr = player.getAttribute(ForgeMod.BLOCK_REACH.get());
        if (blockReachAttr != null && blockReachAttr.getModifier(MINUS_BLOCK_REACH_UUID) == null) {
            blockReachAttr.addTransientModifier(new AttributeModifier(
                    MINUS_BLOCK_REACH_UUID,
                    "Reduced block reach",
                    -1.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        // 2. Reduce Entity Reach (Attack/Interaction) by 1.0
        var entityReachAttr = player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (entityReachAttr != null && entityReachAttr.getModifier(MINUS_ENTITY_REACH_UUID) == null) {
            entityReachAttr.addTransientModifier(new AttributeModifier(
                    MINUS_ENTITY_REACH_UUID,
                    "Reduced entity reach",
                    -1.0D,
                    AttributeModifier.Operation.ADDITION
            ));
        }
    }

    /* @Mod.EventBusSubscriber(modid = Extendedcombat.MODID)
    public static class ReachModifierHandler {

        private static final UUID MINUS_BLOCK_REACH_UUID = UUID.fromString("a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d");
        private static final UUID MINUS_ENTITY_REACH_UUID = UUID.fromString("f6e5d4c3-b2a1-0f9e-8d7c-6b5a4f3e2d1c");

        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();

            var blockReachAttr = player.getAttribute(ForgeMod.BLOCK_REACH.get());
            if (blockReachAttr != null && blockReachAttr.getModifier(MINUS_BLOCK_REACH_UUID) == null) {
                blockReachAttr.addTransientModifier(new AttributeModifier(
                        MINUS_BLOCK_REACH_UUID,
                        "Reduced block reach",
                        -1.0D,
                        AttributeModifier.Operation.ADDITION
                ));
            }

            // 2. Reduce Entity Reach (Attack/Interaction) by 1.0
            var entityReachAttr = player.getAttribute(ForgeMod.ENTITY_REACH.get());
            if (entityReachAttr != null && entityReachAttr.getModifier(MINUS_ENTITY_REACH_UUID) == null) {
                entityReachAttr.addTransientModifier(new AttributeModifier(
                        MINUS_ENTITY_REACH_UUID,
                        "Reduced entity reach",
                        -1.0D,
                        AttributeModifier.Operation.ADDITION
                ));
            }
        }
    } */
}
