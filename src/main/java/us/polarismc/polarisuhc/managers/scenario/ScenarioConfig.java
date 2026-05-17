package us.polarismc.polarisuhc.managers.scenario;

import org.bukkit.configuration.file.YamlConfiguration;
import us.polarismc.polarisuhc.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScenarioConfig extends YamlConfiguration {
    private final Main plugin;
    private final File file;

    public ScenarioConfig(Main plugin, String scenarioId) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder() + "/scenarios", scenarioId + ".yml");
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs())
                throw new IOException("Could not create directories for " + file.getPath());
            if (!file.exists() && !file.createNewFile())
                throw new IOException("Could not create file " + file.getPath());
            load(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File getModeFolder() {
        return file.getParentFile();
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String key, T def) {
        if (!contains(key)) {
            set(key, def);
            save();
        }
        Object value = get(key, def);
        if (value == null) return null;
        if (def instanceof Integer && value instanceof Number n) return (T) (Integer) n.intValue();
        if (def instanceof Long && value instanceof Number n) return (T) (Long) n.longValue();
        if (def instanceof Double && value instanceof Number n) return (T) (Double) n.doubleValue();
        if (def != null && def.getClass().isInstance(value)) return (T) def.getClass().cast(value);
        return def;
    }

    public <T> T getOrDefault(String key, T def, String... comment) {
        T value = getOrDefault(key, def);
        setComments(key, List.of(comment));
        save();
        return value;
    }

    public void set(String key, Object value, boolean persist) {
        set(key, value);
        if (persist) save();
    }

    public void save() {
        try { save(file); } catch (IOException e) {
            plugin.utils.severe("Could not save ScenarioConfig: " + file.getName());
        }
    }

    public void reload() {
        try { load(file); } catch (Exception e) {
            plugin.utils.severe("Could not reload ScenarioConfig: " + file.getName());
        }
    }
}