package mods.PDP1.extended_combat.block;

import mods.PDP1.extended_combat.Extendedcombat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Extendedcombat.MODID);

    // public static final RegistryObject<Block> ROPE_BLOCK = BLOCKS.register(
    //         "rope_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_BLOCK))
    // );
}
