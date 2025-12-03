package com.fancyinnovations.fancycore.config;

import com.fancyinnovations.config.ConfigField;
import com.fancyinnovations.config.ConfigJSON;
import com.fancyinnovations.fancycore.api.FancyCoreConfig;
import com.fancyinnovations.fancycore.main.FancyCorePlugin;

public class FancyCoreConfigImpl implements FancyCoreConfig {

    public static final String LOG_LEVEL_PATH = "settings.logging.level";
    public static final String EVENT_DISCORD_WEBHOOK_URL_PATH = "settings.events.discord_webhook_url";
    public static final String EVENT_DISCORD_NOTIFICATIONS = "settings.events.notifications_enabled";

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

        config.addField(new ConfigField<>(
                EVENT_DISCORD_WEBHOOK_URL_PATH,
                "The Discord webhook URL for event notifications. Leave empty to disable all event notifications.",
                false,
                "",
                false,
                String.class
        ));

        config.addField(new ConfigField<>(
                EVENT_DISCORD_NOTIFICATIONS,
                "Enable Discord notifications for events.",
                false,
                new String[] {"PlayerPunishedEvent", "PlayerReportedEvent"},
                false,
                String[].class
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

    @Override
    public String getEventDiscordWebhookUrl() {
        return config.get(EVENT_DISCORD_WEBHOOK_URL_PATH);
    }

    @Override
    public String[] getEventDiscordNotifications() {
        return config.get(EVENT_DISCORD_NOTIFICATIONS);
    }
}
