package intellicar.movieman.models

import com.google.gson.annotations.SerializedName

data class RatingsModel(

    @SerializedName("Source") var Source : String,
    @SerializedName("Value") var Value : String

)
