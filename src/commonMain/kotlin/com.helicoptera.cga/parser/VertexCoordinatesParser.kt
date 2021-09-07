package com.helicoptera.cga.parser

import com.helicoptera.cga.exception.ParseException
import com.helicoptera.cga.model.NormalVector
import com.helicoptera.cga.model.VertexCoordinates

internal class VertexCoordinatesParser {

    internal fun parseVertexCoordinates(source: String) : VertexCoordinates {
        val values = source.split(VALUES_DELIMITER)
        try {
            val x = values[0].toString().toDouble()
            val y = values[1].toString().toDouble()
            val z = values[2].toString().toDouble()
            val w = if (values.size > 3) values[3].toString().toDouble() else W_DEFAULT_VALUE

            return VertexCoordinates(x, y, z, w)
        } catch (e: Exception) {
            throw ParseException("Invalid vertex coordinates value")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
        private const val W_DEFAULT_VALUE = 1.0
    }
}