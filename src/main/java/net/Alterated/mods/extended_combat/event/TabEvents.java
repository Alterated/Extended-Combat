package net.Alterated.mods.extended_combat.event;

import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabEvents {

    private static final CreativeModeTab.TabVisibility Vis_PAS = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

        if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
            tabData.getEntries().putAfter(
                    Items.LEATHER_BOOTS.getDefaultInstance(),
                    ModItems.LEATHER_SLIDES.get().getDefaultInstance(),
                    Vis_PAS
            );

            tabData.getEntries().putAfter(
                    Items.NETHERITE_AXE.getDefaultInstance(),
                    ModItems.IRON_KNUCKLES.get().getDefaultInstance(),
                    Vis_PAS
            );
            tabData.getEntries().putAfter(
                    ModItems.IRON_KNUCKLES.get().getDefaultInstance(),
                    ModItems.GOLDEN_KNUCKLES.get().getDefaultInstance(),
                    Vis_PAS
            );
            tabData.getEntries().putAfter(
                    ModItems.GOLDEN_KNUCKLES.get().getDefaultInstance(),
                    ModItems.DIAMOND_KNUCKLES.get().getDefaultInstance(),
                    Vis_PAS
            );

            tabData.getEntries().putAfter(
                    ModItems.DIAMOND_KNUCKLES.get().getDefaultInstance(),
                    ModItems.IRON_NAGINATA.get().getDefaultInstance(),
                    Vis_PAS
            );
            tabData.getEntries().putAfter(
                    ModItems.IRON_NAGINATA.get().getDefaultInstance(),
                    ModItems.DIAMOND_NAGINATA.get().getDefaultInstance(),
                    Vis_PAS
            );

            tabData.getEntries().putAfter(
                    Items.BOW.getDefaultInstance(),
                    ModItems.LONGBOW.get().getDefaultInstance(),
                    Vis_PAS
            );
        }

        if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {

            tabData.getEntries().putAfter(
                    Items.STRING.getDefaultInstance(),
                    ModItems.ROPE.get().getDefaultInstance(),
                    Vis_PAS
            );
        }
    }
}
