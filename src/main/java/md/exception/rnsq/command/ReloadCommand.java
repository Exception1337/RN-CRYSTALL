package md.exception.rnsq.command;

import md.exception.rnsq.CRYSTALL;
import md.exception.rnsq.util.ColorUtil;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Exception_
 * @since 16 янв. 2026 г.
 */
public final class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            sender.sendMessage(ColorUtil.color(CRYSTALL.getInstance().getConfig().getString("only-player")));
            return false;
        }

        if (!player.hasPermission("rncrystall.admin")){
            player.sendMessage(ColorUtil.color(CRYSTALL.getInstance().getConfig().getString("no-perm")));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ColorUtil.color(CRYSTALL.getInstance().getConfig().getString("command-not-found")));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            CRYSTALL.getInstance().reloadConfig();
            player.sendMessage(ColorUtil.color(CRYSTALL.getInstance().getConfig().getString("reload-complete")));
        } else {
            player.sendMessage(ColorUtil.color(CRYSTALL.getInstance().getConfig().getString("command-not-found")));
        }

        return true;
    }
}
