package me.onesrodriguez.tokensystem;

import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardHandler implements Listener{
     
    public static Scoreboard board;

    public ScoreboardHandler() {
    }
        
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setScoreboard(board);
    }
    
    public static void scoreboard() throws SQLException{
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
 
        Objective objective = board.registerNewObjective("tokens", "dummy");
        objective.setDisplayName("§5The§lEmpire");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        for(Player pl : Bukkit.getOnlinePlayers()){
            updateScoreboard(pl);
        }
    }

    public static void updateScoreboard(Player player) throws SQLException{
        Score tokens = board.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer("§6§lYour tokens:"));
        tokens.setScore(TokenAPI.getTokens(player));
    }
    
    public Scoreboard getBoard() {
        return board;
    }
     
     
}
