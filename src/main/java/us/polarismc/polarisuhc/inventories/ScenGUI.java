package us.polarismc.polarisuhc.inventories;

import fr.mrmicky.fastinv.FastInv;
import me.putindeer.api.util.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.polarismc.polarisuhc.Main;
import us.polarismc.polarisuhc.managers.scenario.BaseScenario;

import java.util.Arrays;
import java.util.List;

public class ScenGUI extends FastInv {

    public ScenGUI(Player player, Main plugin) {
        super(owner -> Bukkit.createInventory(owner, 27, plugin.utils.chat("Scenarios")));
        List<BaseScenario> enabled = plugin.scen.getEnabled();
        //int[] gridSlots = {10, 12, 14, 16, 18, 28, 30, 32, 34};
        int i = 0;
        for (BaseScenario scen : enabled) {
            ItemBuilder item = plugin.utils.ib(scen.getIcon()).name(scen.getDisplayName());

            ItemStack itemScen = item.build();
            itemScen.editMeta(meta -> meta.lore(Arrays.asList(scen.getDescription())));
            setItem(i, itemScen);
            i++;
        }

        ItemBuilder close = plugin.utils.ib(Material.BARRIER)
                        .name("<red>Close");

        setItem(22, close.build(), e ->
                e.getWhoClicked().closeInventory());
        open(player);
    }
}