package tk.coolv1994.plugins.perms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinnie on 2/4/2015.
 */
public class Groups {
    private static final File groupFile = new File("./plugins/Permissions/groups.json");
    private final Map<String, Group> groups;

    public Groups() {
        this.groups = new HashMap<String, Group>();
        List<String> perms = new ArrayList<String>();
        perms.add("*");
        groups.put("OP", new Group(perms));
    }

    public Groups(Map<String, Group> groups) {
        if (groups == null)
            groups = new HashMap<String, Group>();
        this.groups = groups;
    }

    public boolean hasGroup(String name) {
        if (groups == null)
            return false;
        return groups.containsKey(name);
    }

    public Group getGroup(String name) {
        if (!hasGroup(name))
            return new Group();
        return groups.get(name);
    }

    public Map<String,Group> getGroups() {
        return groups;
    }

    public Group putGroup(String name, Group group) {
        return groups.put(name, group);
    }

    public static Groups loadGroups() {
        try {
            return Permissions.getGson().fromJson(new FileReader(groupFile), Groups.class);
        } catch (FileNotFoundException e) {
            groupFile.getParentFile().mkdirs();
            Groups defaults = new Groups();
            defaults.saveGroups();
            return defaults;
        }
    }

    public void saveGroups() {
        try {
            FileWriter fw = new FileWriter(groupFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Permissions.getGson().toJson(this));
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving groups.\n" + e.getMessage());
        }
    }
}
