package mariculture.fishery.items;

import mariculture.Mariculture;
import mariculture.api.core.MaricultureTab;
import mariculture.api.fishery.ItemBaseRod;
import mariculture.api.fishery.RodQuality;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRod extends ItemBaseRod {
	public ItemRod(int i, RodQuality quality) {
		super(i, quality);
		this.setMaxStackSize(1);
		this.setMaxDamage(quality.getMaxUses());
		this.setCreativeTab(MaricultureTab.tabMariculture);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Mariculture.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
