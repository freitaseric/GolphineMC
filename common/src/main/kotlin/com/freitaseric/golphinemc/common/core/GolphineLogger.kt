package com.freitaseric.golphinemc.common.core

object GolphineLogger {
    private lateinit var logger: IGolphineLogger
    private var debugMode = false

    fun init(impl: IGolphineLogger, debug: Boolean = false) {
        logger = impl
        debugMode = debug
    }

    fun info(msg: String) = logger.info(msg)
    fun warn(msg: String) = logger.warn(msg)
    fun error(msg: String) = logger.error(msg)

    fun debug(msg: String) {
        if (debugMode) logger.debug(msg)
    }
}