package com.example.terrarity;

import net.minecraft.util.Formatting;

public enum Rarity {
    CURSED("Cursed", Formatting.DARK_GRAY),
    COMMON("Common", Formatting.WHITE),
    UNCOMMON("Uncommon", Formatting.YELLOW),
    RARE("Rare", Formatting.BLUE),
    EPIC("Epic", Formatting.DARK_PURPLE),
    LEGENDARY("Legendary", Formatting.GOLD),
    MYTHIC("Mythic", Formatting.RED);

    private final String name;
    private final Formatting color;

    Rarity(String name, Formatting color) {
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
