package com.helicoptera.cga.model

data class Vertex(
    val vertexCoordinates: VertexCoordinates,
    val textureVertex: TextureVertex? = null,
    val normalVector: NormalVector? = null
)