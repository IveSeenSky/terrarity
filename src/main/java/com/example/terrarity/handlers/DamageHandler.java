package com.example.terrarity.handlers;

import com.example.terrarity.rarities.Rarity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;

public class DamageHandler {
    public static float modifyDamage(float amount, DamageSource source, LivingEntity target) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) return amount;

        ItemStack weapon = attacker.getMainHandStack();
        if (!weapon.hasNbt()) return amount;

        assert weapon.getNbt() != null;
        NbtCompound rarityData = weapon.getNbt().getCompound("RarityData");
        Rarity rarity = Rarity.byId(rarityData.getString("rarity"));

        double critChance = getCritChance(rarityData);
        if (Random.create().nextDouble() < critChance) {
            amount *= (float) getCritMultiplier(rarityData);
        }

        return (float) (amount * getDamageMultiplier(rarityData));
    }

    private static double getCritChance(NbtCompound data) {
        if (!data.contains("modifier")) return 0.0;
        JsonObject modifier = parseModifier(data);
        return modifier.get("crit_chance").getAsDouble();
    }

    private static double getCritMultiplier(NbtCompound data) {
        if (!data.contains("modifier")) return 1.5;
        JsonObject modifier = parseModifier(data);
        return modifier.get("crit_multiplier").getAsDouble();
    }

    private static double getDamageMultiplier(NbtCompound data) {
        if (!data.contains("modifier")) return 1.0;
        JsonObject modifier = parseModifier(data);
        return modifier.get("damage").getAsDouble();
    }

    private static JsonObject parseModifier(NbtCompound data) {
        return JsonParser.parseString(data.getString("modifier")).getAsJsonObject();
    }
}
