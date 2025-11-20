package com.fancyinnovations.fancycore.player.registry;

import com.fancyinnovations.fancycore.api.player.FancyPlayer;
import com.fancyinnovations.fancycore.api.player.FancyPlayerRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FancyPlayerRegistryImpl implements FancyPlayerRegistry {

    private final Map<UUID, FancyPlayer> players;

    public FancyPlayerRegistryImpl() {
        this.players = new ConcurrentHashMap<>();
    }

    @Override
    public void register(FancyPlayer player) {
        if (players.containsKey(player.getUUID())) {
            throw new IllegalArgumentException("Player with UUID " + player.getUUID() + " is already registered.");
        }

        players.put(player.getUUID(), player);
    }

    @Override
    public void unregister(FancyPlayer player) {
        players.remove(player.getUUID());
    }

    @Override
    public boolean contains(UUID uuid) {
        return players.containsKey(uuid);
    }

    @Override
    public Optional<FancyPlayer> get(UUID uuid) {
        return Optional.ofNullable(players.get(uuid));
    }

    @Override
    public FancyPlayer mustGet(UUID uuid) {
        return players.get(uuid);
    }

    @Override
    public Collection<FancyPlayer> getAll() {
        return Collections.unmodifiableCollection(players.values());
    }

    @Override
    public void clear() {
        players.clear();
    }
}
