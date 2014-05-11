package mariculture.magic;

import java.util.List;
import java.util.logging.Level;

import mariculture.core.handlers.LogHandler;
import mariculture.core.items.ItemDamageable;
import mariculture.core.util.Rand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/** Mob Magnet, On Right Click, Teleports all mobs within 32 x 32 blocks around the player of the type to an area near you
 * You must first kill a mob of the type you want first, in order to teleport them all to you **/
public class ItemMobMagnet extends ItemDamageable {
	public ItemMobMagnet(int i, int dmg) {
		super(i, dmg);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		if(!stack.hasTagCompound()) {
			list.add(StatCollector.translateToLocal("mariculture.string.noBound1"));
			list.add(StatCollector.translateToLocal("mariculture.string.noBound2"));
			return;
		} else {
			list.add(StatCollector.translateToLocal("mariculture.string.bound"));
			list.add(stack.stackTagCompound.getString("MobName"));
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!stack.hasTagCompound())
			return stack;
		
		try {
			String entity = stack.stackTagCompound.getString("MobClass").trim();
			Class clazz = Class.forName(stack.stackTagCompound.getString("MobClass").trim());
			
			List<EntityMob> enemies = world.getEntitiesWithinAABB(clazz, player.boundingBox.expand(32D, 32D, 32D));
			int x = (int) player.posX;
			int y = (int) (player.posY + 1);
			int z = (int) player.posZ;
			boolean teleported = false;
			for(Object i: enemies) {
				if (i instanceof EntityLivingBase) {
					((EntityLivingBase)i).setPositionAndUpdate(x, y, z);
					teleported = true;
					if(stack.attemptDamageItem(1, Rand.rand))
						stack.stackSize--;
				}
			}
			
			if(teleported) {
				world.playSoundEffect(x, y, z, "mob.endermen.portal", 1.0F, 1.0F);
			}
		} catch (Exception e) {
			LogHandler.log(Level.WARNING, "Mob Magnet Failed to find class for the target entities!");
		}
		
		return stack;
	}
}
