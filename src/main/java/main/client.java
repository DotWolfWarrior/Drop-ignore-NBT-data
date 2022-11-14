package main;

import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;



import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

public class client implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        registerKeyInputs();
    }
    public static final String KeyCategory = "DINBT";

    private static KeyBinding kb = KeyBindingHelper.registerKeyBinding(new KeyBinding("Drop",InputUtil.Type.KEYSYM,GLFW.GLFW_KEY_K,KeyCategory));
    private static MinecraftClient mc;

    
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            mc = client.getInstance();
            if (kb.wasPressed()) {
                var t = "";
                PlayerInventory inv = client.player.getInventory();
                var syncId = mc.player.playerScreenHandler.syncId;
                // List<ItemStack> l = new ArrayList<>();
                List<Integer> l = new ArrayList<>();

                
                ItemStack item = mc.player.getStackInHand(client.player.getActiveHand());
                // if (item.getItem() != "air"){

                // }

                for (int i = 0; i < inv.size(); i++){
                    if (item.getItem() == inv.getStack(i).getItem()){
                        l.add(i);
                        mc.interactionManager.clickSlot(syncId, i, 1, SlotActionType.THROW, mc.player);
                        // client.player.dropStack(inv.getStack(i));

                        // t += inv.getStack(i).getItem()+", ";
                    }
                    
                }
                // l.forEach(it -> {
                //     mc.interactionManager.clickSlot(syncId, inv.getSlotWithStack(it), 1, SlotActionType.THROW, mc.player);
                //     // client.interactionManager.clickSlot(0, 0, 0, null, null);
                // });
                client.player.sendMessage(Text.literal(l.toString()), false);

            }
        });
    }
}

// public void swapSlots(int sourceSlot, int destSlot){
//     int syncId = MinecraftClient.getInstance().player.playerScreenHandler.syncId;
//     MinecraftClient.getInstance().interactionManager.clickSlot(syncId, destSlot, sourceSlot, SlotActionType.SWAP, MinecraftClient.getInstance().player);
//     }
