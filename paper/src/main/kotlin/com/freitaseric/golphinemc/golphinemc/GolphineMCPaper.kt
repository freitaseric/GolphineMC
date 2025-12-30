package com.freitaseric.golphinemc.golphinemc

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.common.ui.ConsoleBanner
import com.freitaseric.golphinemc.golphinemc.listener.PlayerJoinListener
import com.freitaseric.golphinemc.golphinemc.listener.PlayerQuitListener
import org.bukkit.plugin.java.JavaPlugin

class GolphineMCPaper : JavaPlugin() {
    override fun onEnable() {
        GolphineLogger.init(PaperLoggerAdapter(this.logger))

        val banner = ConsoleBanner.generate("Paper", GolphineMC.VERSION)
        GolphineLogger.info(banner)

        server.pluginManager.registerEvents(PlayerJoinListener(), this)
        server.pluginManager.registerEvents(PlayerQuitListener(), this)
    }
}
