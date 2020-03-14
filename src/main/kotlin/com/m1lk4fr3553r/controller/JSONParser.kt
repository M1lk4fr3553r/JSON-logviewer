package com.m1lk4fr3553r.controller

import com.m1lk4fr3553r.model.JSONListItem
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class JSONParser {
    companion object {
        fun parse(file: File): Array<JSONListItem> {
            var items = emptyArray<JSONListItem>()
            for (line in file.readLines()) {
                val tokener = JSONTokener(line)
                val jsonObject = JSONObject(tokener)
                val item = JSONListItem()
                for(key in jsonObject.keys()) {
                    item.items.put(key, jsonObject.get(key).toString())
                }
                items += item
            }
            return items
        }
    }
}