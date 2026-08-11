package mods.PDP1.extended_combat.datagen;

import mods.PDP1.extended_combat.block.ModBlocks;
import mods.PDP1.extended_combat.item.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.apache.commons.io.output.AppendableWriter;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static net.minecraft.data.models.model.TextureMapping.pattern;
import static net.minecraft.data.recipes.RecipeCategory.COMBAT;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.LEATHER_SLIDES.get(), 1)
                .pattern("A A")
                .define('A', Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.IRON_NAGINATA.get(), 1)
                .pattern(" AB").pattern(" CD").pattern("E  ")
                .define('A', Items.GOLD_NUGGET).define('B', Items.IRON_INGOT).define('C', Items.GOLD_INGOT).define('D', Items.GLOWSTONE_DUST).define('E', Items.STICK)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.DIAMOND_NAGINATA.get(), 1)
                .pattern(" AB").pattern(" CD").pattern("E  ")
                .define('A', Items.GOLD_NUGGET).define('B', Items.IRON_INGOT).define('C', Items.GOLD_INGOT).define('D', Items.GLOWSTONE_DUST).define('E', Items.STICK)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.IRON_KNUCKLES.get(), 2)
                .pattern("A A")
                .define('A', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.GOLDEN_KNUCKLES.get(), 2)
                .pattern("A A")
                .define('A', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.DIAMOND_KNUCKLES.get(), 2)
                .pattern("A A")
                .define('A', Items.DIAMOND)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(COMBAT, ModItems.LONGBOW.get(), 1)
                .pattern("BAB").pattern("CDB").pattern(" AB")
                .define('A', CompoundIngredient.of(Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.LOGS))).define('B', ModItems.ROPE.get()).define('C', Items.IRON_INGOT).define('D', Items.BOW)
                .unlockedBy(getHasName(ModItems.ROPE.get()), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(COMBAT, ModItems.ROPE.get(), 1)
                .pattern("AA ").pattern("AA ")
                .define('A', Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(pWriter);
    }
}
