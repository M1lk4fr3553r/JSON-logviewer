package com.m1lk4fr3553r.ui.view

import com.m1lk4fr3553r.model.SettingsListItem

interface DisplayableSettings {
    fun getSettings(): Array<SettingsListItem>
    fun setValues(values: Array<SettingsListItem>)
}