package com.example.terrarity;

import com.example.terrarity.handlers.DamageHandler;
import com.example.terrarity.handlers.TooltipHandler;
import com.example.terrarity.rarities.RarityManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class Terrarity implements ModInitializer {
	@Override
	public void onInitialize() {
		// Регистрация системы редкостей
		ResourceManagerHelper.get(ResourceType.SERVER_DATA)
				.registerReloadListener(new RarityManager());

		// Регистрация обработчика тултипов
		ItemTooltipCallback.EVENT.register(TooltipHandler::onTooltip);

		// Регистрация обработчика урона
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			float newAmount = DamageHandler.modifyDamage(amount, source, entity);
			return newAmount != amount;
		});
	}
}
