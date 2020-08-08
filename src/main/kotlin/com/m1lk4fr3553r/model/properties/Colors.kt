package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.model.SettingsListItem
import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Colors : DisplayableSettings {
    var debug: String = "#0037dd"
    var error: String = "#aa0000"
    var info: String = "#000000"
    var search: String = "#9c9e67"
    var warn: String = "#ada401"

    override fun getSettings(): Array<SettingsListItem> {
        return arrayOf(
                SettingsListItem("debug", debug),
                SettingsListItem("error", error),
                SettingsListItem("info", info),
                SettingsListItem("search", search),
                SettingsListItem("warn", warn)
        )
    }

    override fun setValues(values: Array<SettingsListItem>) {
        debug = values[0].value
        error = values[1].value
        info = values[2].value
        search = values[3].value
        warn = values[4].value
    }
}