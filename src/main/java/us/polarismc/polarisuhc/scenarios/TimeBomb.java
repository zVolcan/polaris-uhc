package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Scenario(name = "TimeBomb", author = "volcqnn", icon = Material.TNT,
        description = "When a player dies, a TNT chest appears at their death location. After 30 seconds, it explodes.")
public class TimeBomb extends BaseScenario {
    private static final int COUNTDOWN_SECONDS = 30;
    private static final float EXPLOSION_POWER = 4.0f;

    private final ConcurrentMap<Location, ArmorStand> holograms = new ConcurrentHashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!isEnabled()) return;

        Location deathLoc = event.getEntity().getLocation();

        deathLoc.getBlock().setType(Material.CHEST);
        if (event.getEntity().getKiller() != null) {
            Chest chest = (Chest) deathLoc.getBlock().getState();
            chest.customName(plugin.utils.chat("Time Bomb"));
        }

        ArmorStand hologram = (ArmorStand) deathLoc.getWorld().spawnEntity(
                deathLoc.clone().add(0.5, 2.5, 0.5), EntityType.ARMOR_STAND);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setInvulnerable(true);
        hologram.setCustomNameVisible(true);
        hologram.customName(plugin.utils.chat("<red>Explodes in " + COUNTDOWN_SECONDS + "s"));
        hologram.setMarker(true);

        holograms.put(deathLoc, hologram);

        plugin.utils.delay(COUNTDOWN_SECONDS * 20, () -> explode(deathLoc, hologram));
    }

    private void explode(Location deathLoc, ArmorStand hologram) {
        deathLoc.getBlock().setType(Material.AIR);

        deathLoc.getWorld().createExplosion(deathLoc, EXPLOSION_POWER, false, false);

        cleanup(hologram);
    }

    private void cleanup(ArmorStand hologram) {
        if (hologram != null && !hologram.isDead()) {
            hologram.remove();
        }
    }

    @Override
    protected void onDisable() {
        holograms.values().forEach(this::cleanup);
        holograms.clear();
    }
}