package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by vinnie on 10/1/15.
 */
public class AddPlayer implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.addplayer") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 1) {
                GawdPerms.getManager().addPlayer(args[0]);
                Chat.sendMessage(player, "Created player " + args[0]);
            } else {
                Chat.sendMessage(player, "!addplayer <username>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        if (args.length >= 1) {
            GawdPerms.getManager().addPlayer(args[0]);
            Chat.sendMessage(sender.name(), "Created player " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!addplayer <username>");
        }
    }
}