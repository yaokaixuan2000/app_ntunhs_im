package com.example.jsonread

data class Singers(
    val singers: List<Singer>
)

data class Singer(
    val agency: String,
    val name: String,
    val year_of_debut: Int
)