package com.freitaseric.golphinemc.golphinemc.events

import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.golphinemc.events.handlers.PlayerJoinHandler
import com.freitaseric.golphinemc.golphinemc.events.handlers.PlayerLeaveHandler

object EventRegistry {
    private val events: List<IModEvent> = listOf(
        PlayerJoinHandler, PlayerLeaveHandler
    )

    fun registerAll() {
        events.forEach {
            try {
                it.register()
            } catch (e: Exception) {
                GolphineLogger.error("Server event registration error: ${it::class.simpleName}")
            }
        }
        GolphineLogger.info("Registered ${events.size} server events.")
    }
}