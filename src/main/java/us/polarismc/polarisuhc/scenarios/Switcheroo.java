package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "Switcheroo", author = "volcqnn", icon = Material.SPECTRAL_ARROW,
        description = "When a projectile you shoot hits a player, your positions will be swapped.")
public class Switcheroo extends BaseScenario {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (event.getHitEntity() == null) return;
        if (!(event.getHitEntity() instanceof Player target)) return;

        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player shooterPlayer)) return;

        Location targetLoc = target.getLocation();
        Location shooterLoc = shooterPlayer.getLocation();

        target.teleport(shooterLoc);
        shooterPlayer.teleport(targetLoc);

        target.getWorld().playSound(targetLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
        shooterPlayer.getWorld().playSound(shooterLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
    }
}