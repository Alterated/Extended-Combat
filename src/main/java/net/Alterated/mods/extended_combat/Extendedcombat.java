package net.Alterated.mods.extended_combat;

import com.mojang.logging.LogUtils;
import net.Alterated.mods.extended_combat.block.init.ModBlocks;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@SuppressWarnings("removal")
// The value here should match an entry in the META-INF/mods.toml file
@Mod(Extendedcombat.MODID)
public class Extendedcombat {

    public static Item.Properties props() {
        return new Item.Properties();
    }
    // Define mod id in a common place for everything to reference
    public static final String MODID = "extended_combat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "extended_combat" namespace

    public Extendedcombat() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);

        LOGGER.info("Hello");
    }
}
