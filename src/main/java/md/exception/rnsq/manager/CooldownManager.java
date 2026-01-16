package md.exception.rnsq.manager;

import md.exception.rnsq.CRYSTALL;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Exception_
 * @since 15 янв. 2026 г.
 */
public final class CooldownManager {
    static Map<UUID, Long> cooldown = new HashMap<>();

    public void set(UUID uuid){
        if (!has(uuid)) {
            cooldown.put(uuid, System.currentTimeMillis() + CRYSTALL.getInstance().getConfig().getLong("cooldown-time"));
        }
    }

    public boolean has(UUID uuid){
        if (cooldown.containsKey(uuid)) {
            if (System.currentTimeMillis() >= cooldown.get(uuid)) {
                cooldown.remove(uuid);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public long remaining(UUID uuid){
        Long end = cooldown.get(uuid);
        if (end == null) return 0;

        long millis = end - System.currentTimeMillis();
        if (millis <= 0) {
            cooldown.remove(uuid);
            return 0;
        }

        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }
}
