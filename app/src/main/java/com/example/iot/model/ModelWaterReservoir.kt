package com.example.iot.model

import com.google.gson.annotations.SerializedName


data class Modelwaterreservoir(

    @field:SerializedName("ione/waterlevel")
    val waterLevel: String? = null,

    @field:SerializedName("ione/debit")
    val debit: String? = null,

    @field:SerializedName("ione/penggunaanLiter")
    val penggunaanLiter: String? = null,

    @field:SerializedName("ione/jarak")
    val jarak: String? = null,

    @field:SerializedName("ione/kapasitas")
    val kapasitas: String? = null,

    @field:SerializedName("ione/suhu")
    val suhu: String? = null,

    @field:SerializedName("ione/statuspompa")
    val statusPompa: Int? = null
)
data class Waterone(

    @field:SerializedName("Modelwaterreservoir")
    val Modelwaterreservoir:Modelwaterreservoir? = null
)