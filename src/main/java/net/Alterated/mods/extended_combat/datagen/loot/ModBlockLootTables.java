package net.Alterated.mods.extended_combat.datagen.loot;

import net.Alterated.mods.extended_combat.block.init.ModBlocks;
import net.Alterated.mods.extended_combat.item.init.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@SuppressWarnings("NullableProblems")
public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ROPE_BUNDLE.get());
        this.add(ModBlocks.ROPE_BLOCK.get(), block -> createSingleItemTable(ModItems.ROPE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
