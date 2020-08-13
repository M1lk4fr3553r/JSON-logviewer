package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Colors : DisplayableSettings {
    var debug: String = "#0037dd"
    var debugBackground: String = "#FFFFFF"
    var error: String = "#aa0000"
    var errorBackground: String = "#FFFFFF"
    var info: String = "#000000"
    var infoBackground: String = "#FFFFFF"
    var search: String = "#9c9e67"
    var warn: String = "#ada401"
    var warnBackground: String = "#FFFFFF"
    var colorEntireRow: Boolean = false

    override fun getKeys(): Array<String> {
        return arrayOf(
                "debug",
                "debugBackground",
                "error",
                "errorBackground",
                "info",
                "infoBackground",
                "search",
                "warn",
                "warnBackground",
                "colorEntireRow"
        )
    }

    override fun getValues(): Array<String> {
        return arrayOf(
                debug,
                debugBackground,
                error,
                errorBackground,
                info,
                infoBackground,
                search,
                warn,
                warnBackground,
                colorEntireRow.toString()
        )
    }

    override fun setValues(values: Array<String>) {
        debug = values[0]
        debugBackground = values[1]
        error = values[2]
        errorBackground = values[3]
        info = values[4]
        infoBackground = values[5]
        search = values[6]
        warn = values[7]
        warnBackground = values[8]
        colorEntireRow = values[9].toBoolean()
    }
}