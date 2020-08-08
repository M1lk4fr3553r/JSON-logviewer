package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Colors : DisplayableSettings {
    var debug: String = "#0037dd"
    var error: String = "#aa0000"
    var info: String = "#000000"
    var search: String = "#9c9e67"
    var warn: String = "#ada401"
    var colorEntireRow: Boolean = false

    override fun getKeys(): Array<String> {
        return arrayOf(
                "debug",
                "error",
                "info",
                "search",
                "warn",
                "colorEntireRow"
        )
    }

    override fun getValues(): Array<String> {
        return arrayOf(
                debug,
                error,
                info,
                search,
                warn,
                colorEntireRow.toString()
        )
    }

    override fun setValues(values: Array<String>) {
        debug = values[0]
        error = values[1]
        info = values[2]
        search = values[3]
        warn = values[4]
        colorEntireRow = values[5].toBoolean()
    }
}