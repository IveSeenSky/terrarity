package com.example.terrarity.rarities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class RarityManager implements SimpleSynchronousResourceReloadListener {
    private static Random random;
    private static final Map<String, Map<Rarity, Map<String, Modifier>>> MODIFIERS = new HashMap<>();
    private static final List<Rarity> RARITY_POOL = new ArrayList<>();

    @Override
    public Identifier getFabricId() {
        return new Identifier("terrarity", "rarity_manager");
    }

    @Override
    public void reload(ResourceManager manager) {
        MODIFIERS.clear();
        RARITY_POOL.clear();

        manager.findResources("rarities", id -> id.getPath().endsWith(".json")).forEach((id, resource) -> {
            try (InputStream is = resource.getInputStream()) {
                JsonObject config = JsonParser.parseReader(new InputStreamReader(is)).getAsJsonObject();
                loadRarities(config.getAsJsonObject("rarities"));
                loadModifiers(config.getAsJsonObject("modifiers"));
            } catch (Exception e) {
                throw new RuntimeException("Failed to load rarity config: " + id, e);
            }
        });
    }

    private void loadRarities(JsonObject rarities) {
        rarities.keySet().forEach(rarityId -> {
            JsonObject rarityConfig = rarities.getAsJsonObject(rarityId);
            Rarity rarity = Rarity.byId(rarityId);
            int weight = rarityConfig.get("weight").getAsInt();
            for (int i = 0; i < weight; i++) {
                RARITY_POOL.add(rarity);
            }
        });
    }

    private void loadModifiers(JsonObject modifiers) {
        modifiers.keySet().forEach(itemType -> {
            Map<Rarity, Map<String, Modifier>> rarityMap = new EnumMap<>(Rarity.class);
            JsonObject typeModifiers = modifiers.getAsJsonObject(itemType);

            typeModifiers.keySet().forEach(rarityId -> {
                Rarity rarity = Rarity.byId(rarityId);
                JsonObject rarityModifiers = typeModifiers.getAsJsonObject(rarityId);
                Map<String, Modifier> modifierMap = new HashMap<>();

                rarityModifiers.keySet().forEach(modifierName -> {
                    JsonObject modifierConfig = rarityModifiers.getAsJsonObject(modifierName);
                    Modifier modifier = new Modifier(modifierConfig);
                    modifierMap.put(modifierName, modifier);
                });

                rarityMap.put(rarity, modifierMap);
            });

            MODIFIERS.put(itemType.toUpperCase(), rarityMap);
        });
    }

    public static Rarity getRandomRarity(Random random) {
        if (RARITY_POOL.isEmpty()) return Rarity.COMMON;
        return RARITY_POOL.get(random.nextInt(RARITY_POOL.size()));
    }

    public static Optional<Modifier> getModifier(String itemType, Rarity rarity) {
        Map<String, Modifier> modifiers = MODIFIERS.getOrDefault(itemType, Collections.emptyMap())
                .getOrDefault(rarity, Collections.emptyMap());

        if (modifiers.isEmpty()) return Optional.empty();
        List<String> keys = new ArrayList<>(modifiers.keySet());
        return Optional.of(modifiers.get(keys.get(random.nextInt(keys.size()))));
    }

    public record Modifier(JsonObject config) {
        public double getValue(String key, double defaultValue) {
            return config.has(key) ? config.get(key).getAsDouble() : defaultValue;
        }
    }
}
