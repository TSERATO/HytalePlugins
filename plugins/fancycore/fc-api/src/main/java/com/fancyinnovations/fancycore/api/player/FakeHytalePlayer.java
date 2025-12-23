package com.fancyinnovations.fancycore.api.player;

import java.util.UUID;

/**
 * Represents a fake Player object for the Hytale API.
 * This interface will be replaced with the actual Hytale Player class when the Hytale API is available.
 */
public interface FakeHytalePlayer {

    // TODO (HTEA): Delete this once replaced with real player class

    UUID getUUID();

    String getUsername();

    void kick(String reason);

}
