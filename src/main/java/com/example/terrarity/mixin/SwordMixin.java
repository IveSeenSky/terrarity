package com.example.terrarity.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Item.class)
public class SwordMixin {
    @Inject(at = @At("Head"))
    private void init() {

    }
}
