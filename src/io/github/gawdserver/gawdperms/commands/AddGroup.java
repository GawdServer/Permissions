package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by vinnie on 10/1/15.
 */
public class AddGroup implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.addgroup") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 1) {
                GawdPerms.getManager().addGroup(args[0]);
                Chat.sendMessage(player, "Created group " + args[0]);
            } else {
                Chat.sendMessage(player, "!addgroup <group>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        if (args.length >= 1) {
            GawdPerms.getManager().addGroup(args[0]);
            Chat.sendMessage(sender.name(), "Created group " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!addgroup <group>");
        }
    }
}