package com.m1lk4fr3553r.model.properties

import com.m1lk4fr3553r.util.Util
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
class Properties {
    val color = Colors()
    val key = Keys()
    val misc = Misc()

    companion object {
        private val jsonConfig = JsonConfiguration(encodeDefaults = true, unquotedPrint = false, prettyPrint = true, indent = "  ")

        fun load(): Properties {
            val json = Json(jsonConfig)
            return json.parse(serializer(), Util.getPropertiesFile().readText())
        }
    }

    fun store() {
        val json = Json(jsonConfig)
        Util.getPropertiesFile().writeText(json.stringify(serializer(), this))
    }
}