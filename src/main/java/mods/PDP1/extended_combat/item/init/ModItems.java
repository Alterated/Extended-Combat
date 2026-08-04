package mods.PDP1.extended_combat.item.init;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mods.PDP1.extended_combat.Extendedcombat;
import mods.PDP1.extended_combat.item.registry.ModArmorMaterials;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.UUID;
import mods.PDP1.extended_combat.item.registry.ModItem;

import static mods.PDP1.extended_combat.Extendedcombat.props;

public class ModItems {



    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Extendedcombat.MODID);

    private static final UUID SPEED_ID = UUID.fromString("789e4567-e89b-12d3-a456-426614174000");

    public static final RegistryObject<Item> LEATHER_SLIDES = ITEMS.register(
            "leather_slides", () ->
            new ArmorItem(
                    ModArmorMaterials.SLIDES,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties()
            ) {

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
            }
    );

    public static final RegistryObject<Item> IRON_KNUCKLES = ITEMS.register(
            "iron_knuckles",
            () -> ModItem.knuckles(
                    232,
                    5,
                    3.0,
                    props()
            )
    );
    public static final RegistryObject<Item> GOLDEN_KNUCKLES = ITEMS.register(
            "golden_knuckles",
            () -> ModItem.knuckles(
                    25,
                    3,
                    2.7,
                    props()
            )
    );
    public static final RegistryObject<Item> DIAMOND_KNUCKLES = ITEMS.register(
            "diamond_knuckles",
            () -> ModItem.knuckles(
                    1280,
                    7,
                    3.1,
                    props()
            )
    );

    public static final RegistryObject<Item> IRON_NAGINATA = ITEMS.register(
            "iron_naginata",
            () -> ModItem.naginata(
                    256 + 56,
                    7,
                    props()
            )
    );
    public static final RegistryObject<Item> GOLDEN_NAGINATA = ITEMS.register(
            "golden_naginata",
            () -> ModItem.naginata(
                    46,
                    5,
                    props()
            )
    );
    public static final RegistryObject<Item> DIAMOND_NAGINATA = ITEMS.register(
            "diamond_naginata",
            () -> ModItem.naginata(
                    1902,
                    9,
                    props()
            )
    );
}
