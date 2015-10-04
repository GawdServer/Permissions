package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class TakeGroupPerm implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.takegroupperm") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 2) {
                GawdPerms.getManager().takeGroupPermission(args[0], args[1]);
                Chat.sendMessage(player, "Took permission '" + args[1] + "' to group " + args[0]);
            } else {
                Chat.sendMessage(player, "!takegroupperm <group> <permission>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (args.length >= 2) {
            GawdPerms.getManager().takeGroupPermission(args[0], args[1]);
            Chat.sendMessage(sender.name(), "Took permission '" + args[1] + "' to group " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!takegroupperm <group> <permission>");
        }
    }
}
