package com.example.movieapp.model

data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val poster: String,
    val images: List<String>,
    val rating: String
)

fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            "id",
            "title",
            "year",
            "genre",
            "director",
            "actors",
            "plot",
            "https://www.justwatch.com/images/poster/242349782/s166/시즌-1",
            listOf(
                "https://image.lawtimes.co.kr/images/82883.jpg",
                "https://t1.daumcdn.net/cfile/tistory/242AC8405954B6431C"
            ),
            "1"
        )
    )
}
