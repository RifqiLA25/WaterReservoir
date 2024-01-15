package com.example.iot.model

import com.google.gson.annotations.SerializedName

data class ModelGrafik1Water(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data1? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class SuhuAir(

	@field:SerializedName("Senin")
	val senin: Float? = null,

	@field:SerializedName("Selasa")
	val selasa: Float? = null,

	@field:SerializedName("Minggu")
	val minggu: Float? = null,

	@field:SerializedName("Rabu")
	val rabu: Float? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Float? = null,

	@field:SerializedName("Jumat")
	val jumat: Float? = null,

	@field:SerializedName("Kamis")
	val kamis: Float? = null
)

data class KedalamanAir(

	@field:SerializedName("Senin")
	val senin: Any? = null,

	@field:SerializedName("Selasa")
	val selasa: Any? = null,

	@field:SerializedName("Minggu")
	val minggu: Any? = null,

	@field:SerializedName("Rabu")
	val rabu: Any? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Any? = null,

	@field:SerializedName("Jumat")
	val jumat: Any? = null,

	@field:SerializedName("Kamis")
	val kamis: Any? = null
)

data class Data1(

	@field:SerializedName("kedalaman_air")
	val kedalamanAir: KedalamanAir? = null,

	@field:SerializedName("debit_air")
	val debitAir: DebitAir? = null,

	@field:SerializedName("kapasitas")
	val kapasitas: Kapasitas? = null,

	@field:SerializedName("stat_pompa")
	val statPompa: StatPompa? = null,

	@field:SerializedName("penggunaanLiter")
	val penggunaanLiter: PenggunaanLiter? = null,

	@field:SerializedName("suhu_air")
	val suhuAir: SuhuAir? = null
)

data class Kapasitas(

	@field:SerializedName("Senin")
	val senin: Float? = null,

	@field:SerializedName("Selasa")
	val selasa: Float? = null,

	@field:SerializedName("Minggu")
	val minggu: Float? = null,

	@field:SerializedName("Rabu")
	val rabu: Float? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Float? = null,

	@field:SerializedName("Jumat")
	val jumat: Float? = null,

	@field:SerializedName("Kamis")
	val kamis: Float? = null
)
data class StatPompa(

	@field:SerializedName("Senin")
	val senin: String? = null,

	@field:SerializedName("Selasa")
	val selasa: Any? = null,

	@field:SerializedName("Minggu")
	val minggu: Any? = null,

	@field:SerializedName("Rabu")
	val rabu: Any? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Any? = null,

	@field:SerializedName("Jumat")
	val jumat: Any? = null,

	@field:SerializedName("Kamis")
	val kamis: String? = null
)

data class PenggunaanLiter(

	@field:SerializedName("Senin")
	val senin: Any? = null,

	@field:SerializedName("Selasa")
	val selasa: Any? = null,

	@field:SerializedName("Minggu")
	val minggu: Any? = null,

	@field:SerializedName("Rabu")
	val rabu: Any? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Any? = null,

	@field:SerializedName("Jumat")
	val jumat: Any? = null,

	@field:SerializedName("Kamis")
	val kamis: Any? = null
)

data class DebitAir(

	@field:SerializedName("Senin")
	val senin: Float? = null,

	@field:SerializedName("Selasa")
	val selasa: Float? = null,

	@field:SerializedName("Minggu")
	val minggu: Float? = null,

	@field:SerializedName("Rabu")
	val rabu: Float? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Float? = null,

	@field:SerializedName("Jumat")
	val jumat: Float? = null,

	@field:SerializedName("Kamis")
	val kamis: Float? = null
)
