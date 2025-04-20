package com.example.terrarity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeModifier {
    private static final Random RANDOM = new Random();

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            RecipeManager recipeManager = server.getRecipeManager();
        });
    }

    private static Rarity determineRarity() {
        double chance = RANDOM.nextDouble();
        if (chance < 0.01) {
            return Rarity.MYTHIC;
        } else if (chance < 0.05) {
            return Rarity.LEGENDARY;
        } else if (chance < 0.15) {
            return Rarity.EPIC;
        } else if (chance < 0.30) {
            return Rarity.RARE;
        } else if (chance < 0.50) {
            return Rarity.UNCOMMON;
        } else if (chance < 0.75) {
            return Rarity.COMMON;
        } else {
            return Rarity.CURSED;
        }
    }

    private static Map<String, Double> generateModifiers(Rarity rarity, Item item) {
        Map<String, Double> modifiers = new HashMap<>();

        switch (item) {
            case SwordItem swordItem -> {
                // Sword
                modifiers.put("damage", 1.0 + (rarity.ordinal() * 0.2));
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

                modifiers.put("speed", 0.0 + (rarity.ordinal() * 0.05));
                modifiers.put("size", 0.0 + (rarity.ordinal() * 0.1));
                modifiers.put("critical_chance", 0.05 + (rarity.ordinal() * 0.02));

            }
            case BowItem bowItem -> {
                // Bow
                modifiers.put("damage", 1.0 + (rarity.ordinal() * 0.2));
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

                //modifiers.put("projectile_speed", 1.0 + (rarity.ordinal() * 0.05));
                //modifiers.put("reload_speed", 1.0 - (rarity.ordinal() * 0.02));
                //modifiers.put("critical_chance", 0.05 + (rarity.ordinal() * 0.02));

            }
            case CrossbowItem crossbowItem -> {
                // Crossbow
                modifiers.put("damage", 1.0 + (rarity.ordinal() * 0.2));
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

                //modifiers.put("projectile_speed", 1.0 + (rarity.ordinal() * 0.08));
                //modifiers.put("reload_speed", 1.0 - (rarity.ordinal() * 0.05));
                //modifiers.put("critical_chance", 0.05 + (rarity.ordinal() * 0.03));

            }
            case ArmorItem armorItem -> {
                // Armor
                modifiers.put("damage", 1.0 + (rarity.ordinal() * 0.2));
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

            }
            case ShieldItem shieldItem -> {
                // Shield
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

            }
            case TridentItem tridentItem -> {
                // Trident
                double damageModifier = 1.0 + (rarity.ordinal() * 0.25);
                modifiers.put("damage", damageModifier);
                double durabilityModifier = 1.0 + (rarity.ordinal() * 0.12);
                modifiers.put("durability", durabilityModifier);

                //modifiers.put("projectile_speed", 1.0 + (rarity.ordinal() * 0.07));
                // modifiers.put("reload_speed", 1.0 - (rarity.ordinal() * 0.03));

            }
            case ToolItem toolItem -> {
                // Tools (Axe, Pickaxe, Shovel, Hoe)
                double damageModifier = 1.0 + (rarity.ordinal() * 0.1);
                modifiers.put("damage", damageModifier);
                double durabilityModifier = 1.0 + (rarity.ordinal() * 0.1);
                modifiers.put("durability", durabilityModifier);

                //modifiers.put("digging_speed", 1.0 + (rarity.ordinal() * 0.15));
                //modifiers.put("fortune", 1.0 + (rarity.ordinal() * 0.05));

            }
            default -> System.out.println("Unknown item type: " + item.toString());
        }
        return modifiers;
    }
}
