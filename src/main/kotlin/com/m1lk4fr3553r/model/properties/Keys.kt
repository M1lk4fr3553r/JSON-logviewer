package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.ui.view.DisplayableSettings
import kotlinx.serialization.Serializable

@Serializable
class Keys : DisplayableSettings {
    var level: String = "level"
    var message: String = "message"
    var timestamp: String = "@timestamp"

    override fun getKeys(): Array<String> {
        return arrayOf(
                "level",
                "message",
                "timestamp"
        )
    }

    override fun getValues(): Array<String> {
        return arrayOf(
                level,
                message,
                timestamp
        )
    }

    override fun setValues(values: Array<String>) {
        level = values[0]
        message = values[1]
        timestamp = values[2]
    }
}