package main;

import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
// import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;



import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;

// import java.util.ArrayList;
// import java.util.List;

import org.lwjgl.glfw.GLFW;

public class client implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        registerKeyInputs();
    }
    public static final String KeyCategory = "DINBT";

    // private static KeyBinding kb = KeyBindingHelper.registerKeyBinding(new KeyBinding("Drop",InputUtil.Type.KEYSYM,GLFW.GLFW_KEY_K,KeyCategory));
    private static MinecraftClient mc;

    
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            mc = MinecraftClient.getInstance();
            if (InputUtil.isKeyPressed(mc.getWindow().getHandle(), GLFW.GLFW_KEY_K)) {
            // if (kb.wasPressed()) {
                var t = "";
                PlayerInventory inv = client.player.getInventory();
                var syncId = mc.player.playerScreenHandler.syncId;
                var slots = mc.player.playerScreenHandler.getStacks();
                // var a = mc.player.playerScreenHandler.
                // var cursor = mc.targetedEntity.

                
                // ItemStack item = mc.player.getStackInHand(client.player.getActiveHand());
                // ItemStack item = mc.player.playerScreenHandler.getCursorStack();
                ItemStack item = inv.getStack(inv.selectedSlot);

                // mc.player.getInventory().selectedSlot;
                if (item.getItem().toString() != "air"){
                    for (int i = 0; i < slots.size(); i++) {
                        var slotId = slots.get(i);
                        if (item.getItem() == slotId.getItem()){
                            t += slotId+":"+slots.get(i).toString()+", \n";
                            mc.interactionManager.clickSlot(syncId, i, 1, SlotActionType.THROW, mc.player);
                        }

                    }
                    // client.player.sendMessage(Text.literal(l.toString()), false);

                }
                client.player.sendMessage(Text.literal(t), false);

                client.player.sendMessage(Text.literal(slots.size()+""), false);


                

            };
        });
    }
}

// public void swapSlots(int sourceSlot, int destSlot){
//     int syncId = MinecraftClient.getInstance().player.playerScreenHandler.syncId;
//     MinecraftClient.getInstance().interactionManager.clickSlot(syncId, destSlot, sourceSlot, SlotActionType.SWAP, MinecraftClient.getInstance().player);
//     }
