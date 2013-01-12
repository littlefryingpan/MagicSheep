package com.magicsheep.Techie;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicSheep extends JavaPlugin{
	
	static MagicSheep plugin;
    public Entity sheepa;
    public List<String> sheeps; 
    // REMOVED DUE TO BUGS/ISSUES
    /*
    List<Material> dablock = new ArrayList<Material>();
    List<Location> daloc = new ArrayList<Location>();
    */
    //public Map<Block, Location> ridintrail = new HashMap<Block, Location>();
    //public UUID sheeps;
    
    public void onEnable() {
    	plugin = this;
        getServer().getPluginManager().registerEvents(new MagicSheepListener(plugin), plugin); // This is basically the same thing I commented out :/
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is now running." );
        //replacements = getConfig().getStringList("replacements");
        sheeps = getConfig().getStringList("sheeps");
        //sheeps = UUID.fromString(dasheep);
    }
    
	@Override
	public void onDisable() {	
		if (sheepa != null) { // NO
        getConfig().set("sheeps", sheeps);
        // getConfig().set("sheeps", sheepa.getUniqueId().toString());
        //Should set all the blocks in the dablock/daloc list back to there location so after the restart the wool trail is reset/gone. (To prevent illegitness) (Should also make it so players can't break the trail)
        saveConfig();
        // REMOVED DUE TO BUGS/ISSUES
        /* 
        if (plugin.dablock.size() > 0) {
        for(int i=0; i<plugin.dablock.size(); i++){
        	plugin.daloc.get(i).getBlock().setType(plugin.dablock.get(i));
        	plugin.dablock.remove(i);
        	plugin.daloc.remove(i); 
        }
		}
		*/
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
			 if (commandLabel.equalsIgnoreCase("magicsheep")) {
				 if (sender instanceof Player) {
			     Player player = (Player) sender;      
        		if (sender.hasPermission("magicsheep.admin") || sender.isOp()) {	
        			 if (args.length == 0) {
        			sheepa = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.SHEEP); 			
        			player.sendMessage(ChatColor.GOLD + "[Magic Sheep]" + ChatColor.BLUE + " A Magic Sheep has been spawned!!");
        			sheeps.add(sheepa.getUniqueId().toString());
        			getConfig().set("sheeps", sheeps);
        			sheeps = getConfig().getStringList("sheeps");
        		} else if ((args.length == 1) && (args[0].equalsIgnoreCase("remove"))) {
    				
    				if ((plugin.sheeps != null)) {
            			for (World i : getServer().getWorlds()){
                			List<LivingEntity> e = i.getLivingEntities();
                			for (Entity ei : e){
                				if (ei instanceof Sheep) {              					
                					if (sheeps.contains(ei.getUniqueId().toString())) {
                						ei.remove();
                						sheeps.remove(ei.getUniqueId().toString());
                					}
                				}
                			}
            			}
            			sender.sendMessage(ChatColor.GOLD + "[Magic Sheep]" + ChatColor.RED + " ALL MAGIC SHEEP HAVE BEEN REMOVED! >:)");
            			} else {
            				sender.sendMessage(ChatColor.GOLD + "[Magic Sheep]" + ChatColor.RED + " ERROR, No magic sheep currently living!");     
            			}
    				
    				
    			} else if (args.length == 1) {
    				
    			}
        		}
        		else {
        			sender.sendMessage(ChatColor.GOLD + "[Magic Sheep]" + ChatColor.RED + "error, you do not have permission to use this command!");
        		}
				 } else {
						sender.sendMessage(ChatColor.GOLD + "[Magic Sheep]" + " Console command ussage:");
						sender.sendMessage(ChatColor.WHITE + "/magicsheep <PlayerName>");
						sender.sendMessage(ChatColor.GRAY + "Description: spawns a magic sheep at the specified players location");
						sender.sendMessage(ChatColor.WHITE + "/magicsheep Remove");
						sender.sendMessage(ChatColor.GRAY + "Description: Removes all MagicSheep");
						
					}
        	}
		return false;
	}
}
