package com.example.iot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iot.config.DataConfigWaterR
import com.example.iot.databinding.FragmentFHomeBinding
import com.example.iot.model.Modelwaterreservoir
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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
 * Use the [fHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class fHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFHomeBinding? = null
    private val binding get() = _binding !!
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
    ): View? {

        _binding = FragmentFHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupKapasitas()
        startAutoRefresh()

        // Mendapatkan referensi ke CardView
        val cardToren1 = binding.cardtoren1

        // Menambahkan listener klik pada CardView
        cardToren1.setOnClickListener {
            // Pindah ke TorenActivity
            val intent = Intent(requireContext(), TorenActivity::class.java)
            startActivity(intent)
        }

        binding.msktoren1.setOnCheckedChangeListener { _, isChecked ->
            // Panggil fungsi untuk mengubah status berdasarkan isChecked
            updateStatus(isChecked)
        }
    }

    private fun updateStatus(isChecked: Boolean) {
        binding.apply {
            handlePumpStatus(if (isChecked) 1 else 0)
        }
    }
    private fun handlePumpStatus(statusPompa: Int) {
        _binding?.apply {
            msktoren1?.apply {
                when (statusPompa) {
                    1 -> {
                        // Status ON
                        text = "ON"
                        setBackgroundColor(resources.getColor(R.color.greenLight))
                        isChecked = true // Set ToggleButton ke posisi ON
                    }

                    0 -> {
                        // Status OFF
                        text = "OFF"
                        setBackgroundColor(resources.getColor(R.color.merah))
                        isChecked = false // Set ToggleButton ke posisi OFF
                    }

                    2 -> {
                        // Status ERROR
                        text = "ERROR"
                        setBackgroundColor(resources.getColor(R.color.abu))
                        // Tidak memperbarui ToggleButton karena status tidak jelas
                    }

                    else -> {
                        Log.e("RifqiLuthfiA", "Status pompa tidak dikenali: $statusPompa")
                    }
                }
            }
        }
    }

    private fun setupKapasitas() {
        binding.apply {
            // Start fetching and updating kapasitas
            scope.launch {
                while (isActive) {
                    updateKapasitas()
                    delay(updateInterval)
                }
            }
        }
    }

    private suspend fun updateKapasitas() {
        try {
            if (isActive) {
                val response = DataConfigWaterR().getService().getDataWater().execute()

                if (isActive && response.isSuccessful) {
                    val model = response.body()
                    Log.d("Kibem", "data json: $model")

                    binding.hToren1.text = model?.kapasitas
                    handlePumpStatus(model?.statusPompa ?: 0)
                }
            }
        } catch (e: Exception) {
            Log.e("Kibem", "Error: ${e.message}")
        }
    }

    private fun startAutoRefresh() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                scope.launch {
                    updateKapasitas()
                    startAutoRefresh()
                }
            }
        }, updateInterval)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        _binding = null
    }

    companion object {
        private const val updateInterval = 1000L
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}