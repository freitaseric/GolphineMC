package com.freitaseric.golphinemc.common.core

import org.geysermc.floodgate.api.FloodgateApi
import org.geysermc.floodgate.util.DeviceOs
import java.util.*

object PlatformResolver {
    private val floodgateApi = FloodgateApi.getInstance()

    fun getIcon(uuid: UUID): String {
        if (!floodgateApi.isFloodgatePlayer(uuid)) return PlatformIcons.JAVA

        val bedrockPlayer = floodgateApi.getPlayer(uuid) ?: return PlatformIcons.UNKNOWN

        return when (bedrockPlayer.deviceOs) {
            DeviceOs.GOOGLE -> PlatformIcons.ANDROID
            DeviceOs.IOS, DeviceOs.OSX -> PlatformIcons.IOS

            DeviceOs.WIN32 -> PlatformIcons.WINDOWS

            DeviceOs.PS4 -> PlatformIcons.PLAYSTATION
            DeviceOs.XBOX -> PlatformIcons.XBOX
            DeviceOs.NX -> PlatformIcons.SWITCH

            DeviceOs.AMAZON, DeviceOs.GEARVR, DeviceOs.HOLOLENS, DeviceOs.WINDOWS_PHONE -> PlatformIcons.ANDROID

            else -> PlatformIcons.UNKNOWN
        }
    }
}