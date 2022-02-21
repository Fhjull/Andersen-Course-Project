package ru.dillab.sportdiary.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.sportdiary.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainMenuTestsCard.setOnClickListener { openTestMenu() }
        binding.mainMenuResultsCard.setOnClickListener { openResults() }


        val time = Calendar.getInstance().timeInMillis
        val formatter = SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.US)
        val year = SimpleDateFormat("yyMMddHH", Locale.US).format(time)
        val month = SimpleDateFormat("MM", Locale.US).format(time)
        val day = SimpleDateFormat("dd", Locale.US).format(time)
        val hour = SimpleDateFormat("HH", Locale.US).format(time)

        // val a = (year + month + day + hour).toInt()
        val a = year.toInt()

        // val properties = MutableLiveData<DayResultProperty>()

        // lifecycleScope.launch {
        //     try {
        //         properties.value = DayResultApi.retrofitService.getGoogleSheetData()
        //     } catch (e: Exception) {
        //         properties.value = DayResultProperty(e.message ?: "", "no", mutableListOf(mutableListOf("no")))
        //     }
        // }



        // val dayResults: LiveData<List<DayResult>> =
        //     Transformations.map(properties, dayResult -> {
        //         dayResults.value.
        //
        //
        // }


        // properties.observe(this.viewLifecycleOwner) {



            // binding.date.text = properties.value.toString()
            //
            // val b = properties.value!!
            // b.values[0].add("string")


            // lifecycleScope.launch {
            //     try {
            //         DayResultApi.retrofitService.updateGoogleSheetData("asdf", "qewr")
            //     } catch (e: Exception) {
            //         Log.d("test", e.message.toString())
            //     }
            // }
        // }


    }

    private fun openResults() {
        val action = MainFragmentDirections.actionMainFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    private fun openTestMenu() {
        val action = MainFragmentDirections.actionMainFragmentToTestsMenuFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}