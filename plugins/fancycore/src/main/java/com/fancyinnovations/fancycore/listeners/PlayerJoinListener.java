package com.fancyinnovations.fancycore.listeners;

import com.fancyinnovations.fancycore.api.moderation.Punishment;
import com.fancyinnovations.fancycore.api.player.FakeHytalePlayer;
import com.fancyinnovations.fancycore.api.player.FancyPlayer;
import com.fancyinnovations.fancycore.main.FancyCorePlugin;
import com.fancyinnovations.fancycore.player.service.FancyPlayerServiceImpl;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener {

    private final static FancyPlayerServiceImpl playerService = (FancyPlayerServiceImpl) FancyCorePlugin.get().getPlayerService();

    public void onPlayerJoin(PlayerJoinEvent event) {
        // TODO: use real event and register listener properly

        FancyPlayer fp = playerService.getByUUID(event.getPlayer().getUUID());
        Punishment punishment = fp.isBanned();
        if (punishment != null) {
            event.cancel();
            event.getPlayer().kick("You are banned from this server."); //TODO: replace with translated message (include ban reason and duration)
            return;
        }


        fp.setPlayer(event.getPlayer());

        playerService.addOnlinePlayer(fp);
    }

    /**
     * Mock PlayerJoinEvent for demonstration purposes.
     */
    interface PlayerJoinEvent {
        @NotNull FakeHytalePlayer getPlayer();

        void cancel();
    }

}
