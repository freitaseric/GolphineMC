package com.freitaseric.golphinemc.golphinemc.events.handlers

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.golphinemc.events.IModEvent
import com.freitaseric.golphinemc.golphinemc.lib.ColorTranslator
import com.freitaseric.golphinemc.golphinemc.lib.managers.NameTagManager
import com.freitaseric.golphinemc.golphinemc.lib.managers.TabListManager
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents

object PlayerJoinHandler : IModEvent {
    private const val FIRST_JOIN_TAG = "golphine.played_before"

    override fun register() {
        ServerPlayConnectionEvents.JOIN.register { handler, _, server ->
            val player = handler.player
            val isFirstLogin = player.commandTags.contains(FIRST_JOIN_TAG)

            if (isFirstLogin) {
                player.addCommandTag(FIRST_JOIN_TAG)

                server.playerManager.broadcast(
                    ColorTranslator.translate("&7[${GolphineMC.COLORED_NAME}&7] &b${player.name} &ecaiu de paraquedas, vamos dar as boas vindas!"),
                    true
                )
            } else {
                server.playerManager.broadcast(
                    ColorTranslator.translate("&7[&a+&7] &b${player.name} &eentrou no servidor! &d:)"), false
                )
            }

            NameTagManager.updateNametag(player, server)
            TabListManager.updateTabList(player, server)
        }
    }
}