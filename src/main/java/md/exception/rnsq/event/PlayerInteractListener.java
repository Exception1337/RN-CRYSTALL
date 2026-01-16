package md.exception.rnsq.event;

import md.exception.rnsq.CRYSTALL;
import md.exception.rnsq.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Exception_
 * @since 15 янв. 2026 г.
 */
public final class PlayerInteractListener implements Listener {

    @EventHandler
    public void onCrystalPlace(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getItem() == null) return;
        if (event.getItem().getType() != Material.END_CRYSTAL) return;

        if (event.getClickedBlock() == null) return;
        if (event.getPlayer().hasPermission("rncrystall.bypass")) return;

        Material blockType = event.getClickedBlock().getType();
        if (blockType != Material.OBSIDIAN && blockType != Material.BEDROCK) return;

        Player player = event.getPlayer();

        if (CRYSTALL.getCooldownManager().has(player.getUniqueId())) {
            player.sendMessage(ColorUtil.color(
                    CRYSTALL.getInstance().getConfig().getString("cooldown-message")
                            .replace("%time%", String.valueOf(
                                    CRYSTALL.getCooldownManager().remaining(player.getUniqueId())
                            ))
            ));
            event.setCancelled(true);
            return;
        }

        CRYSTALL.getCooldownManager().set(player.getUniqueId());
    }
}
