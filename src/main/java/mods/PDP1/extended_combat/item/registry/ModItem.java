package mods.PDP1.extended_combat.item.registry;

import net.minecraft.world.item.Item;
import static mods.PDP1.extended_combat.Extendedcombat.props;

public class ModItem {

    // Private constructor prevents this utility class from being instantiated directly
    private ModItem() {}

    public static WeaponDamage knuckles(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new WeaponDamage(
                3.0,
                maxDamage,
                attackDamage,
                attackSpeed,
                0.05,
                false,
                false,
                false,
                props()
        );
    }

    public static WeaponDamage naginata(int maxDamage, int attackDamage, Item.Properties properties) {
        return new WeaponDamage(
                4.0,
                maxDamage,
                attackDamage,
                1.0,
                -0.05,
                true,
                true,
                true,
                props()
        );
    }
}