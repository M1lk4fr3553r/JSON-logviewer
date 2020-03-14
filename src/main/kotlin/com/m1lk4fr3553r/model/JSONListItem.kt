package com.m1lk4fr3553r.model


class JSONListItem {
    val items = mutableMapOf<String, String>()
    var timestampKey: String = "timestamp"
    var levelKey: String = "level"
    var messageKey: String = "message"

    override fun toString(): String {
        return "${items.get(timestampKey)}   ${items.get(levelKey)}    ${items.get(messageKey)}"
    }
}