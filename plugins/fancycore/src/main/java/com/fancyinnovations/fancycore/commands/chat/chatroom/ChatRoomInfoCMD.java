package com.fancyinnovations.fancycore.commands.chat.chatroom;

import com.fancyinnovations.fancycore.api.chat.ChatRoom;
import com.fancyinnovations.fancycore.api.chat.ChatService;
import com.fancyinnovations.fancycore.api.player.FancyPlayer;
import com.fancyinnovations.fancycore.api.player.FancyPlayerService;
import com.fancyinnovations.fancycore.utils.TimeUtils;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatRoomInfoCMD extends CommandBase {

    protected final OptionalArg<String> chatRoomNameArg = this.withOptionalArg("chatroom", "The name of the chat room", ArgTypes.STRING);

    protected ChatRoomInfoCMD() {
        super("info", "Get information about a chat room");
    }

    @Override
    protected void executeSync(@NotNull CommandContext ctx) {
        if (!ctx.isPlayer()) {
            ctx.sendMessage(Message.raw("This command can only be executed by a player."));
            return;
        }

        FancyPlayer fp = FancyPlayerService.get().getByUUID(ctx.sender().getUuid());
        if (fp == null) {
            ctx.sendMessage(Message.raw("FancyPlayer not found."));
            return;
        }

        ChatRoom chatRoom = null;
        if (chatRoomNameArg.provided(ctx)) {
            chatRoom = ChatService.get().getChatRoom(chatRoomNameArg.get(ctx));
        } else {
            chatRoom = fp.getCurrentChatRoom();
        }

        if (chatRoom == null) {
            ctx.sendMessage(Message.raw("Chat room not found."));
            return;
        }

        List<String> nicknames = chatRoom.getWatchers().stream()
                .map(fancyPlayer -> fancyPlayer.getData().getNickname())
                .toList();

        ctx.sendMessage(Message.raw("Chat Room Info:"));
        ctx.sendMessage(Message.raw("- Name: " + chatRoom.getName()));
        ctx.sendMessage(Message.raw("- IsMuted: " + chatRoom.isMuted()));
        ctx.sendMessage(Message.raw("- Cooldown: " + TimeUtils.formatTime(chatRoom.getCooldown())));
        ctx.sendMessage(Message.raw("- Watchers: " + chatRoom.getWatchers().size() + " (" + String.join(", ", nicknames) + ")"));
    }
}
