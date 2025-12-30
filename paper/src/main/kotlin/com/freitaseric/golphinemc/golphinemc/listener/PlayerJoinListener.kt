package com.freitaseric.golphinemc.golphinemc.listener

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.common.core.PlatformResolver
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.luckperms.api.LuckPermsProvider
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {
    private val serializer = LegacyComponentSerializer.legacyAmpersand()

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        sendJoinMessage(event)
        updateNametag(event.player)
    }

    private fun sendJoinMessage(event: PlayerJoinEvent) {
        val player = event.player

        val message = "${GolphineMC.GRADIENT_COLORED_NAME} <grey>|</grey> <yellow>${player.name} entrou no servidor</yellow>"

        event.joinMessage(MiniMessage.miniMessage().deserialize(message))
    }

    private fun updateNametag(player: Player) {
        val uuid = player.uniqueId

        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard

        player.scoreboard = scoreboard

        val lpProvider = LuckPermsProvider.get()
        val user = lpProvider.userManager.getUser(uuid) ?: return
        val metadata = user.cachedData.metaData

        val prefix = metadata.prefix ?: ""
        val suffix = metadata.suffix ?: ""

        val weight = metadata.getMetaValue("weight")?.toIntOrNull() ?: 0
        val sortOrder = 9999 - weight

        val safeName = player.name.replace(Regex("[^a-zA-Z0-9]"), "")
        val teamName = String.format("%04d_%s", sortOrder, safeName.take(10))

        val icon = PlatformResolver.getIcon(uuid)

        var team = scoreboard.getTeam(teamName)
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName)
        }

        team.prefix(serializer.deserialize(prefix))
        team.suffix(serializer.deserialize(" $icon$suffix"))

        val color = getLastColor(prefix)
        if (color != null) {
            team.color(color)
        }

        if (!team.hasEntry(player.name)) {
            team.addEntry(player.name)
        }
    }

    private fun getLastColor(input: String): NamedTextColor? {
        var lastColor: NamedTextColor? = null
        val length = input.length

        for (i in 0 until length - 1) {
            val c = input[i]
            if ((c == '&' || c == '§') && "0123456789AaBbCcDdEeFf".indexOf(input[i + 1]) > -1) {
                val code = input[i + 1]
                lastColor = when (code.lowercaseChar()) {
                    '0' -> NamedTextColor.BLACK
                    '1' -> NamedTextColor.DARK_BLUE
                    '2' -> NamedTextColor.DARK_GREEN
                    '3' -> NamedTextColor.DARK_AQUA
                    '4' -> NamedTextColor.DARK_RED
                    '5' -> NamedTextColor.DARK_PURPLE
                    '6' -> NamedTextColor.GOLD
                    '7' -> NamedTextColor.GRAY
                    '8' -> NamedTextColor.DARK_GRAY
                    '9' -> NamedTextColor.BLUE
                    'a' -> NamedTextColor.GREEN
                    'b' -> NamedTextColor.AQUA
                    'c' -> NamedTextColor.RED
                    'd' -> NamedTextColor.LIGHT_PURPLE
                    'e' -> NamedTextColor.YELLOW
                    'f' -> NamedTextColor.WHITE
                    else -> null
                }
            }
        }
        return lastColor
    }
}