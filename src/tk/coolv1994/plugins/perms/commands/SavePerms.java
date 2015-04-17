package tk.coolv1994.plugins.perms.commands;

import tk.coolv1994.gawdapi.events.Command;
import tk.coolv1994.gawdapi.utils.Chat;
import tk.coolv1994.plugins.perms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class SavePerms implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.save")) {
            GawdPerms.getManager().savePerms();
            Chat.sendMessage(player, "Permissions saved.");
        }
    }
}
