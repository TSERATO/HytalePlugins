package com.fancyinnovations.fancycore.commands.chat.chatroom;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class ChatRoomCMD extends AbstractCommandCollection {

    public ChatRoomCMD() {
        super("chatroom", "Manage chat rooms");

        this.addSubCommand(new ChatRoomInfoCMD());
    }

}
