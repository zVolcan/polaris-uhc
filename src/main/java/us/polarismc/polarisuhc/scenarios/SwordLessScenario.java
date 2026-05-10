package us.polarismc.polarisuhc.scenarios;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "SwordLess", author = "volcqnn", icon = Material.DIAMOND_SWORD,
        description = "You cannot use swords.")
public class SwordLessScenario extends BaseScenario {

    @EventHandler
    public void onPlayerInteract(EntityDamageByEntityEvent event) {
        if (!isEnabled()) return;

        if (event.getDamager() instanceof Player player && isSword(player.getInventory().getItemInMainHand().getType())) {
            event.setCancelled(true);
            player.sendActionBar(MiniMessage.miniMessage().deserialize("<red>Swords are disabled!"));
        }
    }

    @EventHandler
    public void onCraftSword(CraftItemEvent event) {
        if (event.getCurrentItem() != null && isSword(event.getCurrentItem().getType())) {
            event.setCancelled(true);
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