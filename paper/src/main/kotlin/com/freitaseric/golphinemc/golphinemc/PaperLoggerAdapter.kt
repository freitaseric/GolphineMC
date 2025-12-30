package com.freitaseric.golphinemc.golphinemc

import com.freitaseric.golphinemc.common.core.IGolphineLogger
import java.util.logging.Logger

class PaperLoggerAdapter(private val jul: Logger) : IGolphineLogger {
    override fun info(message: String) = jul.info(message)
    override fun warn(message: String) = jul.warning(message)
    override fun error(message: String) = jul.severe(message)
    override fun debug(message: String) = jul.fine(message)
}