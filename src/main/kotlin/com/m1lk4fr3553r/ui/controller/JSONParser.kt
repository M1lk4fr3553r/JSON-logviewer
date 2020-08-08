package com.m1lk4fr3553r.ui.controller

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
                item.timestampKey = prop.key.timestamp
                item.levelKey = prop.key.level
                item.messageKey = prop.key.message
                items += item
            }
            return items
        }
    }
}
