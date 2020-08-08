package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Misc : DisplayableSettings {
    var lastFile: String = ""
    var disableUpdate: Boolean = false

    override fun getKeys(): Array<String> {
        return arrayOf(
                "lastFile",
                "disableUpdate"
        )
    }

    override fun getValues(): Array<String> {
        return arrayOf(
                lastFile,
                disableUpdate.toString()
        )
    }

    override fun setValues(values: Array<String>) {
        lastFile = values[0]
        disableUpdate = values[1].toBoolean()
    }
}