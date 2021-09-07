package com.helicoptera.cga.parser

import com.helicoptera.cga.entity.VertexEntity
import com.helicoptera.cga.exception.ParseException
import com.helicoptera.cga.model.NormalVector
import com.helicoptera.cga.model.VertexCoordinates

internal class VertexParser {

    internal fun parseVertex(source: String) : VertexEntity {
        val values = source.split(VALUES_DELIMITER)
        try {
            val vertexCoordinatesIndex = values[0].toString().toInt()
            val textureVertexIndex =
                if (values.size > 2 && values[2].isNotEmpty()) values[2].toString().toInt() else null
            val normalVectorIndex = if (values.size > 3) values[3].toString().toInt() else null

            return VertexEntity(vertexCoordinatesIndex, textureVertexIndex, normalVectorIndex)
        } catch (e: Exception) {
            throw ParseException("Invalid vertex value")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = "/"
    }
}