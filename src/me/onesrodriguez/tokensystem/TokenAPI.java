package me.onesrodriguez.tokensystem;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.entity.Player;

public class TokenAPI {
    
    public synchronized static void openConnection(){
        try{
            String username = "REPLACETHISWITHYOURUSERNAME"; 
            String password = "REPLACETHISWITHYOURPASSWORD"; 
            Potato.connection = DriverManager.getConnection("jdbc:mysql://sql.onesrodriguez.co.uk:3306/empiretokens", "username", "password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public synchronized static boolean databaseContainsPlayer(Player player){
        try{
            PreparedStatement sql = Potato.connection.prepareStatement("SELECT * FROM `users` WHERE uuid=?;");
            sql.setString(1, player.getUniqueId().toString());
            ResultSet set = sql.executeQuery();
            boolean containsPlayer = set.next();
            sql.close();
            set.close();
            return containsPlayer;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static int getTokens(Player player) throws SQLException{
        openConnection();
        try{
            int tokens = 0;
            if(databaseContainsPlayer(player)){
                PreparedStatement sql = Potato.connection.prepareStatement("SELECT tokens FROM `users` WHERE uuid=?;");
                sql.setString(1, player.getUniqueId().toString());
                
                ResultSet set = sql.executeQuery();
                set.next();
                
                tokens = set.getInt("tokens");
                                
                sql.close();
                set.close();
                
                return tokens;
            } else {
                PreparedStatement newPlayer = Potato.connection.prepareStatement("INSERT INTO `users` values(?,0,?);");
                newPlayer.setString(1, player.getUniqueId().toString());
                newPlayer.setString(2, player.getName());
                newPlayer.execute();
                newPlayer.close();
                return 0;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        } finally {
            Potato.connection.close();
        }
    }
    
    public static void addTokens(Player player, int i) throws SQLException{
        openConnection();
        try{
            int tokens = 0;
            if(databaseContainsPlayer(player)){
                PreparedStatement sql = Potato.connection.prepareStatement("SELECT tokens FROM `users` WHERE uuid=?;");
                sql.setString(1, player.getUniqueId().toString());
                
                ResultSet set = sql.executeQuery();
                set.next();
                
                tokens = set.getInt("tokens");
                int newTokens = tokens + i;
                
                PreparedStatement tokensUpdate = Potato.connection.prepareStatement("UPDATE `users` SET tokens=? WHERE uuid=?;");
                tokensUpdate.setInt(1, tokens + i);
                tokensUpdate.setString(2, player.getUniqueId().toString());
                tokensUpdate.executeUpdate();
                
                tokensUpdate.close();
                sql.close();
                set.close();
                Potato.sendMessage(player, "§eYou earned §d" + i + " §etokens. You now have §d" + newTokens + " §etokens.");
            } else {
                PreparedStatement newPlayer = Potato.connection.prepareStatement("INSERT INTO `users` values(?,0,?);");
                newPlayer.setString(1, player.getUniqueId().toString());
                newPlayer.setString(2, player.getName());
                newPlayer.execute();
                newPlayer.close();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            Potato.connection.close();
        }
        
    }
    
    public static void takeTokens(Player player, int i) throws SQLException{
        openConnection();
        try{
            int tokens = 0;
            if(databaseContainsPlayer(player)){
                PreparedStatement sql = Potato.connection.prepareStatement("SELECT tokens FROM `users` WHERE uuid=?;");
                sql.setString(1, player.getUniqueId().toString());
                
                ResultSet set = sql.executeQuery();
                set.next();
                
                tokens = set.getInt("tokens");
                int newTokens = tokens - i;
                
                PreparedStatement tokensUpdate = Potato.connection.prepareStatement("UPDATE `users` SET tokens=? WHERE uuid=?;");
                tokensUpdate.setInt(1, tokens - i);
                tokensUpdate.setString(2, player.getUniqueId().toString());
                tokensUpdate.executeUpdate();
                
                tokensUpdate.close();
                sql.close();
                set.close();
                Potato.sendMessage(player, "§eYou §cLOST §d" + i + " §etokens. You now have §d" + newTokens + " §etokens.");
            } else {
                PreparedStatement newPlayer = Potato.connection.prepareStatement("INSERT INTO `users` values(?,0,?);");
                newPlayer.setString(1, player.getUniqueId().toString());
                newPlayer.setString(2, player.getName());
                newPlayer.execute();
                newPlayer.close();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            Potato.connection.close();
        }
        
    }
    
    public static boolean hasEnough(Player player, int i) throws SQLException{
        openConnection();
        try{
            int tokens = 0;
            if(databaseContainsPlayer(player)){
                PreparedStatement sql = Potato.connection.prepareStatement("SELECT tokens FROM `users` WHERE uuid=?;");
                sql.setString(1, player.getUniqueId().toString());
                
                ResultSet set = sql.executeQuery();
                set.next();
                
                tokens = set.getInt("tokens");
                
                sql.close();
                set.close();
                
                return tokens >= i;

            } else {
                PreparedStatement newPlayer = Potato.connection.prepareStatement("INSERT INTO `users` values(?,0,?);");
                newPlayer.setString(1, player.getUniqueId().toString());
                newPlayer.setString(2, player.getName());
                newPlayer.execute();
                newPlayer.close();
                return false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            Potato.connection.close();
        }
        return false;
    }

    
            

}
