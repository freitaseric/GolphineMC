package com.freitaseric.golphinemc.golphinemc.lib

import com.freitaseric.golphinemc.golphinemc.lib.managers.TabListManager
import kotlinx.coroutines.*
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents

object TabListScheduler {
    private var job: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)

    fun register() {
        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            job = scope.launch {
                while (isActive) {
                    delay(10_000)

                    server.execute {
                        server.playerManager.playerList.forEach { player ->
                            TabListManager.updateTabList(player, server)
                        }
                    }
                }
            }
        }

        ServerLifecycleEvents.SERVER_STOPPING.register {
            job?.cancel()
        }
    }
}