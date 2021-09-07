package com.helicoptera.cga.parser

import com.helicoptera.cga.exception.ParseException
import com.helicoptera.cga.model.NormalVector

internal class NormalVectorParser {

    internal fun parseNormalVector(source: String) : NormalVector {
        val values = source.split(VALUES_DELIMITER)
        try {
            val i = values[0].toString().toDouble()
            val j = values[1].toString().toDouble()
            val k = values[2].toString().toDouble()

            return NormalVector(i, j, k)
        } catch (e: Exception) {
            throw ParseException("Invalid normal vector value")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = " "
    }
}