package com.freitaseric.golphinemc

import com.freitaseric.golphinemc.common.core.IGolphineLogger
import org.slf4j.Logger

class VelocityLoggerAdapter(private val slf4j: Logger) : IGolphineLogger {
    override fun info(message: String) = slf4j.info(message)
    override fun warn(message: String) = slf4j.warn(message)
    override fun error(message: String) = slf4j.error(message)
    override fun debug(message: String) = slf4j.debug(message)
}