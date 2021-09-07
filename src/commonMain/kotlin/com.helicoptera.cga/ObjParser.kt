package com.helicoptera.cga

import com.helicoptera.cga.entity.PolygonEntity
import com.helicoptera.cga.entity.VertexEntity
import com.helicoptera.cga.exception.ParseException
import com.helicoptera.cga.mapper.PolygonMapper
import com.helicoptera.cga.model.NormalVector
import com.helicoptera.cga.model.Obj
import com.helicoptera.cga.model.TextureVertex
import com.helicoptera.cga.model.VertexCoordinates
import com.helicoptera.cga.parser.NormalVectorParser
import com.helicoptera.cga.parser.PolygonParser
import com.helicoptera.cga.parser.TextureVertexParser
import com.helicoptera.cga.parser.VertexCoordinatesParser

class ObjParser {

    private val normalVectorParser = NormalVectorParser()
    private val textureVertexParser = TextureVertexParser()
    private val vertexCoordinatesParser = VertexCoordinatesParser()
    private val polygonParser = PolygonParser()
    private val polygonMapper = PolygonMapper()

    fun parseObj(source: String): Obj {
        val normalVectors = mutableListOf<NormalVector>()
        val polygonsEntities = mutableListOf<PolygonEntity>()
        val texturesVertexes = mutableListOf<TextureVertex>()
        val vertexes = mutableListOf<VertexEntity>()
        val vertexesCoordinates = mutableListOf<VertexCoordinates>()

        try {
            val items = source.split(VALUES_DELIMITER)
            for (item in items) {
                val prefixDelimiterIndex = item.indexOf(PREFIX_DELIMITER)
                val prefix = item.substring(0, prefixDelimiterIndex)
                val value = item.substring(prefixDelimiterIndex + 2)
                when (prefix) {
                    VERTEX_COORDINATES_PREFIX -> {
                        val vertexCoordinates = vertexCoordinatesParser.parseVertexCoordinates(value)
                        vertexesCoordinates.add(vertexCoordinates)
                    }
                    TEXTURE_VERTEX_PREFIX -> {
                        val textureVertex = textureVertexParser.parseTextureVertex(value)
                        texturesVertexes.add(textureVertex)
                    }
                    NORMAL_VECTOR_PREFIX -> {
                        val normalVector = normalVectorParser.parseNormalVector(value)
                        normalVectors.add(normalVector)
                    }
                    POLYGON_PREFIX -> {
                        val polygonEntity = polygonParser.parsePolygon(value)
                        polygonsEntities.add(polygonEntity)
                    }
                    else -> {
                        throw ParseException("Invalid object prefix")
                    }
                }
            }

            val polygons = polygonsEntities.map {
                polygonMapper.convertPolygonEntityToModel(it, vertexesCoordinates, texturesVertexes, normalVectors)
            }

            return Obj(vertexesCoordinates, texturesVertexes, normalVectors, polygons)
        } catch (parseException: ParseException) {
            throw parseException
        } catch (e: Exception) {
            throw ParseException("Invalid obj structure")
        }
    }

    companion object {
        private const val VERTEX_COORDINATES_PREFIX = "v"
        private const val TEXTURE_VERTEX_PREFIX = "tx"
        private const val NORMAL_VECTOR_PREFIX = "vn"
        private const val POLYGON_PREFIX = "f"
        private const val VALUES_DELIMITER = "\n"
        private const val PREFIX_DELIMITER = " "
    }
}