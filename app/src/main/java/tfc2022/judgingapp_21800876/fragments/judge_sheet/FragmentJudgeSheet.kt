package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.graphics.Color
import android.os.Bundle
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
import tfc2022.judgingapp_21800876.NavigationManager
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
    private lateinit var popupTrick : View
    private lateinit var popupGrab : View
    private lateinit var mainLinearLayout : LinearLayout
    private lateinit var tantrumList : List<Tantrum>
    private lateinit var tricksLinearLayout : LinearLayout
    private var offAxisClick = ""
    private var wrappedClick = ""
    private var switchClick = ""
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
        popupTrick = inflater.inflate(R.layout.trick_popup, container, false)
        popupGrab = inflater.inflate(R.layout.grab_popup, container, false)

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
            buttonEnd.setOnClickListener { endJudging() }
            //buttonGrab.setOnClickListener { setPopUpGrab() }
            buttonOffAxis.setOnClickListener { buttonAxis(buttonOffAxis) }
            buttonWrapped.setOnClickListener { buttonWrapped(buttonWrapped) }
            buttonSwitch.setOnClickListener { buttonSwitch(buttonSwitch) }
            buttonFall.setOnClickListener { buttonFall() }
        }
    }

    private fun setPopUpGrab(){
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        popupGrab.apply {
            if (parent != null) {
                (parent as ViewGroup).removeView(this)
            }
        }

        val popupWindow = PopupWindow(popupGrab, width, height)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        mainLinearLayout.setBackgroundColor(Color.GRAY)

        val nose = popupGrab.findViewById(R.id.grab_nose) as Button
        val method = popupGrab.findViewById(R.id.grab_method) as Button
        val method2 = popupGrab.findViewById(R.id.grab_method2) as Button
        val stalefish = popupGrab.findViewById(R.id.grab_stalefish) as Button
        val tailfish = popupGrab.findViewById(R.id.grab_tailfish) as Button
        val crail = popupGrab.findViewById(R.id.grab_crail) as Button
        val mute = popupGrab.findViewById(R.id.grab_mute) as Button
        val indy = popupGrab.findViewById(R.id.grab_indy) as Button
        val tindy = popupGrab.findViewById(R.id.grab_tindy) as Button
        val tail = popupGrab.findViewById(R.id.grab_tail) as Button

        //Salvar em algum lugar
        tail.setOnClickListener { closeGrab(popupWindow) }
    }

    private fun closeGrab(popupWindow: PopupWindow) {
        popupWindow.dismiss()
        mainLinearLayout.setBackgroundColor(Color.WHITE)
    }

    private fun endJudging(){
        if(validadeSheet()){
            athlete.tricks = viewModel.getAthleteListOfTricks()
            viewModel.updateTricks(athlete.tricks, athlete.name)

            NavigationManager.goToLeaderboardFragment(parentFragmentManager)
        }
    }

    private fun validadeSheet(): Boolean {
        if(viewModel.getAthleteListOfTricks().isEmpty()){
            Toast.makeText(activity, "Cannot save Sheet without tricks", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun buttonFall(){
        viewModel.addHistory("Fall,,,")
        viewModel.getHistory { updateListHistory(it) }
        viewModel.addListOfTricks("Fall","","", "")
    }

    private fun buttonAxis(botao : Button){
        if(offAxisClick.length > 0){
            offAxisClick = ""
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            offAxisClick = "OA"
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun buttonWrapped(botao : Button){
        if(wrappedClick.length > 0){
            wrappedClick = ""
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            wrappedClick = "WR"
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun buttonSwitch(botao : Button){
        if(switchClick.length > 0){
            switchClick = ""
            botao.setBackgroundColor(botao.context.resources.getColor(R.color.colorPrimary))
        }else{
            switchClick = "SW"
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    private fun minusTreeLevel(){
        tricksLinearLayout.removeAllViews()

        if(backButtonStack.size > 1) {
            for (botao in backButtonStack[backButtonStack.lastIndex - 1]) {
                createButton(botao)
            }

            backButtonStack.removeLast()
            backButtonStack.removeLast()

            showTree()

        }else{
            backButtonStack.clear()
            startButtons()
        }
    }

    private fun startButtons(){
        createButton(1, "HS", false)
        createButton(2, "TS", false)
    }

    private fun createButton(botao: Button){
        curentButtonStack.add(botao)
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
                createButton(tantrum.id, tantrum.shortname, tantrum.isManoeuvre)
            }
        }
        showTree()
    }

    private fun showTree(){
        backButtonStack.addAll(listOf(curentButtonStack.toMutableList()))
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

        curentButtonStack.clear()
    }

    private fun setPopUpTrick(trick : String) {
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        popupTrick.apply {
            if (parent != null) {
                (parent as ViewGroup).removeView(this)
            }
        }

        val popupWindow = PopupWindow(popupTrick, width, height)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        mainLinearLayout.setBackgroundColor(Color.GRAY)

        val trickName = popupTrick.findViewById(R.id.trick_name) as TextView
        val trickHeight = popupTrick.findViewById(R.id.trick_height) as TextView
        val trickWave = popupTrick.findViewById(R.id.trick_wave) as TextView
        val heightUp2 = popupTrick.findViewById(R.id.button_height_up2) as ImageButton
        val heightUp = popupTrick.findViewById(R.id.button_height_up) as ImageButton
        val heightDown = popupTrick.findViewById(R.id.button_height_down) as ImageButton
        val waveLeft = popupTrick.findViewById(R.id.button_left_wave) as ImageButton
        val waveUp = popupTrick.findViewById(R.id.button_up_wave) as ImageButton
        val waveRight = popupTrick.findViewById(R.id.button_right_wave) as ImageButton

        trickName.text = trick

        heightUp2.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_high) }
        heightUp.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_medium) }
        heightDown.setOnClickListener{ trickHeight.text = getString(R.string.trick_height_down) }

        waveLeft.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_left) }
        waveUp.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_up) }
        waveRight.setOnClickListener{ trickWave.text = getString(R.string.trick_wave_right) }

        val cancelButton = popupTrick.findViewById(R.id.button_cancel) as Button
        val confirmButton = popupTrick.findViewById(R.id.button_confirm) as Button

        cancelButton.setOnClickListener{onButtonPopUpClick(false, trick,trickHeight,
            trickWave, popupWindow)}

        confirmButton.setOnClickListener{onButtonPopUpClick(true, trick,trickHeight,
            trickWave, popupWindow)}
    }

    private fun onButtonPopUpClick(button: Boolean, trick: String, trickHeight: TextView,
                                   trickWave: TextView, popupWindow: PopupWindow) {
        if(button){
            if(trickHeight.text == "(Blank)" || trickWave.text == "(Blank)") {
                Toast.makeText(activity, "Cannot save Trick without Height or Wave", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.addHistory("$trick,${trickHeight.text},${trickWave.text}, " +
                        "$offAxisClick$wrappedClick$switchClick" )

                viewModel.getHistory { updateListHistory(it) }

                viewModel.addListOfTricks(trick, trickHeight.text.toString(),
                    trickWave.text.toString(), offAxisClick+wrappedClick+switchClick)
                trickHeight.text = getString(R.string.blank)
                trickWave.text = getString(R.string.blank)
                popupWindow.dismiss()
                cleanConfirm()
            }
        }else{
            trickHeight.text = getString(R.string.blank)
            trickWave.text = getString(R.string.blank)
            popupWindow.dismiss()
            mainLinearLayout.setBackgroundColor(Color.WHITE)
        }
    }

    private fun cleanConfirm(){
        offAxisClick = ""
        wrappedClick = ""
        switchClick = ""
        binding.buttonOffAxis.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        binding.buttonWrapped.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        binding.buttonSwitch.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        mainLinearLayout.setBackgroundColor(Color.WHITE)
        tricksLinearLayout.removeAllViews()
        backButtonStack.clear()
        startButtons()
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