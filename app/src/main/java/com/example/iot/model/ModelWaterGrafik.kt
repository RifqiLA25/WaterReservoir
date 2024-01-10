package com.example.iot.model

import com.google.gson.annotations.SerializedName

data class ModelWaterGrafik(

	@field:SerializedName("average_debit_air")
	val averageDebitAir: Any? = null
)
data class Watertwo(

	@field:SerializedName("ModelWaterGrafik")
	val ModelWaterGrafik:ModelWaterGrafik? = null
)