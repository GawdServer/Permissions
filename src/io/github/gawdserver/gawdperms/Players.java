package io.github.gawdserver.gawdperms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Vinnie on 2/4/2015.
 */
public class Players {
    private static final String playerFile = "players.json";
    private final Map<String, Player> players;

    public Players() {
        players = new HashMap<>();
        List<String> groups = new ArrayList<>();
        groups.add("admin");
        List<String> perms = new ArrayList<>();
        perms.add("perms.give");
        perms.add("perms.take");
        perms.add("perms.load");
        perms.add("perms.save");
        players.put("CoolV1994", new Player(groups, perms));
    }

    public Players(Map<String, Player> players) {
        if (players == null)
            players = new HashMap<>();
        this.players = players;
    }

    public boolean hasPlayer(String username) {
        if (players == null)
            return false;
        return players.containsKey(username);
    }

    public Player getPlayer(String username) {
        if (!hasPlayer(username))
            return new Player();
        return players.get(username);
    }

    public Map<String,Player> getPlayers() {
        return players;
    }

    public Player putPlayer(String username, Player player) {
        return players.put(username, player);
    }

    public Player removePlayer(String name) {
        return players.remove(name);
    }

    public static Players loadPlayers(File pluginDir) {
        try {
            return GawdPerms.getGson().fromJson(new FileReader(new File(pluginDir, playerFile)), Players.class);
        } catch (FileNotFoundException ex) {
            pluginDir.mkdirs();
            Players defaults = new Players();
            defaults.savePlayers(pluginDir);
            return defaults;
        }
    }

    public void savePlayers(File pluginDir) {
        try {
            FileWriter fw = new FileWriter(new File(pluginDir, playerFile));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(GawdPerms.getGson().toJson(this));
            bw.close();
        } catch (IOException ex) {
            GawdPerms.logger.log(Level.SEVERE, "Error saving Players!", ex);
        }
    }
}
