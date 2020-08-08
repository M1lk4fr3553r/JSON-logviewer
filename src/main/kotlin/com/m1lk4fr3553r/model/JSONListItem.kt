package com.m1lk4fr3553r.model

import org.json.JSONObject


class JSONListItem(val json: JSONObject) {
    val raw = json.toString().toLowerCase()
    val items = mutableMapOf<String, String>()
    var timestampKey = "timestamp"
    var levelKey = "level"
    var messageKey = "message"

    val timestamp: String?
    get() {
        return items[timestampKey]
    }

    val level: String?
    get() {
        return items[levelKey]
    }

    val message: String?
    get() {
        return items[messageKey]
    }

    init {
        for (key in json.keys()) {
            items.put(key, json.get(key).toString())
        }
    }

    override fun toString(): String {
        return "${items[timestampKey]}   ${items[levelKey]}    ${items[messageKey]}"
    }
}