package tk.coolv1994.plugins.perms.commands;

import tk.coolv1994.plugins.perms.Permissions;
import tk.coolv1994.gawdserver.events.Command;
import tk.coolv1994.gawdserver.utils.Chat;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class TakePerm implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if (Permissions.getManager().hasPermission(player, "perms.take")) {
            if (args.length >= 2) {
                Permissions.getManager().takePermission(args[0], args[1]);
                Chat.sendMessage(player, "Taken permission '" + args[1] + "' from " + args[0]);
            } else {
                Chat.sendMessage(player, "!takeperm <username> <permission>");
            }
        }
    }
}
