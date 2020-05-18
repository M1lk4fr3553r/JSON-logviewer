package com.m1lk4fr3553r.model

import java.io.*
import java.util.*

class SortedProperties: Properties() {
    private val hexDigit = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    )

    @Throws(IOException::class)
    override fun store(writer: Writer?, comments: String?) {
        store0(if (writer is BufferedWriter) writer else BufferedWriter(writer),
                comments,
                false)
    }

    @Throws(IOException::class)
    override fun store(out: OutputStream?, comments: String?) {
        store0(BufferedWriter(OutputStreamWriter(out, "8859_1")),
                comments,
                true)
    }

    @Throws(IOException::class)
    private fun store0(bw: BufferedWriter, comments: String?, escUnicode: Boolean) {
        comments?.let {
            writeComments(bw, it)
            bw.write("#" + Date().toString())
            bw.newLine()
        }
        synchronized(this) {
            for ((key1, value) in entries.sortedBy { (a1, a2) -> (a1 as String).compareTo((a2 as String)) }) {
                var key = key1 as String?
                var `val` = value as String?
                key = saveConvert(key, true, escUnicode)
                /* No need to escape embedded and trailing spaces for value, hence
                 * pass false to flag.
                 */`val` = saveConvert(`val`, false, escUnicode)
                bw.write("$key=$`val`")
                bw.newLine()
            }
        }
        bw.flush()
    }

    @Throws(IOException::class)
    private fun writeComments(bw: BufferedWriter, comments: String) {
        bw.write("#")
        val len = comments.length
        var current = 0
        var last = 0
        val uu = CharArray(6)
        uu[0] = '\\'
        uu[1] = 'u'
        while (current < len) {
            val c = comments[current]
            if (c > '\u00ff' || c == '\n' || c == '\r') {
                if (last != current) bw.write(comments.substring(last, current))
                if (c > '\u00ff') {
                    uu[2] = toHex(c.toInt() shr 12 and 0xf)
                    uu[3] = toHex(c.toInt() shr 8 and 0xf)
                    uu[4] = toHex(c.toInt() shr 4 and 0xf)
                    uu[5] = toHex(c.toInt() and 0xf)
                    bw.write(String(uu))
                } else {
                    bw.newLine()
                    if (c == '\r' && current != len - 1 && comments[current + 1] == '\n') {
                        current++
                    }
                    if (current == len - 1 ||
                            comments[current + 1] != '#' &&
                            comments[current + 1] != '!') bw.write("#")
                }
                last = current + 1
            }
            current++
        }
        if (last != current) bw.write(comments.substring(last, current))
        bw.newLine()
    }

    private fun saveConvert(theString: String?,
                            escapeSpace: Boolean,
                            escapeUnicode: Boolean): String? {
        if (theString != null) {
            val len = theString.length
            var bufLen = len * 2
            if (bufLen < 0) {
                bufLen = Int.MAX_VALUE
            }
            val outBuffer = StringBuilder(bufLen)
            for (x in 0 until len) {
                val aChar = theString[x]
                // Handle common case first, selecting largest block that
                // avoids the specials below
                if (aChar.toInt() > 61 && aChar.toInt() < 127) {
                    if (aChar == '\\') {
                        outBuffer.append('\\')
                        outBuffer.append('\\')
                        continue
                    }
                    outBuffer.append(aChar)
                    continue
                }
                when (aChar) {
                    ' ' -> {
                        if (x == 0 || escapeSpace) outBuffer.append('\\')
                        outBuffer.append(' ')
                    }
                    '\t' -> {
                        outBuffer.append('\\')
                        outBuffer.append('t')
                    }
                    '\n' -> {
                        outBuffer.append('\\')
                        outBuffer.append('n')
                    }
                    '\r' -> {
                        outBuffer.append('\\')
                        outBuffer.append('r')
                    }
                    '\u000C' -> {
                        outBuffer.append('\\')
                        outBuffer.append('f')
                    }
                    '=', ':', '#', '!' -> {
                        outBuffer.append('\\')
                        outBuffer.append(aChar)
                    }
                    else -> if ((aChar.toInt() < 0x0020 || aChar.toInt() > 0x007e) and escapeUnicode) {
                        outBuffer.append('\\')
                        outBuffer.append('u')
                        outBuffer.append(toHex(aChar.toInt() shr 12 and 0xF))
                        outBuffer.append(toHex(aChar.toInt() shr 8 and 0xF))
                        outBuffer.append(toHex(aChar.toInt() shr 4 and 0xF))
                        outBuffer.append(toHex(aChar.toInt() and 0xF))
                    } else {
                        outBuffer.append(aChar)
                    }
                }
            }
            return outBuffer.toString()
        } else {
            return null;
        }
    }

    private fun toHex(nibble: Int): Char {
        return hexDigit[nibble and 0xF]
    }
}
