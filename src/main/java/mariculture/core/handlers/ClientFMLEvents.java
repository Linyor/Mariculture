package mariculture.core.handlers;

import mariculture.Mariculture;
import mariculture.core.helpers.ClientHelper;
import mariculture.core.helpers.EnchantHelper;
import mariculture.core.helpers.PlayerHelper;
import mariculture.core.lib.ArmorSlot;
import mariculture.core.lib.Modules;
import mariculture.core.network.PacketJewelrySwap;
import mariculture.factory.Factory;
import mariculture.magic.Magic;
import mariculture.magic.enchantments.EnchantmentFlight;
import mariculture.magic.jewelry.ItemJewelry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClientFMLEvents {
	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		EntityPlayer player = ClientHelper.getPlayer();
		ItemStack selected = ClientHelper.getHeldItem();
		if(selected != null && selected.getItem() instanceof ItemJewelry && ClientHelper.isActivateKeyPressed()) {
			Mariculture.packets.sendToServer(new PacketJewelrySwap(ClientHelper.getPlayer().inventory.currentItem));
		}
		
		if(Modules.factory.isActive()) {
			if(ClientHelper.isActivateKeyPressed() && player.isSneaking() && PlayerHelper.hasArmor(player, ArmorSlot.TOP, Factory.fludd)) {
				ItemStack stack = PlayerHelper.getArmorStack(player, ArmorSlot.TOP);
				if(stack.hasTagCompound()) {
					int mode = stack.stackTagCompound.getInteger("mode");
					mode++;
					if (mode > 3) {
						mode = 0;
					}

					ClientHelper.addToChat(StatCollector.translateToLocal("mariculture.string.fludd.mode." + mode));
					stack.stackTagCompound.setInteger("mode", mode);
				} else {
					stack.setTagCompound(new NBTTagCompound());
				}
			}
		}
		
		if(Modules.magic.isActive()) {
			if(ClientHelper.isActivateKeyPressed() && ClientHelper.isToggleKeyPressed() && player.capabilities.isFlying && EnchantHelper.hasEnchantment(Magic.flight, player)) {
				if(EnchantmentFlight.mode < EnchantmentFlight.maxMode) {
					EnchantmentFlight.mode++;
				} else {
					EnchantmentFlight.mode = 0;
				}
				
				ClientHelper.addToChat(StatCollector.translateToLocal("mariculture.string.flight") + (EnchantmentFlight.mode + 1));
				EnchantmentFlight.set(EnchantHelper.getEnchantStrength(Magic.flight, player), player);
			}
		}
	}
}