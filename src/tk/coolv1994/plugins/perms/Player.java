package tk.coolv1994.plugins.perms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class Player {
    private List<String> groups;
    private List<String> permissions;

    public Player() {
        groups = new ArrayList<>();
        permissions = new ArrayList<>();
    }

    public Player(List<String> groups, List<String> permissions) {
        if (groups == null)
            groups = new ArrayList<>();
        this.groups = groups;
        if (permissions == null)
            permissions = new ArrayList<>();
        this.permissions = permissions;
    }

    public List<String> getGroups() {
        return groups;
    }

    public boolean addGroup(String group) {
        return groups.add(group);
    }

    public boolean removeGroup(String group) {
        return groups.remove(group);
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean addPermission(String perm) {
        return permissions.add(perm);
    }

    public boolean removePermission(String perm) {
        return permissions.remove(perm);
    }

    public String toString(){
        return "[groups: "+groups.toString()+"; " + "permissions: "+permissions.toString()+"]";
    }
}
