package tk.coolv1994.plugins.perms;

import tk.coolv1994.gawdapi.perms.PermissionManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Vinnie on 4/16/2015.
 */
public class PermManager implements PermissionManager {
    private Groups groups;
    private Players players;

    public PermManager() {
        groups = new Groups();
        players = new Players();
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

    public void loadPerms() {
        groups = Groups.loadGroups();
        players = Players.loadPlayers();

        // Debug: List players and groups
        try {
            for (Map.Entry<String, Group> e : groups.getGroups().entrySet()) {
                System.out.println("[Permissions] Group: " + e.getKey() + " " + e.getValue().toString());
            }
        } catch (Exception e) {
            System.out.println("[Permissions] Error loading Groups!");
            e.printStackTrace();
        }
        try {
            for (Map.Entry<String, Player> e : players.getPlayers().entrySet()) {
                System.out.println("[Permissions] Player: " + e.getKey() + " " + e.getValue().toString());
            }
        } catch (Exception e) {
            System.out.println("[Permissions] Error loading Players!");
            e.printStackTrace();
        }
    }

    public void savePerms() {
        groups.saveGroups();
        players.savePlayers();
    }
}
