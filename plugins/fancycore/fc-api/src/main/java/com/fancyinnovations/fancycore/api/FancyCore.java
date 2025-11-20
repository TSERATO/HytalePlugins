package com.fancyinnovations.fancycore.api;

import com.fancyinnovations.fancycore.api.player.FancyPlayerRegistry;
import com.fancyinnovations.fancycore.api.player.FancyPlayerStorage;
import de.oliver.fancyanalytics.logger.ExtendedFancyLogger;

public interface FancyCore {

    ExtendedFancyLogger getFancyLogger();

    FancyPlayerStorage getPlayerStorage();
    FancyPlayerRegistry getPlayerRegistry();

}
