package com.freitaseric.golphinemc.golphinemc.listener

import com.freitaseric.golphinemc.common.GolphineMC
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener : Listener {
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        sendQuitMessage(event)
    }

    private fun sendQuitMessage(event: PlayerQuitEvent) {
        val player = event.player

        val message =
            "${GolphineMC.GRADIENT_COLORED_NAME} <grey>|</grey> <yellow>${player.name} saiu do servidor</yellow>"

        event.quitMessage(MiniMessage.miniMessage().deserialize(message))
    }
}