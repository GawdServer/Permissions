package io.github.gawdserver.gawdperms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.gawdserver.api.perms.Permissions;
import io.github.gawdserver.api.plugin.Plugin;
import io.github.gawdserver.api.plugin.PluginDir;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class GawdPerms implements Plugin {
    public static final Logger logger = Logger.getLogger("Permissions");
    private static PermManager manager;
    private static Gson gson;

    public GawdPerms() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.enableComplexMapKeySerialization();
        builder.serializeNulls();
        builder.disableHtmlEscaping();
        gson = builder.create();
    }

    public static PermManager getManager() {
        return manager;
    }

    public static Gson getGson() {
        return gson;
    }

    @Override
    public void startup() {
        File pluginDir = new File(PluginDir.getPluginDir(), "Permissions");
        pluginDir.mkdirs();
        manager = (PermManager) Permissions.getManager();
        manager.setPluginDir(pluginDir);
        manager.loadPerms();
    }

    @Override
    public void shutdown() {
        manager.savePerms();
    }
}
