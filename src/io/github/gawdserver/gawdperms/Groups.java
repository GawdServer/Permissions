package io.github.gawdserver.gawdperms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Vinnie on 2/4/2015.
 */
public class Groups {
    private static final String groupFile = "groups.json";
    private final Map<String, Group> groups;

    public Groups() {
        this.groups = new HashMap<>();
        List<String> perms = new ArrayList<>();
        perms.add("*");
        groups.put("OP", new Group(perms));
    }

    public Groups(Map<String, Group> groups) {
        if (groups == null)
            groups = new HashMap<>();
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

    public Group removeGroup(String name) {
        return groups.remove(name);
    }

    public static Groups loadGroups(File pluginDir) {
        try {
            return GawdPerms.getGson().fromJson(new FileReader(new File(pluginDir, groupFile)), Groups.class);
        } catch (FileNotFoundException ex) {
            pluginDir.mkdirs();
            Groups defaults = new Groups();
            defaults.saveGroups(pluginDir);
            return defaults;
        }
    }

    public void saveGroups(File pluginDir) {
        try {
            FileWriter fw = new FileWriter(new File(pluginDir, groupFile));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(GawdPerms.getGson().toJson(this));
            bw.close();
        } catch (IOException ex) {
            GawdPerms.logger.log(Level.SEVERE, "Error saving Groups!", ex);
        }
    }
}
