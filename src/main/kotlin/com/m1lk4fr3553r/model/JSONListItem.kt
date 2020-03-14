package com.m1lk4fr3553r.model

import org.json.JSONObject


class JSONListItem(json: JSONObject) {
    val raw: String
    val json: JSONObject
    val items = mutableMapOf<String, String>()
    var timestampKey: String = "timestamp"
    var levelKey: String = "level"
    var messageKey: String = "message"

    init {
        this.json = json
        this.raw = json.toString().toLowerCase()

        for (key in json.keys()) {
            items.put(key, json.get(key).toString())
        }
    }

    override fun toString(): String {
        return "${items.get(timestampKey)}   ${items.get(levelKey)}    ${items.get(messageKey)}"
    }
}