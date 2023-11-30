package com.example.iot

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iot.databinding.FragmentFGrafikBinding

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
        // Inflate the layout for this fragment
        binding.apply {

            barChart.animation.duration = animationDuration
            barChart.animate(barSet)

            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration= animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener={index, _, _ ->
                tvChartData.text= lineSet.toList()[index].second.toString()

            }

        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    companion object {
        private val lineSet = listOf(
            "label1" to 4.5F,
            "label2" to 6F,
            "label3" to 10F,
            "label4" to 3F,
            "label5" to 2.5F,
            "label6" to 5.5F,
        )
        private val barSet = listOf(
            "JAN" to 4F,
            "FEB" to 7F,
            "MAR" to 2F,
            "MAY" to 2.3F,
            "APR" to 5F,
            "JUN" to 4F
        )
        private const val animationDuration = 1000L
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