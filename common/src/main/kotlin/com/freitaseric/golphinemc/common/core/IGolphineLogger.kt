package com.freitaseric.golphinemc.common.core

interface IGolphineLogger {
    fun info(message: String)
    fun warn(message: String)
    fun error(message: String)
    fun debug(message: String)
}