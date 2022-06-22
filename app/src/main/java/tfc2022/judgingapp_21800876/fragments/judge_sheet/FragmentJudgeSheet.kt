package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.AthleteViewModel
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentJudgeSheetBinding


private lateinit var binding : FragmentJudgeSheetBinding
private lateinit var athleteViewModel : AthleteViewModel
private const val ARG_ATH = "ARG_ATH"

class JudgeSheetFragment : Fragment() {
    private var athlete : Athlete? = null
    private val adapterRaley = AdapterRaleyList(onClick = ::onItemClick)
    private val adapterTantrum = AdapterTantrumList(onClick = ::onItemClick)
    private val adapterRoll = AdapterRollList(onClick = ::onItemClick)
    private val adapterHistory = AdapterHistoryList()
    private lateinit var popup : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { athlete = it.getParcelable(ARG_ATH) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_judge_sheet)

        //Layout
        val view = inflater.inflate(R.layout.fragment_judge_sheet, container, false)
        popup = inflater.inflate(R.layout.trick_popup, container, false)

        //Binding
        binding = FragmentJudgeSheetBinding.bind(view)

        //ViewModel
        athleteViewModel = ViewModelProvider(this)[AthleteViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.raleysList.layoutManager = LinearLayoutManager(requireContext())
        binding.raleysList.adapter = adapterRaley
        athleteViewModel.getRaleys{ updateListRaley(it) }

        binding.tantrumsList.layoutManager = LinearLayoutManager(requireContext())
        binding.tantrumsList.adapter = adapterTantrum
        athleteViewModel.getTantrums{ updateListTantrum(it) }

        binding.rollsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rollsList.adapter = adapterRoll
        athleteViewModel.getRolls{ updateListRoll(it) }

        binding.historyList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyList.adapter = adapterHistory

        athlete?.let{
            binding.athleteInfoName.text = it.name
            binding.athleteInfoAge.text = it.age
            binding.athleteInfoCountry.text = it.country
            binding.athleteInfoFrontFoot.text = it.frontfoot
        }

    }

    private fun setPopUp() {
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popup, width, height)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    private fun onItemClick(trick: String) {
        //setPopUp()
        athleteViewModel.addHistory(trick)
        athleteViewModel.getHistory{ updateListHistory(it) }
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