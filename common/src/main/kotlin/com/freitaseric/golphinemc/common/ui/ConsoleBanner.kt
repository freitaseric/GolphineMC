package com.freitaseric.golphinemc.common.ui

import com.freitaseric.golphinemc.common.GolphineMC

/**
 * Um utilitário para gerar banners ASCII para o console.
 */
object ConsoleBanner {

    /**
     * Gera uma string de banner ASCII personalizada.
     *
     * @param platform O nome da plataforma (ex: Velocity, Paper).
     * @param version A versão do plugin/mod atual.
     * @return Uma string formatada pronta para ser impressa no console.
     */
    fun generate(platform: String, version: String): String {
        val lines = listOf(
            "${GolphineMC.NAME} v$version",
            "",
            "Platform: $platform",
            "Enabled successfully!",
            "",
            "by ${GolphineMC.AUTHOR}"
        )

        val maxLength = lines.maxOfOrNull { it.length } ?: 0
        val border = "+-${"-".repeat(maxLength + 2)}-+"

        val builder = StringBuilder("\n")
        builder.appendLine(border)
        for (line in lines) {
            val padding = " ".repeat(maxLength - line.length)
            builder.appendLine("| $line$padding |")
        }
        builder.appendLine(border)

        return builder.toString()
    }
}
