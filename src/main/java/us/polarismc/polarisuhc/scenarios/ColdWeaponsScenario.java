package us.polarismc.polarisuhc.scenarios;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;
import us.polarismc.polarisuhc.managers.scenario.Scenario;

@Scenario(name = "ColdWeapons", author = "volcqnn", icon = Material.SNOWBALL,
        description = "Fire Aspect and Flame enchantments are disabled.")
public class ColdWeaponsScenario extends BaseScenario {

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        if (!isEnabled()) return;
        if (event.getEnchantsToAdd().containsKey(Enchantment.FIRE_ASPECT)) {
            event.getEnchantsToAdd().remove(Enchantment.FIRE_ASPECT);
        }
        if (event.getEnchantsToAdd().containsKey(Enchantment.FLAME)) {
            event.getEnchantsToAdd().remove(Enchantment.FLAME);
        }
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        if (!isEnabled()) return;
        ItemStack result = event.getResult();
        if (result != null) {
            removeFireEnchantments(result);
        }
    }

    private void removeFireEnchantments(ItemStack item) {
        if (item.containsEnchantment(Enchantment.FIRE_ASPECT)) {
            item.removeEnchantment(Enchantment.FIRE_ASPECT);
        }
        if (item.containsEnchantment(Enchantment.FLAME)) {
            item.removeEnchantment(Enchantment.FLAME);
        }
    }
}