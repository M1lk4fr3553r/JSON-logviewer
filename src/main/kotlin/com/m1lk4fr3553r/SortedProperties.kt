package com.m1lk4fr3553r

import java.util.*

class SortedProperties: Properties() {
    override fun keys(): Enumeration<Any>? {
        val keysEnum: Enumeration<*> = super.keys()
        val keyList = Vector<String>()
        while (keysEnum.hasMoreElements()) {
            keyList.add(keysEnum.nextElement() as String)
        }
        Collections.sort(keyList)
        return keyList.elements() as Enumeration<Any>?
    }
}
