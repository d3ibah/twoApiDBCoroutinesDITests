package com.twcc.data.api

import com.google.gson.annotations.SerializedName

data class DailyUserResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("explicit") var explicit: Boolean? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("has_more") var hasMore: Boolean? = null,
    @SerializedName("list") var list: ArrayList<DailyUser> = arrayListOf()
)

data class DailyUser(
    @SerializedName("id") var id: String? = null,
    @SerializedName("screenname") var screenname: String? = null
)
