package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.model.JSONListItem
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.util.*

class JSONParser {
    companion object {
        fun parse(file: File): Array<JSONListItem> {
            val prop = getProperties()
            var items = emptyArray<JSONListItem>()
            for (line in file.readLines()) {
                val tokener = JSONTokener(line)
                val jsonObject = JSONObject(tokener)
                val item = JSONListItem(jsonObject.toString().toLowerCase())
                item.timestampKey = prop.getProperty("timestamp", "timestamp")
                item.levelKey = prop.getProperty("level", "level")
                item.messageKey = prop.getProperty("message", "message")
                for (key in jsonObject.keys()) {
                    item.items.put(key, jsonObject.get(key).toString())
                }
                items += item
            }
            return items
        }

        fun getProperties(): Properties {
            val stream = JSONParser::class.java.classLoader.getResourceAsStream("JSONlogviewer.properties")
            val prop = Properties()
            prop.load(stream)
            return prop
        }
    }
}