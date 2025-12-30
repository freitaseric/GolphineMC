package com.freitaseric.golphinemc.golphinemc.lib.managers

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.golphinemc.lib.ColorTranslator
import com.freitaseric.golphinemc.golphinemc.lib.getFormattedPing
import com.freitaseric.golphinemc.golphinemc.lib.getFormattedTps
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity

object TabListManager {
    fun updateTabList(player: ServerPlayerEntity, server: MinecraftServer) {
        val header = ColorTranslator.translate(
            """
            
            ${GolphineMC.COLORED_NAME} &7★ &rSurvival ⛏️
            
            &8----------[ &c${server.currentPlayerCount}&7/&${server.maxPlayerCount} &8]----------
            
        """.trimIndent()
        )
        val footer = ColorTranslator.translate(
            """
            
            &8------------------------------
            
            ${player.getFormattedPing()} &8| ${server.getFormattedTps()}
            
        """.trimIndent()
        )

        val packet = PlayerListHeaderS2CPacket(header, footer)
        player.networkHandler.sendPacket(packet)
    }
}