package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "Switcheroo", author = "volcqnn", icon = Material.SPECTRAL_ARROW,
        description = "When a projectile hits a player, your positions get swapped.")
public class SwitcherooScenario extends BaseScenario {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!isEnabled()) return;
        if (!(event.getEntity() instanceof Projectile projectile)) return;
        if (event.getHitEntity() == null) return;
        if (!(event.getHitEntity() instanceof org.bukkit.entity.Player target)) return;

        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof org.bukkit.entity.Player shooterPlayer)) return;

        org.bukkit.Location targetLoc = target.getLocation();
        org.bukkit.Location shooterLoc = shooterPlayer.getLocation();

        target.teleport(shooterLoc);
        shooterPlayer.teleport(targetLoc);

        target.getWorld().playSound(targetLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
        shooterPlayer.getWorld().playSound(shooterLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
    }
}