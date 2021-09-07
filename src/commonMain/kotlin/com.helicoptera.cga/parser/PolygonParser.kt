package com.helicoptera.cga.parser

import com.helicoptera.cga.entity.PolygonEntity
import com.helicoptera.cga.entity.VertexEntity
import com.helicoptera.cga.exception.ParseException

internal class PolygonParser {

    private val vertexParser = VertexParser()

    internal fun parsePolygon(source: String): PolygonEntity {
        val vertexes = mutableListOf<VertexEntity>()

        val values = source.split(VALUES_DELIMITER)
        for (value in values) {
            val vertex = vertexParser.parseVertex(value)
            vertexes.add(vertex)
        }

        return PolygonEntity(vertexes)
    }

    companion object {
        private const val VALUES_DELIMITER = "/"
    }
}