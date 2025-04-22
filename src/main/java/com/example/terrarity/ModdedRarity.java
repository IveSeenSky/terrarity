package com.example.terrarity;

import net.minecraft.item.*;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

// Поменял название (Rarity -> ModdedRarity) потому что
// в API присутствует класс Rarity отвечающий за цвет
// предмета
public enum ModdedRarity {
    CURSED("Cursed", Formatting.DARK_GRAY),
    COMMON("Common", Formatting.WHITE),
    UNCOMMON("Uncommon", Formatting.YELLOW),
    RARE("Rare", Formatting.BLUE),
    EPIC("Epic", Formatting.DARK_PURPLE),
    LEGENDARY("Legendary", Formatting.GOLD),
    MYTHIC("Mythic", Formatting.RED);

    private final String name;
    private final Formatting color;

    // no use
    // Лист для определения предметов которые могут
    // иметь редкость
    //public static final List<Class<? extends Item>> classes = new ArrayList<Class<? extends Item>>() {
    //    {
    //        add(AxeItem.class);
    //        add(PickaxeItem.class);
    //        add(HoeItem.class);
    //        add(ShovelItem.class);
    //        add(FishingRodItem.class);

    //        add(ArmorItem.class);

    //        add(SwordItem.class);
    //        add(CrossbowItem.class);
    //        add(BowItem.class);
    //    }
    //};

    ModdedRarity(String name, Formatting color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Formatting getColor() {
        return color;
    }
}
