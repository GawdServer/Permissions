package tk.coolv1994.plugins.perms.commands;

import tk.coolv1994.gawdapi.events.Command;
import tk.coolv1994.gawdapi.utils.Chat;
import tk.coolv1994.plugins.perms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class TakePerm implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.take")) {
            if (args.length >= 2) {
                GawdPerms.getManager().takePermission(args[0], args[1]);
                Chat.sendMessage(player, "Taken permission '" + args[1] + "' from " + args[0]);
            } else {
                Chat.sendMessage(player, "!takeperm <username> <permission>");
            }
        }
    }
}
