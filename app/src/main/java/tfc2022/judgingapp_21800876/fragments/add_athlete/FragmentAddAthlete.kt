package tfc2022.judgingapp_21800876.fragments.add_athlete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.AthleteViewModel
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentAddAthleteBinding

private lateinit var binding : FragmentAddAthleteBinding
private lateinit var athleteViewModel : AthleteViewModel

class AddAthleteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_athlete)

        //Layout
        val view = inflater.inflate(R.layout.fragment_add_athlete, container, false)

        //Binding
        binding = FragmentAddAthleteBinding.bind(view)

        //ViewModel
        athleteViewModel = ViewModelProvider(this)[AthleteViewModel::class.java]
        return binding.root
    }

    override fun onStart(){
        super.onStart()

        binding.buttonSave.setOnClickListener{ saveAthlete() }
        categorySpinnerSetup()
        frontfootSpinnerSetup()
        countrySpinnerSetup()
    }

    private fun categorySpinnerSetup(){
        val spinner: Spinner = binding.spinnerCategory
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun frontfootSpinnerSetup(){
        val spinner: Spinner = binding.spinnerFrontFoot
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.frontfoot_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun countrySpinnerSetup(){
        val spinner: Spinner = binding.spinnerCountry
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.country_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun saveAthlete(){
        val name = binding.nameInput.text.toString()
        val age = binding.ageInput.text.toString()
        val category = binding.spinnerCategory.selectedItem.toString()
        val frontfoot = binding.spinnerFrontFoot.selectedItem.toString()
        val country = binding.spinnerCountry.selectedItem.toString()

        athleteViewModel.addAthlete(Athlete(name,age,category,frontfoot,country))

        Toast.makeText(activity, "Athlete registered!", Toast.LENGTH_SHORT).show()
        binding.nameInput.text.clear()
        binding.ageInput.text.clear()
    }
}