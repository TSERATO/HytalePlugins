package com.fancyinnovations.fancycore.listeners;

import com.fancyinnovations.fancycore.api.player.FakeHytalePlayer;
import com.fancyinnovations.fancycore.api.player.FancyPlayer;
import com.fancyinnovations.fancycore.main.FancyCorePlugin;
import com.fancyinnovations.fancycore.player.service.FancyPlayerServiceImpl;
import org.jetbrains.annotations.NotNull;

public class PlayerLeaveListener {

    private final static FancyPlayerServiceImpl playerService = (FancyPlayerServiceImpl) FancyCorePlugin.get().getPlayerService();

    public void onPlayerLeave(PlayerLeaveEvent event) {
        // TODO: use real event and register listener properly

        FancyPlayer fp = playerService.getByUUID(event.getPlayer().getUUID());
        fp.setPlayer(null);

        playerService.removeOnlinePlayer(fp);
    }

    /**
     * Mock PlayerLeaveEvent for demonstration purposes.
     */
    interface PlayerLeaveEvent {
        @NotNull FakeHytalePlayer getPlayer();
    }

}
