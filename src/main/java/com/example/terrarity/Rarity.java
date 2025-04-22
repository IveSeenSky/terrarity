package com.example.terrarity;

import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public enum Rarity {
    CURSED("Cursed", Formatting.DARK_GRAY),
    COMMON("Common", Formatting.WHITE),
    UNCOMMON("Uncommon", Formatting.YELLOW),
    RARE("Rare", Formatting.BLUE),
    EPIC("Epic", Formatting.DARK_PURPLE),
    LEGENDARY("Legendary", Formatting.GOLD),
    MYTHIC("Mythic", Formatting.RED);

    private final String displayName;
    private final Formatting formatting;

    Rarity(String displayName, Formatting formatting) {
        this.displayName = displayName;
        this.formatting = formatting;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Formatting getFormatting() {
        return this.formatting;
    }

    @Nullable
    public static Rarity fromString(String name) {
        for (Rarity rarity : values()) {
            if (rarity.displayName.equalsIgnoreCase(name)) {
                return rarity;
            }
        }
        return null;
    }

    public static Rarity getDefault() {
        return COMMON;
    }

    // Json плачет без этого
    public String toSnakeCase() {
        return this.name().toLowerCase();
    }
}
