package mariculture.fishery.fish;

import static mariculture.api.core.Environment.Salinity.FRESH;
import static mariculture.core.lib.Items.dropletFlux;
import static mariculture.core.lib.Items.dropletWater;
import mariculture.api.core.Environment.Salinity;
import mariculture.api.core.Environment.Time;
import mariculture.api.fishery.RodQuality;
import mariculture.api.fishery.fish.FishSpecies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class FishTetra extends FishSpecies {
	public FishTetra(int id) {
		super(id);
	}

	@Override
	public int[] setSuitableTemperature() {
		return new int[] { 20, 28 };
	}

	@Override
	public Salinity[] setSuitableSalinity() {
		return new Salinity[] { FRESH };
	}

	@Override
	public boolean isDominant() {
		return false;
	}

	@Override
	public int getLifeSpan() {
		return 5;
	}

	@Override
	public int getFertility() {
		return 130;
	}

	@Override
	public int getBaseProductivity() {
		return 2;
	}
	
	@Override
	public int getAreaOfEffectBonus(ForgeDirection dir) {
		return -1;
	}

	@Override
	public void addFishProducts() {
		addProduct(dropletWater, 3.5D);
		addProduct(dropletFlux, 0.5D);
	}

	@Override
	public double getFishOilVolume() {
		return 0.025D;
	}

	@Override
	public int getFishMealSize() {
		return 1;
	}

	@Override
	public float getFoodSaturation() {
		return 0.05F;
	}

	@Override
	public int getFoodDuration() {
		return 8;
	}

	@Override
	public void onConsumed(World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 160, 0));
	}

	@Override
	public RodQuality getRodNeeded() {
		return RodQuality.OLD;
	}

	@Override
	public int getCatchChance() {
		return 45;
	}

	@Override
	public double getCaughtAliveChance(int height, int time) {
		return Time.isDay(time) ? 55D : 25D;
	}
}
