package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class GivePlayerPerm implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.giveplayerperm") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 2) {
                GawdPerms.getManager().givePlayerPermission(args[0], args[1]);
                Chat.sendMessage(player, "Gave permission '" + args[1] + "' to player " + args[0]);
            } else {
                Chat.sendMessage(player, "!giveplayerperm <username> <permission>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        if (args.length >= 2) {
            GawdPerms.getManager().givePlayerPermission(args[0], args[1]);
            Chat.sendMessage(sender.name(), "Gave permission '" + args[1] + "' to player " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!giveplayerperm <username> <permission>");
        }
    }
}
