package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Misc : DisplayableSettings {
    var lastFile: String = ""

    override fun getKeys(): Array<String> {
        return arrayOf(
                "lastFile"
        )
    }

    override fun getValues(): Array<String> {
        return arrayOf(
                lastFile
        )
    }

    override fun setValues(values: Array<String>) {
        lastFile = values[0]
    }
}