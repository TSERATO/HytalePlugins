package com.fancyinnovations.fancycore.api.moderation;

import com.fancyinnovations.fancycore.api.player.FancyPlayer;

import java.util.UUID;

public interface PlayerReport {

    UUID id();

    long reportedAt();

    FancyPlayer reportedPlayer();

    FancyPlayer reportingPlayer();

    String reason();

    boolean isResolved();

    long resolvedAt();

}
