package mods.PDP1.extended_combat.item.registry.WeaponTypes;

import net.minecraft.world.item.Item;
import static mods.PDP1.extended_combat.Extendedcombat.props;

public class ModItem {

    private ModItem() {}

    public static MeleeWeapon knuckles(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new MeleeWeapon(
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
    public static MeleeWeapon naginata(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new MeleeWeapon(
                4.0,
                maxDamage,
                attackDamage,
                attackSpeed,
                -0.05,
                true,
                true,
                true,
                props()
        );
    }

    public static RangedWeapon longbow(int maxDamage, Item.Properties properties) {
        return new RangedWeapon(
                maxDamage,
                70,
                6.0f,
                1.0f,
                props());
    }
}