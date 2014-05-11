package mariculture.core.handlers;

import mariculture.core.lib.Modules;
import mariculture.fishery.Fish;
import mariculture.fishery.Fishery;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {
	@Override
	public int getBurnTime(ItemStack fuel) {
		if (Modules.isActive(Modules.fishery)) {
			if (fuel.itemID == Fishery.fishyFood.itemID) {
				int speciesID = fuel.getItemDamage();
				if (fuel.getItemDamage() == Fish.nether.getID()) {
					return 2500;
				}
			}
		}

		return 0;
	}
}
