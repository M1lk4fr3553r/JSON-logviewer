package com.m1lk4fr3553r.model


class JSONListItem(raw: String) {
    val raw: String
    val items = mutableMapOf<String, String>()
    var timestampKey: String = "timestamp"
    var levelKey: String = "level"
    var messageKey: String = "message"

    init {
        this.raw = raw
    }

    override fun toString(): String {
        return "${items.get(timestampKey)}   ${items.get(levelKey)}    ${items.get(messageKey)}"
    }
}