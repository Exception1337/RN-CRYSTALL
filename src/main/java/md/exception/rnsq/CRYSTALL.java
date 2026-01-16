package md.exception.rnsq;

import lombok.Getter;
import md.exception.rnsq.command.ReloadCommand;
import md.exception.rnsq.event.PlayerInteractListener;
import md.exception.rnsq.manager.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Exception_
 * @since 16 янв. 2026 г.
 */
public final class CRYSTALL extends JavaPlugin {
    @Getter
    static CRYSTALL instance;


    @Getter
    static CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("rncrystall").setExecutor(new ReloadCommand());
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

        System.out.println("=========================");
        System.out.println();
        System.out.println(" ? Author: Exception_");
        System.out.println(" ? Plugin: RN-CRYSTALL");
        System.out.println(" ? Version: v1.0");
        System.out.println();
        System.out.println("=========================");

        cooldownManager = new CooldownManager();
    }
}
