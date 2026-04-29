package me.yourname.anticheat;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class MyAntiCheat extends JavaPlugin {

    @Override
    public void onLoad() {
        // Инициализация пакетного движка
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();
        // Регистрация нашего слушателя проверок
        getServer().getPluginManager().registerEvents(new FlyCheck(), this);
        PacketEvents.getAPI().getEventManager().registerListener(new ReachCheck());
        getLogger().info("MyAntiCheat успешно запущен!");
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
