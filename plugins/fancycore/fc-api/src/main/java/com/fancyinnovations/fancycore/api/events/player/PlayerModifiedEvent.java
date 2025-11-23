package com.fancyinnovations.fancycore.api.events.player;

import com.fancyinnovations.fancycore.api.player.FancyPlayer;

public class PlayerModifiedEvent extends PlayerEvent {

    private final FancyPlayer newPlayerData;
    private final ModifiedField modifiedField;

    public PlayerModifiedEvent(FancyPlayer oldPlayerData, FancyPlayer newPlayerData, ModifiedField modifiedField) {
        super(oldPlayerData);
        this.newPlayerData = newPlayerData;
        this.modifiedField = modifiedField;
    }

    public FancyPlayer getOldPlayerData() {
        return getPlayer();
    }

    public FancyPlayer getNewPlayerData() {
        return newPlayerData;
    }

    public ModifiedField getModifiedField() {
        return modifiedField;
    }

    public enum ModifiedField {
        NICKNAME,
        CHAT_COLOR,
        BALANCE,
        FIRST_LOGIN_TIME,
        PLAY_TIME
    }
}
