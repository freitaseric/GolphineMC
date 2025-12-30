package com.freitaseric.golphinemc

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.common.ui.ConsoleBanner
import com.freitaseric.golphinemc.events.EventRegistry
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger

@Plugin(
    id = "golphinemc",
    name = "GolphineMC",
    version = GolphineMC.VERSION,
    url = "https://plugins.freitaseric.com/golphinemc",
    authors = ["Eric Freitas"]
)
class GolphineMCVelocity @Inject constructor(private val proxy: ProxyServer, private val logger: Logger) {
    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {
        GolphineLogger.init(VelocityLoggerAdapter(this.logger))

        val banner = ConsoleBanner.generate("Velocity", GolphineMC.VERSION)
        GolphineLogger.info(banner)

        EventRegistry.registerAll(this, proxy.eventManager)
    }
}
