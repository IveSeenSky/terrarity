package com.example.terrarity;

import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;

import java.util.Map;

public interface IModifiableItem {
    ModdedRarity getRarity();
    void setRarity(ModdedRarity rarity);
    Map<String, Double> getModifiers(); // String - название модификатора, Double - значение
    void setModifiers(Map<String, Double> modifiers);

    // Метод для получения локализованного названия предмета с учетом редкости
    MutableText getDisplayName(ItemStack stack);
}
