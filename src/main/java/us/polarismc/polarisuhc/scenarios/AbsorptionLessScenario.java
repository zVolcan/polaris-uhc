package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "AbsorptionLess", author = "volcqnn", icon = Material.GOLDEN_APPLE,
        description = "Absorption hearts are disabled, but golden apples still heal.")
public class AbsorptionLessScenario extends BaseScenario {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!isEnabled()) return;
        if (event.getEntity() instanceof org.bukkit.entity.Player player) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.removePotionEffect(PotionEffectType.ABSORPTION), 2);
        }
    }
}