package intellicar.movieman.models

import com.google.gson.annotations.SerializedName

data class SearchDataModel(

    @SerializedName("Title")
    var Title : String,

    @SerializedName("Year")
    var Year : String,

    @SerializedName("imdbID")
    var imdbID : String,

    @SerializedName("Type")
    var Type : String,

    @SerializedName("Poster")
    var Poster : String

)
