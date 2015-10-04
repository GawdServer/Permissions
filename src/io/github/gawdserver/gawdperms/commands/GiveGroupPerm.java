package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class GiveGroupPerm implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.givegroupperm") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 2) {
                GawdPerms.getManager().giveGroupPermission(args[0], args[1]);
                Chat.sendMessage(player, "Gave permission '" + args[1] + "' to group " + args[0]);
            } else {
                Chat.sendMessage(player, "!givegroupperm <group> <permission>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        if (args.length >= 2) {
            GawdPerms.getManager().giveGroupPermission(args[0], args[1]);
            Chat.sendMessage(sender.name(), "Gave permission '" + args[1] + "' to group " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!givegroupperm <group> <permission>");
        }
    }
}
