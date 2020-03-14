package com.m1lk4fr3553r.model


class JSONListItem {
    val items = mutableMapOf<String, String>()

    init {
        items.put("level", "INFO")
        items.put("message", "message.......")
        items.put("timestamp", "time")
    }

    override fun toString(): String {
        return "${items.get("timestamp")}   ${items.get("level")}    ${items.get("message")}"
    }
}