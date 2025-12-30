package com.freitaseric.golphinemc.events.handlers

import com.freitaseric.golphinemc.common.core.PlatformResolver
import com.freitaseric.golphinemc.events.IPluginEvent
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChatEvent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.query.QueryOptions

class ChatHandler : IPluginEvent {
    private val lp = LuckPermsProvider.get()
    private val serializer = LegacyComponentSerializer.legacyAmpersand()

    @Subscribe
    @Suppress("DEPRECATION")
    fun onPlayerChat(event: PlayerChatEvent) {
        if (event.message.startsWith("/")) return

        val player = event.player
        val currentServer = player.currentServer.orElse(null) ?: return

        event.result = PlayerChatEvent.ChatResult.denied()

        val user = lp.userManager.getUser(player.uniqueId)
        val metadata = user?.cachedData?.getMetaData(QueryOptions.defaultContextualOptions()) ?: return

        val prefix = metadata.prefix ?: ""
        val suffix = metadata.suffix ?: ""

        val nameColor = metadata.getMetaValue("name-color") ?: "&7"
        val platformIcon = PlatformResolver.getIcon(player.uniqueId)

        val formatString = "$prefix$nameColor${player.username} $platformIcon$suffix&r: ${event.message}"

        val component = serializer.deserialize(formatString)

        currentServer.server.playersConnected.forEach { it.sendMessage(component) }
    }
}