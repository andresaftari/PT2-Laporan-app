package org.ray.core.data.remote.api.response

import com.google.gson.annotations.SerializedName

// Untuk API Response data classes
data class ResponseLogin(
    @field:SerializedName("msg")
    var msg: String = "",

    @field:SerializedName("name")
    var name: String = "",

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

data class ResponseHistory(
    var bukti_kerusakan: String = "",
    var deskripsi_kerusakan: String = "",
    var id: String = "",
    var lokasi: String = "",
    var nama_kerusakan: String = "",
    var status: String = "",
    var tanggal_laporan: String = "",
    var username: String = ""
)