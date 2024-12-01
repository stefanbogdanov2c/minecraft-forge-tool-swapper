package com.example.toolmod;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.network.chat.Component;

public class ToolBreakHandler {

    public ToolBreakHandler() {
      MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onToolBreak(PlayerDestroyItemEvent event) {
      // Ensure this only happens for players
      if (!(event.getEntity() instanceof ServerPlayer player)) return;

      // Check if the broken item was a tool
      ItemStack brokenItem = event.getOriginal();
      if (!brokenItem.isDamageableItem()) return;

      // Find a replacement tool in the player's inventory
      ItemStack replacement = findReplacementTool(player, brokenItem);
      if (replacement != null) {
        // Replace the broken tool
        player.getInventory().removeItem(replacement);
        player.setItemInHand(InteractionHand.MAIN_HAND, replacement);
        player.displayClientMessage(Component.literal("Your tool has been replaced automatically!"), true);
      }
    }

    private ItemStack findReplacementTool(ServerPlayer player, ItemStack brokenItem) {
      // Iterate through the player's inventory
      for (ItemStack stack : player.getInventory().items) {
          if (!stack.isEmpty() && stack.getItem() == brokenItem.getItem()) {
              return stack;
          }
      }
      return null; // No replacement found
    }
}
