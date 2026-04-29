package me.yourname.anticheat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;

public class FlyCheck implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // 1. Ігноруємо адмінів та донатерів з правом "anticheat.bypass"
        if (player.hasPermission("anticheat.bypass")) return;

        // 2. Ігноруємо тих, у кого ввімкнено легальний /fly або Creative/Spectator
        if (player.getAllowFlight() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;

        // 3. Проста логіка: якщо гравець у повітрі та рухається вгору без блоків під ногами
        if (event.getTo().getY() > event.getFrom().getY()) {
            if (player.getLocation().subtract(0, 1, 0).getBlock().getType().isAir() && 
                player.getFallDistance() == 0) {
                
                // Повертаємо гравця назад на землю
                event.setTo(event.getFrom());
                player.sendMessage("§c[AC] Політ заборонено!");
            }
        }
    }
}
