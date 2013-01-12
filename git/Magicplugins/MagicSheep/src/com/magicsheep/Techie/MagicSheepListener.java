package com.magicsheep.Techie;

import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
//import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
//import org.bukkit.craftbukkit.entity.CraftCreature;
//import org.bukkit.craftbukkit.CraftWorld;

import com.magicsheep.Techie.MagicSheep;

public class MagicSheepListener implements Listener{
	   private final MagicSheep plugin;
	   Random random = new Random();
	   int scolor = 0;
	   DyeColor sco = DyeColor.WHITE;
	   Block dablock;
	   Location daloc;
	   
	    public MagicSheepListener(MagicSheep instance) {
	        plugin = instance;
	    }
	    
	    @EventHandler(priority = EventPriority.NORMAL)
		public void onMobHurt(EntityDamageByEntityEvent event){
			if (event.getEntity() instanceof Sheep) {	
				//if (plugin.sheepa != null && event.getEntity() == plugin.sheepa) {
				if ((plugin.sheeps != null)) {
				//if (event.getEntity().getUniqueId().toString().equals(plugin.dasheep.toString())) {
					if (plugin.sheeps.contains(event.getEntity().getUniqueId().toString())) {
					Player attacker = (Player) event.getDamager(); //Get the person that attacked?		
					
					World world = event.getEntity().getWorld();
					world.playEffect(event.getEntity().getLocation(), Effect.EXTINGUISH, 5); // MUAHHA
					((Sheep) event.getEntity()).setColor(Colorchanger((Sheep) event.getEntity()));
					//attacker.sendMessage(event.getEntity().getUniqueId().toString() + " " + plugin.dasheep.toString());
					//^ used for debuggin
					if (attacker.getInventory().getItemInHand().getType() != null && attacker.getInventory().getItemInHand().getType() == Material.STICK)
					{
						// REMOVED DUE TO BUGS/ISSUES
						/*
						int xx = event.getEntity().getLocation().getBlockX();
		                int yy = event.getEntity().getLocation().getBlockY() +2;
		                int zz = event.getEntity().getLocation().getBlockZ();
		                World ww = event.getEntity().getWorld();
		                Location lloc = new Location(ww, xx, yy, zz);
						((Sheep) event.getEntity()).teleport(lloc); //tp up 2 so it doesn't place  block when we wipe them below!
						 if (plugin.dablock.size() > 0) {
							 attacker.sendMessage("Does not equal 0!");
						        for(int i=0; i<plugin.dablock.size(); i++){
						        	plugin.daloc.get(i).getBlock().setType(plugin.dablock.get(i)); //Doesn't seem to remove them all 100% of the time! Should I run it twice? :,(
						        	plugin.dablock.remove(i);
						        	plugin.daloc.remove(i); 
						        }
								}
								*/
					((Sheep) event.getEntity()).setPassenger(attacker); // :O RIDE THE SHEEP!
					}
					event.setCancelled(true);
				}
				}
			}
		}
		@EventHandler(priority = EventPriority.NORMAL)
		public void onShearSheep(PlayerShearEntityEvent event){	
				//if ((plugin.sheeps != null) && (event.getEntity().getUniqueId().toString() == plugin.dasheep.toString())) {	
			      if ((plugin.sheeps != null) && (plugin.sheeps.contains(event.getEntity().getUniqueId().toString()))) {	
					event.setCancelled(true);  //You can't shear the magic sheep :,(
				}
		}
		@EventHandler (priority = EventPriority.NORMAL)
		public void onentitydie(EntityDeathEvent event) {
			if ((plugin.sheeps != null) && (plugin.sheeps.contains(event.getEntity().getUniqueId().toString()))) {
				event.getDrops().clear(); // KEEPIN' IT LEGIT!
				event.setDroppedExp(0);
				event.getEntity().playEffect(EntityEffect.WOLF_SMOKE);
				plugin.sheeps.remove(event.getEntity().getUniqueId().toString());
				
			}
		}
		
