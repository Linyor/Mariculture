package mariculture.core.render;

import mariculture.core.Core;
import mariculture.core.blocks.BlockOyster;
import mariculture.core.lib.Modules;
import mariculture.core.lib.RenderMeta;
import mariculture.diving.render.ModelAirPump;
import mariculture.factory.Factory;
import mariculture.factory.render.ModelFLUDD;
import mariculture.factory.render.ModelTurbineGas;
import mariculture.factory.render.ModelTurbineHand;
import mariculture.factory.render.ModelTurbineWater;
import mariculture.fishery.Fishery;
import mariculture.fishery.render.ModelFeeder;
import mariculture.fishery.render.ModelSift;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderSingleItem implements IItemRenderer {
	private static final float scale = (float) (1.0 / 20.0);
	private static final ResourceLocation AIR_PUMP = new ResourceLocation("mariculture", "textures/blocks/air_pump_texture.png");
	private static final ResourceLocation SIFT = new ResourceLocation("mariculture", "textures/blocks/sift_texture.png");
	private static final ResourceLocation FEEDER = new ResourceLocation("mariculture", "textures/blocks/feeder_texture.png");
	private static final ResourceLocation FLUDD = new ResourceLocation("mariculture", "textures/blocks/fludd_texture.png");
	private static final ResourceLocation TURBINE = new ResourceLocation("mariculture", "textures/blocks/turbine_texture.png");
	private static final ResourceLocation TURBINE_GAS = new ResourceLocation("mariculture", "textures/blocks/turbine_gas_texture.png");
	private static final ResourceLocation TURBINE_HAND = new ResourceLocation("mariculture", "textures/blocks/turbine_hand_texture.png");

	private final ModelAirPump pump = new ModelAirPump(scale);
	private final ModelSift sift = new ModelSift(scale);
	private final ModelFeeder feeder = new ModelFeeder(scale);
	private final ModelFLUDD fludd = new ModelFLUDD(scale);
	private final ModelTurbineGas turbineGas = new ModelTurbineGas(scale);
	private final ModelTurbineWater turbine = new ModelTurbineWater(scale);
	private final ModelTurbineHand turbineHand = new ModelTurbineHand(scale);

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(item.itemID == Core.rendered.blockID) {
			int meta = item.getItemDamage();
			if(meta == RenderMeta.GEYSER || meta == RenderMeta.ANVIL_1 || meta >= RenderMeta.INGOT_CASTER)
				return false;
		} else if (item.itemID == Core.oyster.blockID && item.getItemDamage() != BlockOyster.NET) {
			return false;
		}

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.AIR_PUMP) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(AIR_PUMP);
			pump.renderInventory(type);
		}

		if (Modules.isActive(Modules.factory)) {
			if (item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.TURBINE_WATER) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TURBINE);
				turbine.renderInventory(type);
			}
			
			if (item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.TURBINE_GAS) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TURBINE_GAS);
				turbineGas.renderInventory(type);
			}
			
			if (item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.TURBINE_HAND) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TURBINE_HAND);
				turbineHand.renderInventory(type);
			}

			if ((item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.FLUDD_STAND)
					|| item.itemID == Factory.fludd.itemID) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(FLUDD);
				fludd.renderInventory(type);
			}
		}

		if (Modules.isActive(Modules.fishery)) {
			if (item.itemID == Core.rendered.blockID && item.getItemDamage() == RenderMeta.FISH_FEEDER) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(FEEDER);
				feeder.renderInventory(type);
			}
			if (item.itemID == Fishery.siftBlock.blockID) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(SIFT);
				sift.renderInventory(type);
			}
		}
	}
}
