package org.ray.core.data.remote.api.response

import com.google.gson.annotations.SerializedName

// Untuk API Response data classes
data class ResponseLogin(
    @field:SerializedName("msg")
    var msg: String = "",

    @field:SerializedName("user")
    var user: String = "",

    @field:SerializedName("status")
    var status: String = ""
)

data class ResponseRegister(
    @field:SerializedName("msg")
    var msg: String = "",

    @field:SerializedName("status")
    var status: String = ""
)

data class ResponseReport(
    @field:SerializedName("msg")
    var msg: String = "",

    @field:SerializedName("status")
    var status: String = ""
)