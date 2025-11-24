package com.fancyinnovations.fancycore.metrics;

import com.fancyinnovations.fancycore.main.FancyCorePlugin;

public class PluginMetrics {

    private final static FancyCorePlugin plugin = FancyCorePlugin.get();

    public PluginMetrics() {

    }

    public void register() {
        // TODO: Implement metrics registration
    }

    private double totalAmountPlayers() {
        return plugin.getPlayerStorage().countPlayers();
    }

    private double onlinePlayers() {
        return plugin.getPlayerService().getOnlinePlayers().size();
    }

    private String serverSizeCategory() {
        if (onlinePlayers() == 0) {
            return "empty";
        } else if (onlinePlayers() <= 25) {
            return "small";
        } else if (onlinePlayers() <= 100) {
            return "medium";
        } else if (onlinePlayers() <= 500) {
            return "large";
        } else if (onlinePlayers() > 500) {
            return "very_large";
        }

        return "unknown";
    }
}
