package mariculture.transport;

import mariculture.Mariculture;
import mariculture.core.Core;
import mariculture.core.helpers.RecipeHelper;
import mariculture.core.helpers.RegistryHelper;
import mariculture.core.lib.CraftingMeta;
import mariculture.core.lib.EntityIds;
import mariculture.core.lib.ItemIds;
import mariculture.core.lib.Modules.RegistrationModule;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Transport extends RegistrationModule {
	public static Item speedBoat;
	@Override
	public void registerHandlers() {
		return;
	}

	@Override
	public void registerBlocks() {
		return;
	}

	@Override
	public void registerItems() {
		speedBoat = new ItemSpeedBoat(ItemIds.speedBoat).setUnlocalizedName("speedBoat");
		RegistryHelper.register(new Object[] { speedBoat });
	}
	
	@Override
	public void registerOther() {
		EntityRegistry.registerModEntity(EntitySpeedBoat.class, "speedBoat", EntityIds.SPEED_BOAT, Mariculture.instance, 250, 5, false);
	}

	@Override
	public void registerRecipes() {
		RecipeHelper.addShapedRecipe(new ItemStack(speedBoat), new Object[] {
			"G F", "AAA", 'G', Block.thinGlass, 'F', new ItemStack(Core.crafting, 1, CraftingMeta.COOLER), 'A', "ingotAluminum"
		});
	}
}
