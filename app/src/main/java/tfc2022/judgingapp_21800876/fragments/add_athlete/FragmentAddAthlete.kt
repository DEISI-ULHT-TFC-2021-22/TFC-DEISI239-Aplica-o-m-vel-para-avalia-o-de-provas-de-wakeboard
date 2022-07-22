package tfc2022.judgingapp_21800876.fragments.add_athlete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentAddAthleteBinding

/* Fragment Add Athelete
*
* This class objective is to add athletes to a database working with ViewModel
* Evey athlete add is a persistent data for judges to have all the info on the tablet.
*
* The information needed to add an athlete is Name, Age, Country, Category, Front Foot
*/

private lateinit var binding : FragmentAddAthleteBinding
private lateinit var viewModel : ViewModel

class AddAthleteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_athlete)

        //Layout
        val view = inflater.inflate(R.layout.fragment_add_athlete, container, false)

        //Binding
        binding = FragmentAddAthleteBinding.bind(view)

        //ViewModel
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        return binding.root
    }

    override fun onStart(){
        super.onStart()

        binding.buttonSave.setOnClickListener{ saveAthlete() }
        categorySpinnerSetup()
        frontfootSpinnerSetup()
        countryAutoComplete()
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

    private fun countryAutoComplete(){
        val auto: AutoCompleteTextView = binding.countryInput
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.country_array,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            auto.setAdapter(adapter)
        }
    }

    private fun validateForm(name: String, age: String, country: String): Boolean {
        if(name == "" || age == "" || country == ""){
            return false
        }
        return true
    }

    private fun saveAthlete(){
        val name = binding.nameInput.text.toString()
        val age = binding.ageInput.text.toString()
        val category = binding.spinnerCategory.selectedItem.toString()
        val frontfoot = binding.spinnerFrontFoot.selectedItem.toString()
        val country = binding.countryInput.text.toString()

        if(validateForm(name, age, country)) {
            viewModel.addAthlete(
                Athlete(name, age, category, frontfoot, country, "", "",
                false, "", "", "", 0.0)
            )

            Toast.makeText(activity, "Athlete registered!", Toast.LENGTH_SHORT).show()
            binding.nameInput.text.clear()
            binding.ageInput.text.clear()
            binding.countryInput.text.clear()
        }else{
            Toast.makeText(activity, "Name, Age and Country required.", Toast.LENGTH_SHORT).show()
        }
    }
}