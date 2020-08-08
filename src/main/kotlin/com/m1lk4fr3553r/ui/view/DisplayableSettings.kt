package com.m1lk4fr3553r.ui.view

interface DisplayableSettings {
    fun getKeys(): Array<String>
    fun getValues(): Array<String>
    fun setValues(values: Array<String>)
}