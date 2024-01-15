package com.example.iot

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.williamchart.view.BarChartView
import com.db.williamchart.view.LineChartView
import com.example.iot.config.DataConfigWaterGrafik
import com.example.iot.databinding.FragmentFGrafikBinding
import com.example.iot.model.Kapasitas
import com.example.iot.model.ModelAverage
import com.example.iot.model.ModelGrafik1Water
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fGrafik.newInstance] factory method to
 * create an instance of this fragment.
 */
class fGrafik : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //private lateinit var  binding: FragmentFGrafikBinding
    private var _binding: FragmentFGrafikBinding? = null
    private val binding get() = _binding !!

    private val lineSet = mutableListOf<Pair<String, Float>>()
    private val barSet = mutableListOf<Pair<String, Float>>()

    private lateinit var barChartView: BarChartView
    private lateinit var lineChartView: LineChartView

    private val animationDuration = 1000L
    private val handler = Handler()


    private val scope = CoroutineScope(Dispatchers.Main + Job())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFGrafikBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        fetchDataFromApi()
        startAutoRefresh()

    }

    private fun setupCharts() {
        binding.apply {
            barChart.animation.duration = animationDuration
            barChart.animate(barSet)
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener = { index, _, _ ->
                tvChartData.text = lineSet.toList()[index].second.toString()
            }
        }
    }
    private fun fetchDataFromApi() {
        // Assuming you have a Retrofit service for fetching earthquake data
        // Make sure to replace ApiService and getDataGempa with your actual Retrofit service
        DataConfigWaterGrafik().getService()
            .getDataWaterG()
            .enqueue(object : Callback<ModelGrafik1Water>{
                override fun onResponse(
                    call: Call<ModelGrafik1Water>,
                    response: Response<ModelGrafik1Water>
                ) {
                    if (response.isSuccessful) {
                        val modelGrafik1Water = response.body()
                        if (modelGrafik1Water != null) {
                            parseData(modelGrafik1Water)
                            updateCharts()
                        } else {
                            // Handle the case when response.body() is null
                        }
                    } else {
                        // Handle the case when the response is not successful
                    }
                }

                override fun onFailure(call: Call<ModelGrafik1Water>, t: Throwable) {

                }

            })
    }

    private fun startAutoRefresh() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                scope.launch {
                    startAutoRefresh()
                }
            }
        }, updateInterval)
    }

    private fun parseData(modelGrafik1Water: ModelGrafik1Water) {
        modelGrafik1Water?.let {
            it.data?.let { data ->
                val kapasitas = data.kapasitas
                val debitair = data.debitAir

                val senin = kapasitas?.senin?: 0.0f
                val selasa = kapasitas?.selasa?: 0.0f
                val rabu = kapasitas?.rabu?: 0.0f
                val kamis = kapasitas?.kamis?: 0.0f
                val jumat = kapasitas?.jumat?: 0.0f
                val sabtu = kapasitas?.sabtu?: 0.0f
                val minggu = kapasitas?.minggu?: 0.0f

                val senin1 = debitair?.senin?: 0.0f
                val selasa1 = debitair?.selasa?: 0.0f
                val rabu1 = debitair?.rabu?: 0.0f
                val kamis1 = debitair?.kamis?: 0.0f
                val jumat1 = debitair?.jumat?: 0.0f
                val sabtu1 = debitair?.sabtu?: 0.0f
                val minggu1 = debitair?.minggu?: 0.0f

                lineSet.apply {
                    clear()
                    add("Senin" to senin)
                    add("Selasa" to selasa)
                    add("Rabu" to rabu)
                    add("Kamis" to kamis)
                    add("Jumat" to jumat)
                    add("Sabtu" to sabtu)
                    add("Minggu" to minggu)
                }

                barSet.apply {
                    clear()
                    add("Senin" to senin1)
                    add("Selasa" to selasa1)
                    add("Rabu" to rabu1)
                    add("Kamis" to kamis1)
                    add("Jumat" to jumat1)
                    add("Sabtu" to sabtu1)
                    add("Minggu" to minggu1)
                }
            }
        }
    }

    private fun updateCharts() {
        binding.apply {
            // Add new data
            lineChart.animate(lineSet)
            barChart.animate(barSet)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        private const val animationDuration = 1000L
        private const val updateInterval = 1000L

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fGrafik.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fGrafik().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}