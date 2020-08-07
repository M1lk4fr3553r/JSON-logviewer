package com.m1lk4fr3553r.model.properties

import kotlinx.serialization.Serializable

@Serializable
class Colors {
    var debug: String = "#0037dd"
    var error: String = "#aa0000"
    var info: String = "#000000"
    var search: String = "#9c9e67"
    var warn: String = "#ada401"
}