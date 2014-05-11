package mariculture.fishery.fish;

import static mariculture.api.core.Environment.Salinity.SALINE;
import static mariculture.core.lib.Items.dropletAqua;
import static mariculture.core.lib.Items.dropletEarth;
import static mariculture.core.lib.Items.dropletFrozen;
import static mariculture.core.lib.Items.dropletWater;
import mariculture.api.core.Environment.Height;
import mariculture.api.core.Environment.Salinity;
import mariculture.api.core.Environment.Time;
import mariculture.api.fishery.RodQuality;
import mariculture.api.fishery.fish.FishSpecies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FishTuna extends FishSpecies {
	public FishTuna(int id) {
		super(id);
	}

	@Override
	public int[] setSuitableTemperature() {
		return new int[] { -5, 15 };
	}

	@Override
	public Salinity[] setSuitableSalinity() {
		return new Salinity[] { SALINE };
	}

	@Override
	public boolean isDominant() {
		return true;
	}

	@Override
	public int getLifeSpan() {
		return 15;
	}

	@Override
	public int getFertility() {
		return 200;
	}

	@Override
	public int getFoodConsumption() {
		return 2;
	}

	@Override
	public int getWaterRequired() {
		return 60;
	}

	@Override
	public void addFishProducts() {
		addProduct(dropletWater, 3.5D);
		addProduct(dropletAqua, 4D);
		addProduct(dropletEarth, 3D);
		addProduct(dropletFrozen, 5D);
	}

	@Override
	public double getFishOilVolume() {
		return 3.250D;
	}

	@Override
	public int getFishMealSize() {
		return 8;
	}

	@Override
	public int getFoodStat() {
		return 4;
	}

	@Override
	public float getFoodSaturation() {
		return 0.65F;
	}

	@Override
	public void onConsumed(World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 160, 1));
	}

	@Override
	public RodQuality getRodNeeded() {
		return RodQuality.OLD;
	}

	@Override
	public int getCatchChance() {
		return 25;
	}

	@Override
	public double getCaughtAliveChance(int height, int time) {
		return Time.isNoon(time) && Height.isCave(height) ? 5D : 0D;
	}
}
