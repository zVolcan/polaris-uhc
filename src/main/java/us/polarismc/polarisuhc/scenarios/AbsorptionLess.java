package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "AbsorptionLess", author = "volcqnn", icon = Material.GOLDEN_APPLE,
        description = "Absorption hearts are disabled, but golden apples still heal.")
public class AbsorptionLess extends BaseScenario {
    @EventHandler
    public void onGoldenAppleConsume(PlayerItemConsumeEvent event) {
        Material type = event.getItem().getType();
        if (type == Material.GOLDEN_APPLE || type == Material.ENCHANTED_GOLDEN_APPLE) {
            plugin.utils.delay(() -> event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION));
        }
    }
}