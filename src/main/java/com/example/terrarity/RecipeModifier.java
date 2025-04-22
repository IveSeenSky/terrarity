package com.example.terrarity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.*;
import net.minecraft.recipe.*;

import java.util.*;

/*
public class RecipeModifier {
    private static final Random RANDOM = new Random();

    // Массив говна (Вес > Тип)
    private static Map<Double, ModdedRarity> rarities;
    private static double totalWeight = 0;

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            RecipeManager recipeManager = server.getRecipeManager();
        });

        // Заполнение массива говна (0 = 0%)
        rarities.put(0.0,     ModdedRarity.CURSED);
        rarities.put(1.0/500.0,    ModdedRarity.COMMON);
        rarities.put(1.0/1000.0,    ModdedRarity.UNCOMMON);
        rarities.put(1.0/1500.0,    ModdedRarity.RARE);
        rarities.put(1.0/2000.0,    ModdedRarity.EPIC);
        rarities.put(1.0/2500.0,    ModdedRarity.LEGENDARY);
        rarities.put(1.0/5000.0,   ModdedRarity.MYTHIC);

        for (var item : rarities.entrySet()) {
            totalWeight += item.getKey();
        }
    }

    private static ModdedRarity determineRarity() {
        ModdedRarity result = null;
        double random = RANDOM.nextInt() * totalWeight;
        double cWeight = 0;

        for (var item : rarities.entrySet()) {
            cWeight = cWeight + item.getKey();
            if (random <= cWeight)
                result = item.getValue();
        }

        return result != null ? result : ModdedRarity.COMMON;
    }

    private static Map<String, Double> generateModifiers(ModdedRarity rarity, Item item) {
        Map<String, Double> modifiers = new HashMap<>();

        switch (item) {
            case SwordItem swordItem -> {
                // Sword
                modifiers.put("damage", 1.0 + (rarity.ordinal() * 0.2));
                modifiers.put("durability", 1.0 + (rarity.ordinal() * 0.1));

                modifiers.put("attack_speed", 0.0 + (rarity.ordinal() * 0.05));
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
*/