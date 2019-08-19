package com.example.footballclubapi

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var teamId : String? = null,

    @SerializedName("strTeam")
    var teamName : String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge : String? = null
)