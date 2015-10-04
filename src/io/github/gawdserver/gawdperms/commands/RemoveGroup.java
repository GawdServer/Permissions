package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by vinnie on 10/1/15.
 */
public class RemoveGroup implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.removegroup") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            if (args.length >= 1) {
                GawdPerms.getManager().removeGroup(args[0]);
                Chat.sendMessage(player, "Removed group " + args[0]);
            } else {
                Chat.sendMessage(player, "!removegroup <group>");
            }
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        if (args.length >= 1) {
            GawdPerms.getManager().removeGroup(args[0]);
            Chat.sendMessage(sender.name(), "Removed group " + args[0]);
        } else {
            Chat.sendMessage(sender.name(), "!removegroup <group>");
        }
    }
}