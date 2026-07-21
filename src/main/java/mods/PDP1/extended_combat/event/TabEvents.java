package mods.PDP1.extended_combat.event;

import mods.PDP1.extended_combat.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabEvents {

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

        if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
            tabData.getEntries().putAfter(
                    Items.LEATHER_BOOTS.getDefaultInstance(),
                    ModItems.LEATHER_SLIDES.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );

            tabData.getEntries().putAfter(
                    Items.NETHERITE_AXE.getDefaultInstance(),
                    ModItems.IRON_KNUCKLES.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            tabData.getEntries().putAfter(
                    ModItems.IRON_KNUCKLES.get().getDefaultInstance(),
                    ModItems.GOLDEN_KNUCKLES.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            tabData.getEntries().putAfter(
                    ModItems.GOLDEN_KNUCKLES.get().getDefaultInstance(),
                    ModItems.DIAMOND_KNUCKLES.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }
}
