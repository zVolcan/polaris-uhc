package us.polarismc.polarisuhc.managers.scenario.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import us.polarismc.polarisuhc.Main;

import java.util.Objects;

public class ReloadScenarios implements CommandExecutor {
    private final Main plugin;

    public ReloadScenarios(Main plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("reloadscenarios")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        plugin.scen.reload();
        plugin.utils.message(sender, "Reloaded scenarios! Toggling an scenario will not have any effect until the server is restarted.");
        return true;
    }
}
