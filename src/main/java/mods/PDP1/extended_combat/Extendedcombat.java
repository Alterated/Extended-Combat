package mods.PDP1.extended_combat;

import com.mojang.logging.LogUtils;
import mods.PDP1.extended_combat.item.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Extendedcombat.MODID)
public class Extendedcombat {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "extended_combat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "extended_combat" namespace

    public Extendedcombat() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);

        LOGGER.info("Hello");
    }
}
