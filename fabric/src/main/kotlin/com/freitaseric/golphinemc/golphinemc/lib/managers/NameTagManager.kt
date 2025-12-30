package com.freitaseric.golphinemc.golphinemc.lib.managers

import com.freitaseric.golphinemc.common.core.PlatformResolver
import com.freitaseric.golphinemc.golphinemc.lib.getLastColors
import net.luckperms.api.LuckPermsProvider
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

object NameTagManager {
    fun updateNametag(player: ServerPlayerEntity, server: MinecraftServer) {
        val scoreboard = server.scoreboard
        val uuid = player.uuid

        val lpProvider = LuckPermsProvider.get()
        val user = lpProvider.userManager.getUser(uuid) ?: return

        val metadata = user.cachedData.metaData

        val prefix = metadata.prefix?.replace("&", "§") ?: ""
        val suffix = metadata.suffix?.replace("&", "§") ?: ""

        val weight = metadata.getMetaValue("weight")?.toIntOrNull() ?: 0

        val sortOrder = 9999 - weight
        val teamName = String.format("%04d_%s", sortOrder, player.name.string.take(10))

        val icon = PlatformResolver.getIcon(uuid)

        val team = scoreboard.getTeam(teamName) ?: scoreboard.addTeam(teamName)

        team.suffix = Text.literal(prefix)
        team.suffix = Text.literal(" $icon$suffix")

        val lastColor = getLastColors(prefix)
        if (lastColor != null) {
            team.color = lastColor
        }

        scoreboard.addScoreHolderToTeam(player.name.string, team)
    }

}