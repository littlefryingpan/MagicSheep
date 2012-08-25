package com.magicsheep.Techie;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicSheep extends JavaPlugin{
	
	static MagicSheep plugin;
    //public List<String> swears;
    public Entity sheepa;
    public String dasheep;
    List<Material> dablock = new ArrayList<Material>();
    List<Location> daloc = new ArrayList<Location>();
    //public Map<Block, Location> ridintrail = new HashMap<Block, Location>();
    //public UUID sheeps;
    
    public void onEnable() {
    	plugin = this;
        getServer().getPluginManager().registerEvents(new MagicSheepListener(plugin), plugin); // This is basically the same thing I commented out :/
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is now running." );
        //replacements = getConfig().getStringList("replacements");
        dasheep = getConfig().getString("dasheep");
        //sheeps = UUID.fromString(dasheep);
    }
    
	@Override
	public void onDisable() {	
		if (sheepa != null) {
        getConfig().set("dasheep", sheepa.getUniqueId().toString());
        //Should set all the blocks in the dablock/daloc list back to there location so after the restart the wool trail is reset/gone. (To prevent illegitness) (Should also make it so players can't break the trail)
        saveConfig();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
			 if (commandLabel.equalsIgnoreCase("magicsheep")) {
				 if (sender instanceof Player) {
			     Player player = (Player) sender;       
        		if (sender.hasPermission("magicsheep.admin") || sender.isOp()) {
        			if (sheepa != null) {
        			sheepa.remove(); //Kill the old one! (Doens't work after restarts)
        			}
        			sheepa = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.SHEEP); 			
        			player.sendMessage(ChatColor.BLUE + "MAGIC SHEEP TIME!");
        			getConfig().set("dasheep", sheepa.getUniqueId().toString());
        			dasheep = getConfig().getString("dasheep");
        		}
        		else {
        			sender.sendMessage(ChatColor.RED + "error, you do not have permission to use this command!");
        		}
				 } else {
						sender.sendMessage("You can't run this command from console!");
					}
        	}
		return false;
	}
}
