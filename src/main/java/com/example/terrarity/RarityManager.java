package com.example.terrarity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RarityManager {
    private static RarityModifiers rarityModifiers;
    private static Random random;

    private static final Map<Rarity, Integer> DEFAULT_WEIGHTS = Map.of(
            Rarity.COMMON, 50,
            Rarity.UNCOMMON, 25,
            Rarity.RARE, 15,
            Rarity.EPIC, 7,
            Rarity.LEGENDARY, 2,
            Rarity.MYTHIC, 1
    );

    public static Rarity getRandomRarity() {
        List<Rarity> rarities = new ArrayList<>();

        for (Map.Entry<Rarity, Integer> entry : DEFAULT_WEIGHTS.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                rarities.add(entry.getKey());
            }
        }

        return rarities.get(random.nextInt(rarities.size()));
    }

    public static void loadConfig() {
        try {
            File configFile = new File("./src/mai");
            Gson gson = new Gson();
            rarityModifiers = gson.fromJson(new FileReader(configFile), RarityModifiers.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RarityModifiers getModifiers() {
        return rarityModifiers;
    }
}

class RarityModifiers {
    public Map<String, Map<String, Map<String, Map<String, Double>>>> rarity_modifiers;
}
