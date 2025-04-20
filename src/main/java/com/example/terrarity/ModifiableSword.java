package com.example.terrarity;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import java.util.HashMap;
import java.util.Map;

public class ModifiableSword extends SwordItem implements IModifiableItem {

    private Rarity rarity = Rarity.COMMON;
    private Map<String, Double> modifiers = new HashMap<>();

    public ModifiableSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public Rarity getRarity(){
        return rarity;
    }

    @Override
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public Map<String, Double> getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(Map<String, Double> modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public MutableText getDisplayName(ItemStack stack) {
        MutableText name = super.getName().copy();
        name.formatted(getRarity().getColor());
        return Text.translatable("item." + this.getTranslationKey() + ".rarity_prefix", getRarity().getName()).append(name);
    }

    @Override
    public Text getName() {
        return getDisplayName(getDefaultStack());
    }
}
