package com.fancyinnovations.fancycore.api.player;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FancyPlayerRegistry {

    void register(FancyPlayer player);

    void unregister(FancyPlayer player);

    boolean contains(UUID uuid);

    Optional<FancyPlayer> get(UUID uuid);

    @Nullable FancyPlayer mustGet(UUID uuid);

    Collection<FancyPlayer> getAll();

    void clear();
}
