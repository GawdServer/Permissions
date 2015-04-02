package tk.coolv1994.plugins.perms.commands;

import tk.coolv1994.plugins.perms.Permissions;
import tk.coolv1994.gawdserver.events.Command;
import tk.coolv1994.gawdserver.utils.Chat;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class SavePerms implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if (Permissions.getManager().hasPermission(player, "perms.save")) {
            Permissions.getManager().savePerms();
            Chat.sendMessage(player, "Permissions saved.");
        }
    }
}
