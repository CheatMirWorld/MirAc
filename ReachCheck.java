package me.yourname.anticheat;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ReachCheck extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        // Проверяем, что это пакет взаимодействия с сущностью (удар)
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            Player player = (Player) event.getPlayer();
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event.getByteBuf());

            // Если это атака
            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                Entity target = Bukkit.getEntity(wrapper.getEntityId());
                
                if (target != null) {
                    double distance = player.getLocation().distance(target.getLocation());

                    // Если дистанция больше 3.8 блоков (с запасом на пинг)
                    if (distance > 3.8) {
                        event.setCancelled(true); // Отменяем удар
                        player.sendMessage("§c[AntiCheat] Не бейте так далеко!");
                        Bukkit.getLogger().warning(player.getName() + " не прошел Reach: " + distance);
                    }
                }
            }
        }
    }
}
