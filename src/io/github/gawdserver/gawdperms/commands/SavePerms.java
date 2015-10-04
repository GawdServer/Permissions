package io.github.gawdserver.gawdperms.commands;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;
import io.github.gawdserver.gawdperms.GawdPerms;

/**
 * Created by Vinnie on 2/18/2015.
 */
public class SavePerms implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (GawdPerms.getManager().hasPermission(player, "perms.saveperms") ||
                GawdPerms.getManager().hasPermission(player, "perms.*")) {
            GawdPerms.getManager().savePerms();
            Chat.sendMessage(player, "Permissions saved.");
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (sender != Sender.CONSOLE) {
            return;
        }
        GawdPerms.getManager().savePerms();
        Chat.sendMessage(sender.name(), "Permissions saved.");
    }
}
