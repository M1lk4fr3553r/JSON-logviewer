package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.model.SettingsListItem
import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Keys : DisplayableSettings{
    var level: String = "level"
    var message: String = "message"
    var timestamp: String = "@timestamp"

    override fun getSettings(): Array<SettingsListItem> {
        return arrayOf(
                SettingsListItem("level", level),
                SettingsListItem("message", message),
                SettingsListItem("timestamp", timestamp)
        )
    }

    override fun setValues(values: Array<SettingsListItem>) {
        level = values[0].value
        message = values[1].value
        timestamp = values[2].value
    }
}