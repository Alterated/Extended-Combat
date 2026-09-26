package net.Alterated.mods.extended_combat.event.client;

import net.Alterated.mods.extended_combat.Extendedcombat;
import net.Alterated.mods.extended_combat.entity.ModEntities;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.UUID;

@SuppressWarnings("removal")
@Mod.EventBusSubscriber(modid = "extended_combat", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    ModItems.LONGBOW.get(),
                    new ResourceLocation("pull"),
                    (stack, level, entity, seed) -> {
                        if (entity == null || entity.getUseItem() != stack) {
                            return 0.0F;
                        }
                        int ticksHeld = stack.getUseDuration() - entity.getUseItemRemainingTicks();
                        return (float) ticksHeld / 70.0F;
                    }
            );
            ItemProperties.register(
                    ModItems.LONGBOW.get(),
                    new ResourceLocation("pulling"),
                    (stack, level, entity, seed) -> {
                        return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
                    }
            );
        });
    }

    public static int getExactVanillaColor(UUID uuid) {
        int hashCode = uuid.hashCode();
        int rawR = (hashCode & 0xFF0000) >> 16;
        int rawG = (hashCode & 0x00FF00) >> 8;
        int rawB = (hashCode & 0x0000FF);

        int r = Math.max(rawR, 25);
        int g = Math.max(rawG, 25);
        int b = Math.max(rawB, 25);

        return (255 << 24) | (r << 16) | (g << 8) | b;
    }

    @Mod.EventBusSubscriber(modid = Extendedcombat.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeWideClientEvents {

        @SubscribeEvent
        public static void onComputeFov(ComputeFovModifierEvent event) {
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            if (player.isUsingItem() && player.getUseItem().is(ModItems.LONGBOW.get())) {
                int ticksHeld = player.getTicksUsingItem();
                float progress = (float) ticksHeld / 70.0F;
                if (progress > 1.0F) {
                    progress = 1.0F;
                } else {
                    progress *= progress;
                }
                float fovModifier = 1.0F - (progress * 0.25F);
                event.setNewFovModifier(event.getFovModifier() * fovModifier);
            }
        }
    }
}
