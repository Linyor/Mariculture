package mariculture.api.fishery.fish;

import mariculture.api.core.EnumBiomeType;
import mariculture.api.core.EnumSalinityType;
import mariculture.api.fishery.Fishing;
import net.minecraft.world.World;

@Deprecated
public enum EnumFishGroup {
	@Deprecated
	NETHER(0, new EnumBiomeType[] { EnumBiomeType.HELL, EnumBiomeType.ARID }, 
			  new EnumSalinityType[] { EnumSalinityType.MAGIC }),
	
	@Deprecated	
	RIVER(1, new EnumBiomeType[] { EnumBiomeType.NORMAL, EnumBiomeType.COLD, EnumBiomeType.FROZEN }, 
			 new EnumSalinityType[] { EnumSalinityType.FRESH }),
	@Deprecated		
	OCEAN(2, new EnumBiomeType[] { EnumBiomeType.OCEAN, EnumBiomeType.FROZEN_OCEAN, EnumBiomeType.COLD, EnumBiomeType.FROZEN }, 
			 new EnumSalinityType[] { EnumSalinityType.SALT, EnumSalinityType.FRESH }),
			
	@Deprecated
	TROPICAL(3, new EnumBiomeType[] { EnumBiomeType.HOT, EnumBiomeType.NORMAL, EnumBiomeType.OCEAN }, 
				new EnumSalinityType[] { EnumSalinityType.FRESH, EnumSalinityType.SALT }),
			
	@Deprecated
	DOMESTICATED(4, new EnumBiomeType[] { EnumBiomeType.NORMAL, EnumBiomeType.HOT, EnumBiomeType.OCEAN, EnumBiomeType.FROZEN_OCEAN,
										  EnumBiomeType.COLD, EnumBiomeType.MUSHROOM, EnumBiomeType.FROZEN, EnumBiomeType.HELL},
					new EnumSalinityType[] { EnumSalinityType.FRESH, EnumSalinityType.SALT, EnumSalinityType.MAGIC }),
	@Deprecated		
	ENDER(5, new EnumBiomeType[] { EnumBiomeType.ENDER, EnumBiomeType.COLD }, 
			 new EnumSalinityType[] { EnumSalinityType.MAGIC }),
	
	@Deprecated
	AMAZONIAN(6, new EnumBiomeType[] { EnumBiomeType.HOT, EnumBiomeType.ARID }, 
				 new EnumSalinityType[] { EnumSalinityType.FRESH, EnumSalinityType.MAGIC }),
	@Deprecated
	FLATFISH(7, new EnumBiomeType[] { EnumBiomeType.FROZEN_OCEAN, EnumBiomeType.OCEAN, EnumBiomeType.HOT, EnumBiomeType.ARID, EnumBiomeType.HELL }, 
				new EnumSalinityType[] { EnumSalinityType.SALT, EnumSalinityType.MAGIC }),
				
	@Deprecated
	JELLY(8, new EnumBiomeType[] { EnumBiomeType.OCEAN, EnumBiomeType.FROZEN_OCEAN, EnumBiomeType.ENDER },  
			 new EnumSalinityType[] { EnumSalinityType.SALT, EnumSalinityType.MAGIC }),
			 
	@Deprecated
	NEMO(9, new EnumBiomeType[] { EnumBiomeType.MUSHROOM, EnumBiomeType.OCEAN }, 
			new EnumSalinityType[] { EnumSalinityType.MAGIC, EnumSalinityType.SALT });

	private final int id;
	private final EnumBiomeType[] biomes;
	private final EnumSalinityType[] salinity;

	private EnumFishGroup(int id, EnumBiomeType[] biomes, EnumSalinityType[] salinity) {
		this.id = id;
		this.biomes = biomes;
		this.salinity = salinity;
	}

	public int getID() {
		return this.id;
	}
	
	public EnumBiomeType[] getBiomes() {
		return this.biomes;
	}

	public boolean canLive(World world, int x, int y, int z) {
		return Fishing.fishHelper.canLive(world.getWorldChunkManager().getBiomeGenAt(x, z), getBiomes(), getSalinityRequired(), world.getBlockTileEntity(x, y, z));
	}

	public EnumSalinityType[] getSalinityRequired() {
		return salinity;
	}
}
