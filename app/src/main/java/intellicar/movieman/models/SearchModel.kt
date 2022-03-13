package intellicar.movieman.models

import com.google.gson.annotations.SerializedName

data class SearchModel(

    @SerializedName("Search")
    var Search : ArrayList<SearchDataModel>,

    @SerializedName("totalResults")
    var totalResults : Int,

    @SerializedName("Response")
    var Response : String
)
