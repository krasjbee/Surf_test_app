package com.example.surf_test_app.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favourites")
data class FilmResult(
    @Ignore
    @SerializedName("adult")
    val adult: Boolean,
    @Ignore
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @Ignore
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @Ignore
    @SerializedName("original_language")
    val originalLanguage: String,
    @Ignore
    @SerializedName("original_title")
    val originalTitle: String,
    @Ignore
    @SerializedName("overview")
    val overview: String,
    @Ignore
    @SerializedName("popularity")
    val popularity: Double,
    @Ignore
    @SerializedName("poster_path")
    val posterPath: String,
    @Ignore
    @SerializedName("release_date")
    val releaseDate: String,
    @Ignore
    @SerializedName("title")
    val title: String,
    @Ignore
    @SerializedName("video")
    val video: Boolean,
    @Ignore
    @SerializedName("vote_average")
    val voteAverage: Double,
    @Ignore
    @SerializedName("vote_count")
    val voteCount: Int,
    @ColumnInfo(name = "is_favourite")
    var isFavorite: Boolean = false
) {
    constructor() : this(
        false,
        "",
        emptyList(),
        -1,
        "",
        "",
        "",
        -1.0,
        "",
        "",
        "",
        false,
        -1.0,
        -1,
    )
}