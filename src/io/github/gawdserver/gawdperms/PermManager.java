package io.github.gawdserver.gawdperms;

import io.github.gawdserver.api.perms.PermissionManager;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Vinnie on 4/16/2015.
 */
public class PermManager implements PermissionManager {
    private Groups groups;
    private Players players;
    private File pluginDir;

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
                    // Group Perm Check
                    if (p.contains(perm))
                        return true;
                }
            }
        }
        return false;
    }

    public void givePlayerGroup(String username, String group) {
        players.getPlayer(username).addGroup(group);
    }

    public void takePlayerGroup(String username, String group) {
        players.getPlayer(username).removeGroup(group);
    }

    public void giveGroupPermission(String group, String permission) {
        groups.getGroup(group).addPermission(permission);
    }

    public void takeGroupPermission(String group, String permission) {
        groups.getGroup(group).removePermission(permission);
    }

    public void givePlayerPermission(String username, String permission) {
        players.getPlayer(username).addPermission(permission);
    }

    public void takePlayerPermission(String username, String permission) {
        players.getPlayer(username).removePermission(permission);
    }

    public void addPlayer(String username) {
        players.putPlayer(username, new Player());
    }

    public void removePlayer(String username) {
        players.removePlayer(username);
    }

    public void addGroup(String group) {
        groups.putGroup(group, new Group());
    }

    public void removeGroup(String group) {
        groups.removeGroup(group);
    }

    public void setPluginDir(File pluginDir) {
        this.pluginDir = pluginDir;
    }

    public void loadPerms() {
        groups = Groups.loadGroups(pluginDir);
        players = Players.loadPlayers(pluginDir);

        // Debug: List players and groups
        for (Map.Entry<String, Group> e : groups.getGroups().entrySet()) {
            GawdPerms.logger.log(Level.INFO, "Group: {0} {1}" + new Object[]{e.getKey(), e.getValue().toString()});
        }
        for (Map.Entry<String, Player> e : players.getPlayers().entrySet()) {
            GawdPerms.logger.log(Level.INFO, "Player: {0} {1}", new Object[]{e.getKey(), e.getValue().toString()});
        }
    }

    public void savePerms() {
        groups.saveGroups(pluginDir);
        players.savePlayers(pluginDir);
    }
}
