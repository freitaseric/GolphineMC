package com.freitaseric.golphinemc.golphinemc.lib

import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object ColorTranslator {
    fun translate(input: String): MutableText {
        val parts = input.split("(?=&[0-9a-fk-or])".toRegex())
        val root = Text.empty()

        for (part in parts) {
            var textPart = part
            var format: Formatting? = null

            if (textPart.length >= 2 && textPart.startsWith("&")) {
                val code = textPart[1]
                format = Formatting.byCode(code)
                textPart = textPart.substring(2)
            }

            val component = Text.literal(textPart)
            if (format != null) {
                component.formatted(format)
            }
            root.append(component)
        }
        return root
    }
}