package com.helicoptera.cga.mapper

import com.helicoptera.cga.entity.PolygonEntity
import com.helicoptera.cga.model.*

internal class PolygonMapper {

    internal fun convertPolygonEntityToModel(
        polygonEntity: PolygonEntity,
        vertexesCoordinates: List<VertexCoordinates>,
        textureVertexes: List<TextureVertex>,
        normalVectors: List<NormalVector>
    ): Polygon {
        val vertexes = polygonEntity.vertexes.map {
            it.run {
                val vertexCoordinates = if (vertexCoordinatesIndex > 0) vertexesCoordinates[vertexCoordinatesIndex]
                else vertexesCoordinates[vertexesCoordinates.lastIndex + vertexCoordinatesIndex]
                val textureVertex =
                    if (textureVertexIndex != null) if (textureVertexIndex > 0) textureVertexes[textureVertexIndex]
                    else textureVertexes[textureVertexes.lastIndex + textureVertexIndex] else null
                val normalVector =
                    if (normalVectorIndex != null) if (normalVectorIndex > 0) normalVectors[normalVectorIndex]
                    else normalVectors[normalVectors.lastIndex + normalVectorIndex] else null

                return@run Vertex(vertexCoordinates, textureVertex, normalVector)
            }
        }

        return Polygon(vertexes)
    }
}