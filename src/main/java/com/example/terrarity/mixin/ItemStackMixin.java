package com.example.terrarity.mixin;

import com.example.terrarity.rarities.RarityManager;
import net.minecraft.item.*;

import java.util.*;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.example.terrarity.rarities.Rarity;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V", at = @At("RETURN"))
    private void onConstructed(CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.hasNbt() && stack.getNbt().contains("RarityData")) return;

        applyRarity(stack);
    }

    @Unique
    private void applyRarity(ItemStack stack) {
        Rarity rarity = RarityManager.getRandomRarity((java.util.Random) Random.create()); //При бедах стирать к хуям
        RarityManager.Modifier modifier = RarityManager.getModifier(getItemType(stack.getItem()), rarity)
                .orElse(null);

        NbtCompound nbt = stack.getOrCreateNbt();
        NbtCompound rarityData = new NbtCompound();

        rarityData.putString("rarity", rarity.asString());
        if (modifier != null) {
            rarityData.putString("modifier", modifier.config().toString());
        }

        nbt.put("RarityData", rarityData);
    }

    @Unique
    private String getItemType(Item thisItem) {
        return switch (thisItem) {
            case ArmorItem armorItem -> "ArmorItem";
            case AxeItem axeItem -> "AxeItem";
            case PickaxeItem pickaxeItem -> "PickaxeItem";
            case HoeItem hoeItem -> "HoeItem";
            case ShovelItem shovelItem -> "ShovelItem";
            case FishingRodItem fishingRodItem -> "FishingRodItem";
            case SwordItem swordItem -> "SwordItem";
            case CrossbowItem crossbowItem -> "CrossbowItem";
            case BowItem bowItem -> "BowItem";
            case null, default -> "OTHER";
        };
    }
}
