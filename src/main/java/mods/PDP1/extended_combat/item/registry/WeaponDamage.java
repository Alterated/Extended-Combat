package mods.PDP1.extended_combat.item.registry;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import java.util.UUID;

import static mods.PDP1.extended_combat.Extendedcombat.props;

public class WeaponDamage extends Item {
    private static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    private static final UUID MAINHAND_SPEED_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private static final UUID OFFHAND_SPEED_ID = UUID.fromString("987fc234-a12b-34c5-d678-012213141516");
    private static final UUID WEAPON_REACH_UUID = UUID.fromString("df2f3297-d376-4cce-b8d6-ae9faaa98ce3");

    private final double attackDamage;
    private final double attackSpeed;
    private final double movementSpeedModifier;
    private final double attackReach;

    private WeaponDamage(double attackReach, int maxDamage, int attackDamage, double attackSpeed, double movementSpeedmultiplier, Item.Properties properties) {
        // Sets the max durability on the properties object
        super(properties.durability(maxDamage));

        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.movementSpeedModifier = movementSpeedmultiplier;
        this.attackReach = attackReach;
    }

    public static WeaponDamage knuckles(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new WeaponDamage(
                3,
                maxDamage,
                attackDamage,
                attackSpeed,
                0.05,
                props()
        );
    }

    public static WeaponDamage naginata(int maxDamage, int attackDamage, double attackSpeed, Item.Properties properties) {
        return new WeaponDamage(
                4.5,
                maxDamage,
                attackDamage,
                attackSpeed,
                -0.15,
                props()
        );
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity attacker) {
        if (state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(2, attacker, (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            BASE_ATTACK_DAMAGE_UUID,
                            "Weapon modifier",
                            this.attackDamage,
                            AttributeModifier.Operation.ADDITION
            ));

            builder.put(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                            BASE_ATTACK_SPEED_UUID,
                            "Weapon modifier",
                            this.attackSpeed - 4.0D,
                            AttributeModifier.Operation.ADDITION
            ));

            builder.put(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(
                            MAINHAND_SPEED_ID,
                            "Mainhand Speed",
                            this.movementSpeedModifier,
                            AttributeModifier.Operation.MULTIPLY_BASE
            ));

            builder.put(
                    ForgeMod.ENTITY_REACH.get(),
                    new AttributeModifier(
                            WEAPON_REACH_UUID,
                            "Mainhand Reach",
                            this.attackReach - 3.0,
                            AttributeModifier.Operation.ADDITION
                    )
            );
        }

        if (slot == EquipmentSlot.OFFHAND) {
            builder.put(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(
                            OFFHAND_SPEED_ID,
                            "Offhand Speed",
                            this.movementSpeedModifier,
                            AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }

        return builder.build();
    }
}