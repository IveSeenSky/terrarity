package com.example.terrarity.rarities;

import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

public enum Rarity implements StringIdentifiable {
    CURSED("Cursed", "CURSED", Formatting.DARK_GRAY, 0),
    COMMON("common", "COMMON", Formatting.WHITE, 50),
    UNCOMMON("uncommon", "UNCOMMON", Formatting.YELLOW, 25),
    RARE("rare", "RARE", Formatting.BLUE, 15),
    EPIC("epic", "EPIC", Formatting.DARK_PURPLE, 7),
    LEGENDARY("legendary", "LEGENDARY", Formatting.GOLD, 3),
    MYTHIC("mythic", "MYTHIC", Formatting.RED, 1);

    private final String id;
    private final String displayName;
    private final Formatting formatting;
    private final int weight;

    Rarity(String id, String displayName, Formatting formatting, int weight) {
        this.id = id;
        this.displayName = displayName;
        this.formatting = formatting;
        this.weight = weight;
    }

    @Override
    public String asString() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Formatting getFormatting() {
        return this.formatting;
    }

    public int getWeight() {
        return this.weight;
    }

    public static Rarity byId(String id) {
        for (Rarity rarity : values()) {
            if (rarity.id.equals(id)) {
                return rarity;
            }
        }
        return COMMON;
    }
}
