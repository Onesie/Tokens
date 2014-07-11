package me.onesrodriguez.tokensystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Potato extends JavaPlugin {
   
    public static String CHAT_TAG = "§8[§5EmpireMC§8]";
      
    public static void sendMessage(Player player, String message){
        player.sendMessage(CHAT_TAG + message);
    }
    
    public static Connection connection;
        
    @Override
    public void onEnable(){
       getServer().getPluginManager().registerEvents(new EventHandlers(), this);
       System.out.println("BOOBS");
    }
    
    @Override
    public void onDisable(){
        try {
            if(connection != null && !connection.isClosed())
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("addtokens")){
            Player player = (Player) sender;
            if(!(sender instanceof Player)){
                sender.sendMessage("NO.");
                return true;
            }
            if(player.hasPermission("tokens.add")){
                int tokens = Integer.parseInt(args[0]);
                if(args.length == 1){
                    try {
                        TokenAPI.addTokens(player, tokens);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                   
                if(args.length == 2){
                    Player target = Bukkit.getPlayer(args[1]);
                    try {
                        TokenAPI.addTokens(target, tokens);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                return true;
                }
                }              
            }                       
        }
        return false;
    }


}