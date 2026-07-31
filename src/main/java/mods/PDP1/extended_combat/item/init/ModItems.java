package mods.PDP1.extended_combat.item.init;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mods.PDP1.extended_combat.Extendedcombat;
import mods.PDP1.extended_combat.item.registry.KnuckleDamage;
import mods.PDP1.extended_combat.item.registry.ModArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.UUID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Extendedcombat.MODID);

    private static final UUID SPEED_ID = UUID.fromString("789e4567-e89b-12d3-a456-426614174000");
    private static final UUID KNUCKLES_SPEED_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public static final RegistryObject<Item> LEATHER_SLIDES = ITEMS.register("leather_slides", () ->
            new ArmorItem(ModArmorMaterials.SLIDES, ArmorItem.Type.BOOTS, new Item.Properties()) { // Remove .durability(52)

                @Override
                public int getMaxDamage(ItemStack stack) {
                    return 52; // Hardcodes the durability exactly to what you want
                }

                @Override
                public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
                    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
                    builder.putAll(super.getAttributeModifiers(slot, stack));
                    if (slot == EquipmentSlot.FEET) {
                        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_ID, "Slides Speed", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
                    }
                    return builder.build();
                }
            });

    public static final RegistryObject<Item> IRON_KNUCKLES = ITEMS.register("iron_knuckles",
            () -> new KnuckleDamage(56, 7, 3.0, new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_KNUCKLES = ITEMS.register("golden_knuckles",
            () -> new KnuckleDamage(25, 5, 2.5, new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_KNUCKLES = ITEMS.register("diamond_knuckles",
            () -> new KnuckleDamage(1280, 8, 2.5, new Item.Properties()));
}
