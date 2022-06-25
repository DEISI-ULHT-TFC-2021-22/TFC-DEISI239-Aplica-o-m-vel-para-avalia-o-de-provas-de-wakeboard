package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.NavigationManager
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentJudgeSheetBinding

private lateinit var binding : FragmentJudgeSheetBinding
private lateinit var viewModel : ViewModel
private const val ARG_ATH = "ARG_ATH"

class JudgeSheetFragment : Fragment() {
    private lateinit var athlete : Athlete
    private val adapterRaley = AdapterRaleyList(onClick = ::onItemClick)
    private val adapterTantrum = AdapterTantrumList(onClick = ::onItemClick)
    private val adapterRoll = AdapterRollList(onClick = ::onItemClick)
    private val adapterHistory = AdapterHistoryList()
    private lateinit var popup : View
    private lateinit var background : ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { athlete = it.getParcelable(ARG_ATH)!! }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_judge_sheet)

        //Layout
        val view = inflater.inflate(R.layout.fragment_judge_sheet, container, false)
        background = view.findViewById(R.id.scrollView) as ScrollView
        background.setBackgroundColor(Color.WHITE)
        popup = inflater.inflate(R.layout.trick_popup, container, false)

        //Binding
        binding = FragmentJudgeSheetBinding.bind(view)

        //ViewModel
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.raleysList.layoutManager = LinearLayoutManager(requireContext())
        binding.raleysList.adapter = adapterRaley
        viewModel.getRaleys{ updateListRaley(it) }

        binding.tantrumsList.layoutManager = LinearLayoutManager(requireContext())
        binding.tantrumsList.adapter = adapterTantrum
        viewModel.getTantrums{ updateListTantrum(it) }

        binding.rollsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rollsList.adapter = adapterRoll
        viewModel.getRolls{ updateListRoll(it) }

        binding.historyList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.historyList.adapter = adapterHistory

        athlete.let{
            binding.athleteInfoName.text = it.name
            binding.athleteInfoCountry.text = it.country
            binding.athleteInfoFrontFoot.text = it.frontfoot
        }

        binding.buttonFinish.setOnClickListener { finishJudgeSheet() }
        binding.buttonFall.setOnClickListener { fallJudgeSheet() }
    }

    private fun fallJudgeSheet(){
        athlete.fall = true
        NavigationManager.goToLeaderboardFragment(parentFragmentManager)
    }

    private fun finishJudgeSheet(){
        if(validadeJudgeSheet()) {
            athlete.tricks = viewModel.getAthleteListOfTricks()
            athlete.execution = binding.executionInput.text.toString()
            athlete.intensity = binding.intensityInput.text.toString()
            athlete.comprehension = binding.comprehensionInput.text.toString()
            athlete.score =
                calculateScore(athlete.execution, athlete.intensity, athlete.comprehension)

            viewModel.updateTricks(athlete.tricks, athlete.name)

            viewModel.updateExecution(athlete.tricks, athlete.name)
            viewModel.updateIntensity(athlete.tricks, athlete.name)
            viewModel.updateComprehension(athlete.tricks, athlete.name)

            viewModel.updateScore(athlete.score, athlete.name)

            NavigationManager.goToLeaderboardFragment(parentFragmentManager)
        }
    }

    private fun validadeJudgeSheet(): Boolean {
        if(binding.executionInput.text.toString() == "" || binding.intensityInput.text.toString() == ""
            || binding.comprehensionInput.text.toString() == ""){
            Toast.makeText(activity, "Cannot save Sheet without a score", Toast.LENGTH_SHORT).show()
            return false
        }
        if(viewModel.getAthleteListOfTricks().isEmpty()){
            Toast.makeText(activity, "Cannot save Sheet without tricks", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun calculateScore(execution: String?, intensity: String?, comprehension: String?): Double {
        return (execution?.toDouble()!! + intensity?.toDouble()!! + comprehension?.toDouble()!!) * 3.33
    }

    private fun setPopUp(trick : String) {
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popup, width, height)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        val trickName = popup.findViewById(R.id.trick_name) as TextView
        val trickHeight = popup.findViewById(R.id.trick_height) as TextView
        val trickWave = popup.findViewById(R.id.trick_wave) as TextView
        val heightUp2 = popup.findViewById(R.id.button_height_up2) as ImageButton
        val heightUp = popup.findViewById(R.id.button_height_up) as ImageButton
        val heightDown = popup.findViewById(R.id.button_height_down) as ImageButton
        val waveLeft = popup.findViewById(R.id.button_left_wave) as ImageButton
        val waveUp = popup.findViewById(R.id.button_up_wave) as ImageButton
        val waveRight = popup.findViewById(R.id.button_right_wave) as ImageButton

        trickName.text = trick

        heightUp2.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_high) }
        heightUp.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_medium) }
        heightDown.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_down) }

        waveLeft.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_left) }
        waveUp.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_up) }
        waveRight.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_right) }

        val cancelButton = popup.findViewById(R.id.button_cancel) as Button
        val confirmButton = popup.findViewById(R.id.button_confirm) as Button

        cancelButton.setOnClickListener{onButtonClick(false, trick,trickHeight, trickWave, popupWindow)}

        confirmButton.setOnClickListener{onButtonClick(true, trick,trickHeight, trickWave, popupWindow)}
    }

    private fun onButtonClick(button: Boolean, trick: String, trickHeight: TextView,
                              trickWave: TextView, popupWindow: PopupWindow) {
        if(button){
            if(trickHeight.text == "(Blank)" || trickWave.text == "(Blank)") {
                Toast.makeText(activity, "Cannot save Trick without Height or Wave", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.addHistory(trick)
                viewModel.getHistory { updateListHistory(it) }
                viewModel.addListOfTricks(trick)
                trickHeight.text = getString(R.string.blank)
                trickWave.text = getString(R.string.blank)
                popupWindow.dismiss()
                background.setBackgroundColor(Color.WHITE)
            }
        }else{
            trickHeight.text = getString(R.string.blank)
            trickWave.text = getString(R.string.blank)
            popupWindow.dismiss()
            background.setBackgroundColor(Color.WHITE)
        }
    }

    private fun onItemClick(trick: String) {
        background.setBackgroundColor(Color.GRAY)
        setPopUp(trick)
    }

    private fun updateListRaley(raleyList : List<String>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterRaley.updateItems(raleyList)
        }
    }

    private fun updateListTantrum(tantrumList : List<String>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterTantrum.updateItems(tantrumList)
        }
    }

    private fun updateListRoll(rollList : List<String>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterRoll.updateItems(rollList)
        }
    }

    private fun updateListHistory(historyList : List<String>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterHistory.updateItems(historyList)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(athlete : Athlete) =
            JudgeSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ATH, athlete)
                }
            }
    }
}