package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.util.Util
import com.m1lk4fr3553r.model.JSONListItem
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class JSONParser {
    companion object {
        fun parse(file: File): Array<JSONListItem> {
            val prop = Util.getProperties()
            var items = emptyArray<JSONListItem>()
            for (line in file.readLines()) {
                val tokener = JSONTokener(line)
                val jsonObject = JSONObject(tokener)
                val item = JSONListItem(jsonObject)
                item.timestampKey = prop.getProperty("key.timestamp", "timestamp")
                item.levelKey = prop.getProperty("key.level", "level")
                item.messageKey = prop.getProperty("key.message", "message")
                items += item
            }
            return items
        }
    }
}
