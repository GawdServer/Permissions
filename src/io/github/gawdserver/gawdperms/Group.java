package io.github.gawdserver.gawdperms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class Group {
    private List<String> permissions;

    public Group() {
        permissions = new ArrayList<>();
    }

    public Group(List<String> permissions) {
        if (permissions == null)
            permissions = new ArrayList<>();
        this.permissions = permissions;
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
        return "[permissions: "+permissions.toString()+"]";
    }
}
