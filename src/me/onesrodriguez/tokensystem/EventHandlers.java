package me.onesrodriguez.tokensystem;

import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventHandlers implements Listener{
    
  
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException{
        for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
        }
    }
      
    @EventHandler
    public void onKill(EntityDeathEvent e) throws SQLException{
        if(e.getEntity() instanceof Player){
            Player deadp = (Player) e.getEntity();
            if(deadp.getKiller() instanceof Player){
                Player p = deadp.getKiller();
                if(p.hasPermission("tokens.x2") && !p.isOp()){
                    TokenAPI.addTokens(p, 4);
                    TokenAPI.takeTokens(deadp, 2);
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
                    }
                }
                if(p.hasPermission("tokens.x3") && !p.isOp()){
                    TokenAPI.addTokens(p, 6);
                    TokenAPI.takeTokens(deadp, 2);
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
                    }
                }
                if(p.hasPermission("tokens.x4") && !p.isOp()){
                    TokenAPI.addTokens(p, 8);
                    TokenAPI.takeTokens(deadp, 2);
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
                    }
                }
                if(p.isOp()){
                    TokenAPI.addTokens(p, 10);
                    TokenAPI.takeTokens(deadp, 2);
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
                    }
                }else{
                    TokenAPI.addTokens(p, 2);
                    TokenAPI.takeTokens(deadp, 2);
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ScoreboardHandler.updateScoreboard(pl);
                    }
                }

            }
            
        
}
}
}    
