package com.freitaseric.golphinemc.golphinemc.lib

import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Formatting

internal fun getLastColors(input: String): Formatting? {
    var lastFormat: Formatting? = null
    val length = input.length

    for (i in 0 until length - 1) {
        val c = input[i]
        if (c == '§' && "0123456789AaBbCcDdEeFf".indexOf(input[i + 1]) > -1) {
            val code = input[i + 1]
            lastFormat = Formatting.byCode(code)
        }
    }
    return lastFormat
}

fun ServerPlayerEntity.getFormattedPing(): String {
    val ping = this.networkHandler.latency

    val color = when {
        ping < 0 -> "&7"
        ping < 100 -> "&a"
        ping < 200 -> "&e"
        else -> "&c"
    }

    val pingString = String.format("%.1f", ping)

    return "&7Ping: $color$pingString"
}

fun MinecraftServer.getFormattedTps(): String {
    val mspt = this.averageTickTime

    var tps = if (mspt <= 0) 20.0 else 1000.0 / mspt

    if (tps > 20.0) tps = 20.0

    val color = when {
        tps >= 18.0 -> "&a"
        tps >= 15.0 -> "&e"
        else -> "&c"
    }

    val tpsString = String.format("%.1f", tps)

    return "&7TPS: $color$tpsString"
}

fun MinecraftServer.getFormattedMspt(): String {
    val mspt = this.averageTickTime
    val color = when {
        mspt < 40.0 -> "&a"
        mspt < 50.0 -> "&e"
        else -> "&c"
    }

    val msptFormatted = String.format("%.1fms", mspt)

    return "&7MSPT: $color$msptFormatted"
}