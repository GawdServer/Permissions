package tk.coolv1994.plugins.perms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tk.coolv1994.gawdapi.perms.Permissions;
import tk.coolv1994.gawdapi.plugin.Plugin;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class GawdPerms implements Plugin {
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
        manager = (PermManager) Permissions.getManager();
        manager.loadPerms();
    }

    @Override
    public void shutdown() {
        manager.savePerms();
    }
}
