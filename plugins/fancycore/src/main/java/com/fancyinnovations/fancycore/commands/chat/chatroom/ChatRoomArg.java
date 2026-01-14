package com.fancyinnovations.fancycore.commands.chat.chatroom;

import com.fancyinnovations.fancycore.api.chat.ChatRoom;
import com.fancyinnovations.fancycore.api.chat.ChatService;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.ParseResult;
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChatRoomArg {

    public static final String NAME = "chatroom";
    public static final String DESCRIPTION = "The name of the chat room";

    public static final SingleArgumentType<ChatRoom> TYPE = new SingleArgumentType<ChatRoom>("ChatRoom", "The name of the chatroom", new String[]{"global", "staff"}) {

        public @Nullable ChatRoom parse(@Nonnull String input, @Nonnull ParseResult parseResult) {
            ChatRoom chatRoom = ChatService.get().getChatRoom(input);
            if (chatRoom == null) {
                parseResult.fail(Message.raw("Chat room \"" + input + "\" not found."));
                return null;
            }

            return chatRoom;
        }
    };

}
