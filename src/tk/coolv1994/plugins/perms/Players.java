package tk.coolv1994.plugins.perms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vinnie on 2/4/2015.
 */
public class Players {
    private static final File playerFile = new File("./plugins/Permissions/players.json");
    private final Map<String, Player> players;

    public Players() {
        players = new HashMap<String, Player>();
        List<String> groups = new ArrayList<String>();
        groups.add("OP");
        List<String> perms = new ArrayList<String>();
        perms.add("perms.*");
        players.put("CONSOLE", new Player(groups, perms));
    }

    public Players(Map<String, Player> players) {
        if (players == null)
            players = new HashMap<String, Player>();
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

    public static Players loadPlayers() {
        try {
            return Permissions.getGson().fromJson(new FileReader(playerFile), Players.class);
        } catch (FileNotFoundException e) {
            playerFile.getParentFile().mkdirs();
            Players defaults = new Players();
            defaults.savePlayers();
            return defaults;
        }
    }

    public void savePlayers() {
        try {
            FileWriter fw = new FileWriter(playerFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Permissions.getGson().toJson(this));
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving players.\n" + e.getMessage());
        }
    }
}
