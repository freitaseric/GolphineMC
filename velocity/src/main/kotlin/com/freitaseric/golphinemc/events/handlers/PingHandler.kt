package com.freitaseric.golphinemc.events.handlers

import com.freitaseric.golphinemc.common.GolphineMC
import com.freitaseric.golphinemc.common.core.GolphineLogger
import com.freitaseric.golphinemc.events.IPluginEvent
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import com.velocitypowered.api.util.Favicon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import java.awt.image.BufferedImage
import java.net.URI
import javax.imageio.ImageIO

class PingHandler : IPluginEvent {
    private var job: Job? = null
    private val scope = CoroutineScope(Dispatchers.Default)
    private val motd: Component = MiniMessage.miniMessage().deserialize(
        """
            ${GolphineMC.GRADIENT_COLORED_NAME}
            <gray>Nade nas profundezas do nosso Survival!
        """.trimIndent()
    )

    @Volatile
    private var cachedFavicon: Favicon? = null

    init {
        loadFavicon()
    }

    @Subscribe
    fun onPing(event: ProxyPingEvent) {
        val ping = event.ping.asBuilder()

        ping.description(motd)
        cachedFavicon?.let {
            ping.favicon(it)
        }

        event.ping = ping.build()
    }

    private fun loadFavicon() {
        job = scope.launch(Dispatchers.IO) {
            try {
                val url = URI("https://cdn.freitaseric.com/golphinemc/pack.png").toURL()

                val image: BufferedImage? = ImageIO.read(url)

                if (image == null) {
                    GolphineLogger.warn("Não foi possível decodificar a imagem da CDN.")
                    return@launch
                }

                if (image.width != 64 || image.height != 64) {
                    GolphineLogger.warn("Ícone inválido! Tamanho recebido: ${image.width}x${image.height}. Exigido: 64x64.")
                    return@launch
                }

                val favicon = Favicon.create(image)
                cachedFavicon = favicon
                GolphineLogger.info("Ícone do servidor atualizado via CDN!")
            } catch (e: Exception) {
                GolphineLogger.error("Erro ao baixar ícone: ${e.message}")
            }
        }
    }
}