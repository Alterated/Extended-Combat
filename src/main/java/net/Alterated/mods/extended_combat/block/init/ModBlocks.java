package net.Alterated.mods.extended_combat.block.init;

import net.Alterated.mods.extended_combat.Extendedcombat;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Extendedcombat.MODID);

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final RegistryObject<Block> ROPE_BUNDLE = BLOCKS.register(
            "rope_bundle", () ->
                    new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS))
    );

    public static final RegistryObject<Block> ROPE_BLOCK = BLOCKS.register(
            "rope_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .noOcclusion()
                            .noCollission()
                            .replaceable()
                            .instabreak()
                            .mapColor(DyeColor.ORANGE)
                            .sound(SoundType.VINE)
                    )
    );
}
