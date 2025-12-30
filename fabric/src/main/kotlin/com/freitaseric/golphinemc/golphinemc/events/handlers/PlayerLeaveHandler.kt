package com.freitaseric.golphinemc.golphinemc.events.handlers

import com.freitaseric.golphinemc.golphinemc.events.IModEvent
import com.freitaseric.golphinemc.golphinemc.lib.ColorTranslator
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents

object PlayerLeaveHandler : IModEvent {
    override fun register() {
        ServerPlayConnectionEvents.DISCONNECT.register { handler, server ->
            val player = handler.player

            server.playerManager.broadcast(
                ColorTranslator.translate("&7[&c-&7] &b${player.name} &esaiu do servidor. &d:("), false
            )
        }
    }
}