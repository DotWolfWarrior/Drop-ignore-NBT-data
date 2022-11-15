package main;

import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
// import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;



import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;


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
                // List<ItemStack> l = new ArrayList<>();
                // List<Integer> l = new ArrayList<>();

                
                // ItemStack item = mc.player.getStackInHand(client.player.getActiveHand());
                // ItemStack item = mc.player.playerScreenHandler.getCursorStack();
                // ItemStack item = inv.getStack(inv.selectedSlot);
                ItemStack item = mc.player.playerScreenHandler.

                // mc.player.getInventory().selectedSlot;
                if (item.getItem().toString() != "air"){
                    for (int i = 0; i < inv.size(); i++) {
                        var slotId = inv.getStack(i);
                        if (item.getItem() == slotId.getItem()){
                            t += slotId+":"+inv.getStack(i).toString()+", \n";
                            mc.interactionManager.clickSlot(syncId, i, 1, SlotActionType.THROW, mc.player);
                        }

                    }
                    // client.player.sendMessage(Text.literal(l.toString()), false);

                }
                client.player.sendMessage(Text.literal(t), false);

                client.player.sendMessage(Text.literal("Key was pressed"), false);


                

            };
        });
    }
}

// public void swapSlots(int sourceSlot, int destSlot){
//     int syncId = MinecraftClient.getInstance().player.playerScreenHandler.syncId;
//     MinecraftClient.getInstance().interactionManager.clickSlot(syncId, destSlot, sourceSlot, SlotActionType.SWAP, MinecraftClient.getInstance().player);
//     }
