package com.freitaseric.golphinemc.golphinemc

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.common.ui.ConsoleBanner
import com.freitaseric.golphinemc.golphinemc.events.EventRegistry
import com.freitaseric.golphinemc.golphinemc.lib.TabListScheduler
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GolphineMCFabric : ModInitializer {

    override fun onInitialize() {
        val logger: Logger = LoggerFactory.getLogger("GolphineMC")
        GolphineLogger.init(FabricLoggerAdapter(logger))

        val banner = ConsoleBanner.generate("Fabric", GolphineMC.VERSION)
        GolphineLogger.info(banner)

        EventRegistry.registerAll()
        TabListScheduler.register()
    }
}
