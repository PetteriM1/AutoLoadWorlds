package me.petterim1.autoloadworlds;

import cn.nukkit.plugin.PluginBase;

import java.io.File;
import java.util.List;

public class Plugin extends PluginBase {

    public void onEnable() {
        saveDefaultConfig();
        List<String> doNotLoad = getConfig().getStringList("doNotLoad");
        getLogger().info("Loading worlds...");
        int count = 0;
        try {
            for (File fs : new File(new File("").getCanonicalPath() + "/worlds/").listFiles()) {
                if ((fs.isDirectory() && !doNotLoad.contains(fs.getName()) && !getServer().isLevelLoaded(fs.getName()))) {
                    getServer().loadLevel(fs.getName());
                    count++;
                }
            }
            getLogger().info("Loaded " + count + " worlds");
        } catch (Exception e) {
            this.getLogger().error("Unable to load worlds", e);
        }
    }
}
