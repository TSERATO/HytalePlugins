package com.fancyinnovations.fancycore.player.service;

import com.fancyinnovations.fancycore.api.player.FancyPlayer;
import com.fancyinnovations.fancycore.main.FancyCorePlugin;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CleanUpPlayerCacheRunnable implements Runnable{

    private final FancyPlayerServiceImpl service;

    public CleanUpPlayerCacheRunnable() {
        this.service = (FancyPlayerServiceImpl) FancyCorePlugin.get().getPlayerService();
    }

    public ScheduledFuture<?> schedule() {
        return FancyCorePlugin.get().getThreadPool().scheduleWithFixedDelay(
                this,
                10,
                30,
                TimeUnit.MINUTES
        );
    }

    @Override
    public void run() {
        for (FancyPlayer fp : service.getAll()) {
            //TODO: uncomment once FancyPlayer#isOnline is implemented
//            if (!fp.isOnline()) {
//                service.removePlayerFromCache(fp.getUUID());
//            }
        }
    }
}