		 @EventHandler (priority = EventPriority.NORMAL)
		    public void onPlayerMove(PlayerMoveEvent event) {
		        if (!event.isCancelled()) {
		        	Player player = event.getPlayer();
		        	//List nearbyentity = player.getNearbyEntities(5, 5, 5)      	
		        	//if ((plugin.sheeps != null) && player.isInsideVehicle() && player.getVehicle().getUniqueId().toString().equals(plugin.dasheep.toString())) {	
		        	if ((plugin.sheeps != null) && player.isInsideVehicle() && plugin.sheeps.contains(player.getVehicle().getUniqueId().toString())) {	

	                	if (player.isSneaking()) {
	                		Float dirX = (float) (0 - (Math.sin((player.getEyeLocation().getYaw() / 180) * Math.PI) * 3)); //3 for speed
		                	Float dirZ = (float) (Math.cos((player.getEyeLocation().getYaw() / 180) * Math.PI) * 3);
		                	//player.getVehicle().setVelocity(player.getEyeLocation().toVector());
		                	player.getVehicle().setVelocity(player.getVehicle().getVelocity().setZ(dirZ).multiply(0.10000002D));
		                	player.getVehicle().setVelocity(player.getVehicle().getVelocity().setX(dirX).multiply(0.10000002D));
		                	((Sheep) player.getVehicle()).setColor(Colorchanger((Sheep) player.getVehicle()));
		                	
		                	//Location locy = player.getLocation();
		                	//((Entity) player.getVehicle()).setLocation(locy.getX(), locy.getY(), locy.getZ(), locy.getYaw(), locy.getPitch());
	                	}
		        		// REMOVED DUE TO BUGS/ISSUES
		        		/*
		        		int x = player.getLocation().getBlockX();
		                int y = player.getLocation().getBlockY() -1;
		                int z = player.getLocation().getBlockZ();
		                World w = player.getWorld();
		                Location loc = new Location(w, x, y, z);
		                if (!loc.getBlock().getType().equals(Material.WOOL) && !loc.getBlock().getType().equals(Material.AIR)) { //An attempt to save the block it was previously and change it back after the sheep moves 5 blocks.
		                plugin.dablock.add(loc.getBlock().getType());
		                plugin.daloc.add(loc);
		                player.sendMessage("Not wool or air!");
		                }
		                if (plugin.dablock.size() > 5) {
		                	plugin.daloc.get(0).getBlock().setType(plugin.dablock.get(0));
		                	plugin.dablock.remove(0);
		                	plugin.daloc.remove(0); 
		                	player.sendMessage("THIS STRING IS GREATER THEN 5");
		                	player.sendMessage(plugin.dablock.toString());
		                } 
		                if (!loc.getBlock().getType().equals(Material.AIR)) {
		                loc.getBlock().setTypeIdAndData(35, sco.getData(), true);
		                }
		                */	                
		        	}
		        }
		 }
		 @EventHandler (priority = EventPriority.NORMAL)
		    public void onPlayerInteract (PlayerInteractEvent event) {
		        if (!event.isCancelled()) {
		        	Player player = event.getPlayer();
		            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction() == Action.LEFT_CLICK_AIR) {
		                //Block block = event.getClickedBlock();
		                if ((plugin.sheeps != null) && player.isInsideVehicle() && plugin.sheeps.contains(player.getVehicle().getUniqueId().toString())) {	
		                	//Sheep mob = (Sheep) player.getVehicle(); //again, "Mob" needs to be a type of creature you wish to control
							//Block to = block; //block location they need to travel to.
							//EntityCreature ec = ((CraftCreature)mob).getHandle();
							//PathEntity pf = ((CraftWorld)to.getWorld()).getHandle().a(ec, to.getX(), to.getY(), to.getZ(), 16.0f, true, false, false, true);
							//ec.setPathEntity(pf);
		                	
		                	//Sheep mob = (Sheep) player.getVehicle(); //"Mob" needs to be the type of creature you wish to control
		                	//Location location; //set your location
		                	//EntityCreature ec = ((CraftCreature)mob).getHandle();
		                	//Navigation nav = ec.al();
		                	//nav.a(location.getX(),location.getY(),location.getZ(),0.3f);
		                	//player.getVehicle().setVelocity(player.getEyeLocation().getDirection());
		                	
		                	
		                }
		                    }
		                }
		            } 
 public static DyeColor Colorchanger(Sheep sheep) 
 {
	 if (sheep.getColor().equals(DyeColor.WHITE)) {
		 return DyeColor.GREEN;
	 }
	 if (sheep.getColor().equals(DyeColor.GREEN)) {
		 return DyeColor.RED;
	 }
	 if (sheep.getColor().equals(DyeColor.RED)) {
		 return DyeColor.BLUE;
	 }
	 if (sheep.getColor().equals(DyeColor.BLUE)) {
		 return DyeColor.BLACK;
	 }
	 if (sheep.getColor().equals(DyeColor.BLACK)) {
		 return DyeColor.CYAN;
	 }
	 if (sheep.getColor().equals(DyeColor.CYAN)) {
		 return DyeColor.PINK;
	 }
	 if (sheep.getColor().equals(DyeColor.PINK)) {
		 return DyeColor.GRAY;
	 }
	 if (sheep.getColor().equals(DyeColor.GRAY)) {
		 return DyeColor.YELLOW;
	 }
	 if (sheep.getColor().equals(DyeColor.YELLOW)) {
		 return DyeColor.BROWN;
	 }
	 if (sheep.getColor().equals(DyeColor.ORANGE)) {
		 return DyeColor.LIME;
	 }
	 if (sheep.getColor().equals(DyeColor.LIME)) {
		 return DyeColor.SILVER;
	 }
	 if (sheep.getColor().equals(DyeColor.SILVER)) {
		 return DyeColor.WHITE;
	 }
	return DyeColor.WHITE;
	
	 
 }
				
}
