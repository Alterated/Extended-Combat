package net.Alterated.mods.extended_combat.item.registry.WeaponTypes;

import net.minecraft.world.item.Item;
import static net.Alterated.mods.extended_combat.Extendedcombat.props;

/**
 * This Class holds all the static methods from
 * the standard MeleeWeapon and RangedWeapon classes
 *
 * @author PDP1
 */

public class ModItem {

    /**
     * Works as an empty constructor
     * lets the class keep valid syntax
     */
    private ModItem() {}

    /**
     * The Static Method Used to Register The Knuckles Easier and cleanly
     * called mainly in extended_combat.item.init.ModItems
     *
     * @param maxDamage The Durability of the weapon
     * @param attackDamage The Base Attack Damage The Weapon Ends Up Doing
     * @param attackSpeed The Attack Speed of the weapon (eg. 1.6 for swords, 4 being the default for punches)
     * @param properties Acts as an ending piece which allows extra properties to be set in the calling class
     * @return returns a new MeleeWeapon to use that constructor in MeleeWeapon
     */
    public static MeleeWeapon knuckles(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new MeleeWeapon(
                0,
                maxDamage,
                attackDamage,
                attackSpeed,
                0.05,
                false,
                false,
                false,
                false,
                props()
        );
    }

    /**
     * The Static Method Used to Register The Naginata Easier and cleanly
     * called mainly in extended_combat.item.init.ModItems
     *
     * @param maxDamage The Durability of the weapon
     * @param attackDamage The Base Attack Damage The Weapon Ends Up Doing
     * @param attackSpeed The Attack Speed of the weapon (eg. 1.6 for swords, 4 being the default for punches)
     * @param properties Acts as an ending piece which allows extra properties to be set in the calling class
     * @return returns a new MeleeWeapon to use that constructor in MeleeWeapon
     */
    public static MeleeWeapon naginata(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new MeleeWeapon(
                1,
                maxDamage,
                attackDamage,
                attackSpeed,
                -0.05,
                true,
                true,
                true,
                false,
                props()
        );
    }

    /**
     * The Static Method Used to Register The Throwing Axe Easier and cleanly
     * called mainly in extended_combat.item.init.ModItems
     *
     * @param maxDamage The Durability of the weapon
     * @param attackDamage The Base Attack Damage The Weapon Ends Up Doing
     * @param attackSpeed The Attack Speed of the weapon (eg. 1.6 for swords, 4 being the default for punches)
     * @param properties Acts as an ending piece which allows extra properties to be set in the calling class
     * @return returns a new MeleeWeapon to use that constructor in MeleeWeapon
     */
    public static MeleeWeapon throwing_axe(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new MeleeWeapon(
                3,
                maxDamage,
                attackDamage,
                attackSpeed,
                0,
                false,
                false,
                false,
                true,
                props()
        );
    }

    /**
     * The Static Method Used to Register The Longbow Easier and cleanly
     * called mainly in extended_combat.item.init.ModItems
     *
     * @param maxDamage The Durability of the Longbow
     * @param properties Acts as an ending piece which allows extra properties to be set in the calling class
     * @return returns a new RangedWeapon to use that constructor in RangedWeapon
     */
    public static RangedWeapon longbow(int maxDamage, Item.Properties properties) {
        return new RangedWeapon(
                maxDamage,
                70,
                6.0f,
                1.0f,
                props()
        );
    }
}