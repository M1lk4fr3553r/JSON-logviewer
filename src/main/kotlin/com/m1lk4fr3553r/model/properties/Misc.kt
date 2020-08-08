package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.model.SettingsListItem
import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Misc : DisplayableSettings {
    var lastFile: String = ""

    override fun getSettings(): Array<SettingsListItem> {
        return arrayOf(
                SettingsListItem("lastFile", lastFile)
        )
    }

    override fun setValues(values: Array<SettingsListItem>) {
        lastFile = values[0].value
    }
}