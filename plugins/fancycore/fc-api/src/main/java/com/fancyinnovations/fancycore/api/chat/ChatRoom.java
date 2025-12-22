package com.fancyinnovations.fancycore.api.chat;

import com.fancyinnovations.fancycore.api.player.FancyPlayer;

import java.util.List;

public interface ChatRoom {

    String getName();

    List<FancyPlayer> getParticipants();

    void join(FancyPlayer player);

    void leave(FancyPlayer player);

    void broadcastMessage (String message);

    void sendMessage(FancyPlayer sender, String message);

    boolean isMuted();

    void setMuted(boolean muted);

    void clearChat();

    long getCooldown();

    void setCooldown(long cooldown);

}
