package com.helicoptera.cga.parser

import com.helicoptera.cga.exception.ParseException
import com.helicoptera.cga.model.NormalVector
import com.helicoptera.cga.model.TextureVertex

internal class TextureVertexParser {

    internal fun parseTextureVertex(source: String) : TextureVertex {
        val values = source.split(VALUES_DELIMITER)
        try {
            val u = values[0].toString().toDouble()
            val v = if (values.size > 1) values[1].toString().toDouble() else V_DEFAULT_VALUE
            val w = if (values.size > 2) values[2].toString().toDouble() else W_DEFAULT_VALUE

            return TextureVertex(u, v, w)
        } catch (e: Exception) {
            throw ParseException("Invalid texture vertex value")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
        private const val V_DEFAULT_VALUE = 0.0
        private const val W_DEFAULT_VALUE = 0.0
    }
}