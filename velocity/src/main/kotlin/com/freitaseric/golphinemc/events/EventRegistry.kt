package com.freitaseric.golphinemc.events

import com.freitaseric.golphinemc.GolphineMCVelocity
import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.events.handlers.ChatHandler
import com.freitaseric.golphinemc.events.handlers.PingHandler
import com.velocitypowered.api.event.EventManager

object EventRegistry {
    private val events: List<IPluginEvent> = listOf(
        ChatHandler(), PingHandler()
    )

    fun registerAll(plugin: GolphineMCVelocity, eventManager: EventManager) {
        events.forEach {
            try {
                eventManager.register(plugin, it)
            } catch (e: Exception) {
                GolphineLogger.error("Server event registration error: ${it::class.simpleName}")
            }
        }
        GolphineLogger.info("Registered ${events.size} server events.")
    }
}