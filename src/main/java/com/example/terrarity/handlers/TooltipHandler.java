package com.example.terrarity.handlers;

import com.example.terrarity.rarities.Rarity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class TooltipHandler {
    public static void onTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip) {
        if (!stack.hasNbt() || !stack.getNbt().contains("RarityData")) return;

        NbtCompound rarityData = stack.getNbt().getCompound("RarityData");
        Rarity rarity = Rarity.byId(rarityData.getString("rarity"));

        MutableText rarityText = Text.literal(rarity.getDisplayName())
                .formatted(rarity.getFormatting());
        tooltip.add(Text.literal("Rarity: ").append(rarityText));

        if (rarityData.contains("modifier")) {
            JsonObject modifier = JsonParser.parseString(rarityData.getString("modifier")).getAsJsonObject();
            modifier.keySet().forEach(key -> {
                double value = modifier.get(key).getAsDouble();
                Text line = Text.literal(String.format("%s: %+.0f%%",
                                Util.createTranslationKey("modifier", new Identifier(key)),
                                (value - 1.0) * 100))
                        .formatted(Formatting.GRAY);
                tooltip.add(line);
            });
        }
    }
}
