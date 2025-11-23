package com.fancyinnovations.fancycore.api.events.player;

import com.fancyinnovations.fancycore.api.moderation.Punishment;
import com.fancyinnovations.fancycore.api.player.FancyPlayer;

/**
 * Event fired when a player receives a punishment.
 */
public class PlayerPunishedEvent extends PlayerEvent {

    private final Punishment punishment;

    public PlayerPunishedEvent(FancyPlayer player, Punishment punishment) {
        super(player);
        this.punishment = punishment;
    }

    /**
     * Returns the punishment that was applied to the player.
     *
     * @return the Punishment object
     */
    public Punishment getPunishment() {
        return punishment;
    }
}
