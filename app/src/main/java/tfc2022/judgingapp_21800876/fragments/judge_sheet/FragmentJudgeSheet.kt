package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.data.tantrums.Tantrum
import tfc2022.judgingapp_21800876.databinding.FragmentJudgeSheetBinding
import tfc2022.judgingapp_21800876.utils.readJson

private lateinit var binding : FragmentJudgeSheetBinding
private lateinit var viewModel : ViewModel
private const val ARG_ATH = "ARG_ATH"

class JudgeSheetFragment : Fragment() {
    private lateinit var athlete : Athlete
    private val adapterHistory = AdapterHistoryList()
    private lateinit var popup : View
    private lateinit var mainLinearLayout : LinearLayout
    private lateinit var tantrumList : List<Tantrum>
    private lateinit var tricksLinearLayout : LinearLayout
    private var offAxisClick = false
    private var wrappedClick = false
    private var switchClick = false
    private var curentButtonStack : MutableList<Button> = mutableListOf()
    private var backButtonStack : MutableList<MutableList<Button>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { athlete = it.getParcelable(ARG_ATH)!! }
        tantrumList = readJson<List<Tantrum>>(requireContext(),"tantrums.json")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_judge_sheet)

        //Layout
        val view = inflater.inflate(R.layout.fragment_judge_sheet, container, false)

        //MainLinearLayout for background color change + popup
        mainLinearLayout = view.findViewById(R.id.mainLinear) as LinearLayout
        mainLinearLayout.setBackgroundColor(Color.WHITE)
        popup = inflater.inflate(R.layout.trick_popup, container, false)

        //LinearLayout for trick tree
        tricksLinearLayout = view.findViewById(R.id.sheet_tricks) as LinearLayout

        //Binding
        binding = FragmentJudgeSheetBinding.bind(view)

        //ViewModel
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.historyList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        binding.historyList.adapter = adapterHistory

        athlete.let{
            binding.athleteInfoName.text = it.name
            binding.athleteInfoFrontFoot.text = it.frontfoot
        }

        startButtons()

        with(binding) {
            buttonBack.setOnClickListener { minusTreeLevel() }
            buttonEnd.setOnClickListener {  }
            buttonGrab.setOnClickListener { setPopUpGrab() }
            buttonOffAxis.setOnClickListener { buttonAxis(buttonOffAxis) }
            buttonWrapped.setOnClickListener { buttonWrapped(buttonWrapped) }
            buttonSwitch.setOnClickListener { buttonSwitch(buttonSwitch) }
            buttonFall.setOnClickListener { buttonFall() }
        }
    }

    private fun buttonFall(){
        viewModel.addHistory("Fall,,")
        viewModel.getHistory { updateListHistory(it) }
        viewModel.addListOfTricks("Fall","","")
    }

    private fun buttonAxis(botao : Button){
        if(offAxisClick){
            offAxisClick = false
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            offAxisClick = true
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun buttonWrapped(botao : Button){
        if(wrappedClick){
            wrappedClick = false
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            wrappedClick = true
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun buttonSwitch(botao : Button){
        if(switchClick){
            switchClick = false
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            switchClick = true
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun minusTreeLevel(){
        //tricksLinearLayout.removeAllViews()
        TODO()
    }

    private fun startButtons(){
        createButton(1, "HS", false)
        createButton(2, "TS", false)
    }

    private fun createButton(id : Int, text : String, finish : Boolean){
        val botao = Button(requireContext())
        botao.tag = id
        botao.text = text
        botao.textSize = 25F
        botao.setTextColor(Color.WHITE)
        botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))

        if(finish) {
            botao.setOnClickListener { setPopUpTrick(text) }
        }else{
            botao.setOnClickListener { createTree(botao) }
        }

        if(id == 1 || id == 2){
            val space = Space(requireContext())

            tricksLinearLayout.addView(
                botao, LinearLayoutCompat.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )

            tricksLinearLayout.addView(space, LinearLayoutCompat.LayoutParams(50, 50))
        }else {
            curentButtonStack.add(botao)
        }
    }

    private fun createTree(botao : Button){
        for (tantrum in tantrumList){
            if(tantrum.parent[0] == botao.tag){
                if(!tantrum.isManoeuvre) {
                    createButton(tantrum.id, tantrum.shortname, false)
                }else{
                    createButton(tantrum.id, tantrum.shortname, true)
                }
            }
        }
        showTree()
    }

    private fun showTree(){
        backButtonStack.toMutableList().add(curentButtonStack)
        tricksLinearLayout.removeAllViews()

        for(botao in curentButtonStack) {
            val space = Space(requireContext())

            tricksLinearLayout.addView(
                botao, LinearLayoutCompat.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )

            tricksLinearLayout.addView(space, LinearLayoutCompat.LayoutParams(50, 50))
        }

        Log.d("Stack Current = ", curentButtonStack.toString())

        curentButtonStack.clear()

        Log.d("Stack Back = ", backButtonStack.toString())
    }

    private fun setPopUpGrab(){

    }

    private fun setPopUpTrick(trick : String) {
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        popup.apply {
            if (parent != null) {
                (parent as ViewGroup).removeView(this)
            }
        }

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

        cancelButton.setOnClickListener{onButtonPopUpClick(false, trick,trickHeight, trickWave, popupWindow)}

        confirmButton.setOnClickListener{onButtonPopUpClick(true, trick,trickHeight, trickWave, popupWindow)}
    }

    private fun onButtonPopUpClick(button: Boolean, trick: String, trickHeight: TextView,
                                   trickWave: TextView, popupWindow: PopupWindow) {
        if(button){
            if(trickHeight.text == "(Blank)" || trickWave.text == "(Blank)") {
                Toast.makeText(activity, "Cannot save Trick without Height or Wave", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.addHistory("$trick,${trickHeight.text},${trickWave.text}")
                viewModel.getHistory { updateListHistory(it) }
                viewModel.addListOfTricks(trick, trickHeight.text.toString(), trickWave.text.toString())
                trickHeight.text = getString(R.string.blank)
                trickWave.text = getString(R.string.blank)
                popupWindow.dismiss()
                mainLinearLayout.setBackgroundColor(Color.WHITE)
            }
        }else{
            trickHeight.text = getString(R.string.blank)
            trickWave.text = getString(R.string.blank)
            popupWindow.dismiss()
            mainLinearLayout.setBackgroundColor(Color.WHITE)
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