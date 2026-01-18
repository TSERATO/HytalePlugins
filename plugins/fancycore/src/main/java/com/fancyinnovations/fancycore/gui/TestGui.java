package com.fancyinnovations.fancycore.gui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TestGui extends InteractiveCustomUIPage<TestGui.TestData> {

    private int counter = 0;
    private final List<String> items = new ArrayList<>();

    public TestGui(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, TestData.CODEC);

        // Add some initial items
        items.add("Initial Item 1");
        items.add("Initial Item 2");
        items.add("Initial Item 3");
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref,
                      @Nonnull UICommandBuilder uiCommandBuilder,
                      @Nonnull UIEventBuilder uiEventBuilder,
                      @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/FancyCore_TestPage.ui");

        // Set initial counter value
        uiCommandBuilder.set("#CounterLabel.Text", "Items: " + items.size());

        // Add event for add button
        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#IncrementButton",
            EventData.of("Button", "AddItem"),
            false
        );

        // Build the list of items
        buildItemList(uiCommandBuilder, uiEventBuilder);
    }

    private void buildItemList(@Nonnull UICommandBuilder uiCommandBuilder,
                               @Nonnull UIEventBuilder uiEventBuilder) {
        // Clear existing items
        uiCommandBuilder.clear("#ItemCards");

        // Add each item to the list
        for (int i = 0; i < items.size(); i++) {
            uiCommandBuilder.append("#ItemCards", "Pages/FancyCore_TestEntry.ui");
            uiCommandBuilder.set("#ItemCards[" + i + "] #ItemName.Text", items.get(i));
            uiCommandBuilder.set("#ItemCards[" + i + "] #ItemDescription.Text", "Item #" + (i + 1) + " - Click to remove");

            // Add click event for each item
            uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#ItemCards[" + i + "]",
                EventData.of("Button", "RemoveItem").append("ItemIndex", String.valueOf(i)),
                false
            );
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref,
                                @Nonnull Store<EntityStore> store,
                                @Nonnull TestData data) {
        super.handleDataEvent(ref, store, data);

        // Handle add item button
        if ("AddItem".equals(data.button)) {
            counter++;
            items.add("New Item " + counter);

            // Create new builders and rebuild the UI
            UICommandBuilder commandBuilder = new UICommandBuilder();
            UIEventBuilder eventBuilder = new UIEventBuilder();
            commandBuilder.set("#CounterLabel.Text", "Items: " + items.size());
            buildItemList(commandBuilder, eventBuilder);
            this.sendUpdate(commandBuilder, eventBuilder, false);
        }

        // Handle remove item button
        if ("RemoveItem".equals(data.button) && data.itemIndex != null) {
            try {
                int index = Integer.parseInt(data.itemIndex);
                if (index >= 0 && index < items.size()) {
                    items.remove(index);

                    // Create new builders and rebuild the UI
                    UICommandBuilder commandBuilder = new UICommandBuilder();
                    UIEventBuilder eventBuilder = new UIEventBuilder();
                    commandBuilder.set("#CounterLabel.Text", "Items: " + items.size());
                    buildItemList(commandBuilder, eventBuilder);
                    this.sendUpdate(commandBuilder, eventBuilder, false);
                }
            } catch (NumberFormatException e) {
                // Ignore invalid index
            }
        }
    }

    public static class TestData {
        static final String KEY_BUTTON = "Button";
        static final String KEY_ITEM_INDEX = "ItemIndex";

        public static final BuilderCodec<TestData> CODEC =
                BuilderCodec.<TestData>builder(TestData.class, TestData::new)
                        .addField(new KeyedCodec<>(KEY_BUTTON, Codec.STRING),
                                 (data, s) -> data.button = s,
                                 data -> data.button)
                        .addField(new KeyedCodec<>(KEY_ITEM_INDEX, Codec.STRING),
                                 (data, s) -> data.itemIndex = s,
                                 data -> data.itemIndex)
                        .build();

        private String button;
        private String itemIndex;
    }
}

