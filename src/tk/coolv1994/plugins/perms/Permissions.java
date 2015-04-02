package tk.coolv1994.plugins.perms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tk.coolv1994.gawdserver.perms.PermissionManager;
import tk.coolv1994.gawdserver.plugin.Plugin;

import java.util.List;
import java.util.Map;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class Permissions implements Plugin, PermissionManager {
    private static Permissions manager;
    private Groups groups;
    private Players players;
    private static Gson gson;

    public Permissions() {
        manager = this;
        groups = new Groups();
        players = new Players();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.enableComplexMapKeySerialization();
        builder.serializeNulls();
        builder.disableHtmlEscaping();
        gson = builder.create();
    }

    @Override
    public boolean hasPermission(String username, String perm) {
        if (players.hasPlayer(username)) {
            List<String> p = players.getPlayer(username).getPermissions();
            // Negative perms
            if (p.contains("-" + perm))
                return false;
            // Star perm formatting
            String base = "";
            if (perm.contains("."))
                base = perm.substring(0, perm.lastIndexOf(".") + 1);
            // Player Star perms
            if (p.contains("*"))
                return true;
            if (p.contains(base + "*"))
                return true;
            // Perm Check
            if (p.contains(perm))
                return true;
            // Groups
            for (String group : players.getPlayer(username).getGroups()) {
                if (groups.hasGroup(group)) {
                    p = groups.getGroup(group).getPermissions();
                    // Group Negative perms
                    if (p.contains("-" + perm))
                        return false;
                    // Group Star perms
                    if (p.contains("*"))
                        return true;
                    if (p.contains(base + "*"))
                        return true;
                    // Group Perm Check
                    if (p.contains(perm))
                        return true;
                }
            }
        }
        return false;
    }

    public void giveGroup(String username, String group) {
        Player p = players.getPlayer(username);
        p.addGroup(group);
        players.putPlayer(username, p);
    }

    public void takeGroup(String username, String group) {
        Player p = players.getPlayer(username);
        p.removeGroup(group);
        players.putPlayer(username, p);
    }

    public void givePermission(String username, String permission) {
        Player p = players.getPlayer(username);
        p.addPermission(permission);
        players.putPlayer(username, p);
    }

    public void takePermission(String username, String permission) {
        Player p = players.getPlayer(username);
        p.removePermission(permission);
        players.putPlayer(username, p);
    }

    public static Permissions getManager() {
        return manager;
    }

    public void loadPerms() {
        groups = Groups.loadGroups();
        players = Players.loadPlayers();
        try {
            for (Map.Entry<String, Group> e : groups.getGroups().entrySet()) {
                System.out.println(e.getKey() + " (G)" + e.getValue().toString());
            }
            for (Map.Entry<String, Player> e : players.getPlayers().entrySet()) {
                System.out.println(e.getKey() + " (P)" + e.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePerms() {
        groups.saveGroups();
        players.savePlayers();
    }

    @Override
    public void startup() {
        loadPerms();
    }

    @Override
    public void shutdown() {
        savePerms();
    }

    public static Gson getGson() {
        return gson;
    }
}
