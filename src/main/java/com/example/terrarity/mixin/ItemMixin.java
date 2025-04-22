package com.example.terrarity.mixin;

import com.example.terrarity.IRarity;
import com.example.terrarity.ModdedRarity;
import com.example.terrarity.Terrarity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Dictionary;

// WIP
// Дополнение исходного кода предмета для добавления реализации
// редкостей
@Mixin(Item.class)
public class ItemMixin {

    public ModdedRarity rarity = null;
    // временный костыль
    @Unique
    public String type = null;
    @Unique
    Item thisItem;

    //Определение типа предмета (только тип может иметь редкость)
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void invTick(CallbackInfo c) {
        thisItem = (Item)(Object)this;
        if (type == null) {
            // Это пиздец но я не знаю как сделать по другому
            // Ебаный ООП
            if (thisItem instanceof ArmorItem) {
                type = "ArmorItem";
            } else if (thisItem instanceof AxeItem) {
                type = "AxeItem";
            } else if (thisItem instanceof PickaxeItem) {
                type = "PickaxeItem";
            } else if (thisItem instanceof HoeItem) {
                type = "HoeItem";
            } else if (thisItem instanceof ShovelItem) {
                type = "ShovelItem";
            } else if (thisItem instanceof FishingRodItem) {
                type = "FishingRodItem";
            } else if (thisItem instanceof SwordItem) {
                type = "SwordItem";
            } else if (thisItem instanceof CrossbowItem) {
                type = "CrossbowItem";
            } else if (thisItem instanceof BowItem) {
                type = "BowItem";
            }
        }
        if (type != null)
            Terrarity.LOGGER.info(type);
    }
}
