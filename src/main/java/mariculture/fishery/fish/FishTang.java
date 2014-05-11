package mariculture.fishery.fish;

import static mariculture.api.core.Environment.Salinity.BRACKISH;
import static mariculture.api.core.Environment.Salinity.SALINE;
import static mariculture.core.lib.Items.dropletAqua;
import static mariculture.core.lib.Items.dropletWater;
import static mariculture.core.lib.Items.lapis;
import mariculture.api.core.Environment.Height;
import mariculture.api.core.Environment.Salinity;
import mariculture.api.core.Environment.Time;
import mariculture.api.fishery.RodQuality;
import mariculture.api.fishery.fish.FishSpecies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FishTang extends FishSpecies {
	public FishTang(int id) {
		super(id);
	}

	@Override
	public int[] setSuitableTemperature() {
		return new int[] { 24, 29 };
	}

	@Override
	public Salinity[] setSuitableSalinity() {
		return new Salinity[] { SALINE, BRACKISH };
	}

	@Override
	public boolean isDominant() {
		return false;
	}

	@Override
	public int getLifeSpan() {
		return 9;
	}

	@Override
	public int getFertility() {
		return 4000;
	}

	@Override
	public int getWaterRequired() {
		return 25;
	}

	@Override
	public void addFishProducts() {
		addProduct(dropletWater, 6.5D);
		addProduct(dropletAqua, 4.5D);
		addProduct(lapis, 2.0D);
	}

	@Override
	public double getFishOilVolume() {
		return 0.725D;
	}

	@Override
	public void onConsumed(World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 80, 0));
	}

	@Override
	public boolean canWork(int time) {
		return Time.isDay(time);
	}

	@Override
	public RodQuality getRodNeeded() {
		return RodQuality.GOOD;
	}

	@Override
	public double getCatchChance(int height, int time) {
		return Height.isShallows(height) ? 25D : 0D;
	}

	@Override
	public double getCaughtAliveChance(int height, int time) {
		return Time.isDawn(time) ? 5D : 0D;
	}
}
