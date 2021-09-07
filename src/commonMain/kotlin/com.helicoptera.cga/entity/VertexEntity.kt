package com.helicoptera.cga.entity

internal data class VertexEntity(
    val vertexCoordinatesIndex: Int,
    val textureVertexIndex: Int? = null,
    val normalVectorIndex: Int? = null
)