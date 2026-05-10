package us.polarismc.polarisuhc.scenarios;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "SwordLess", author = "volcqnn", icon = Material.DIAMOND_SWORD,
        description = "You cannot use swords.")
public class SwordLessScenario extends BaseScenario {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!isEnabled()) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (event.getItem() != null && isSword(event.getItem().getType())) {
            event.setCancelled(true);
            event.getPlayer().sendActionBar(Component.text("<red>Swords are disabled!"));
        }
    }

    private boolean isSword(Material type) {
        return type == Material.WOODEN_SWORD ||
               type == Material.STONE_SWORD ||
               type == Material.IRON_SWORD ||
               type == Material.GOLDEN_SWORD ||
               type == Material.DIAMOND_SWORD ||
               type == Material.NETHERITE_SWORD;
    }
}