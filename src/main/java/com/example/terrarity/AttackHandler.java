package com.example.terrarity;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;

public class AttackHandler {
    public static void register() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.hasNbt()) {
                NbtCompound nbt = stack.getNbt();
                if (nbt.contains("CritChance")) {
                    double chance = nbt.getDouble("CritChance");
                    if (Math.random() < chance) {
                        // Применяем критический урон
                        player.addCritParticles(entity);
                        entity.damage(world.getDamageSources().playerAttack(player), getCritDamage(stack));
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}