package com.fancyinnovations.fancycore.config;

import com.fancyinnovations.config.ConfigField;
import com.fancyinnovations.config.ConfigJSON;
import com.fancyinnovations.fancycore.api.FancyCoreConfig;
import com.fancyinnovations.fancycore.main.FancyCorePlugin;

public class FancyCoreConfigImpl implements FancyCoreConfig {

    public static final String LOG_LEVEL_PATH = "settings.logging.level";

    private static final String CONFIG_FILE_PATH = "plugins/FancyHolograms/config.yml";
    private ConfigJSON config;

    public void init() {
        config = new ConfigJSON(FancyCorePlugin.get().getFancyLogger(), CONFIG_FILE_PATH);

        config.addField(new ConfigField<>(
                LOG_LEVEL_PATH,
                "The log level for the plugin (DEBUG, INFO, WARN, ERROR).",
                false,
                "INFO",
                false,
                String.class
        ));
    }

    @Override
    public void reload() {
        config.reload();
    }

    @Override
    public String getLogLevel() {
        return config.get(LOG_LEVEL_PATH);
    }
}
