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
import tfc2022.judgingapp_21800876.data.tricks.Trick
import tfc2022.judgingapp_21800876.databinding.FragmentJudgeSheetBinding
import tfc2022.judgingapp_21800876.utils.readJson

/* Fragment JudgeSheet
*
* The main screen of the app.
* Here Judges can evaluate an athlete while he or she is doing tricks
*
* This class is composed of several functions, here is a quick overview:
*
* 1 - Athlete info (Name, Front Foot)
* 2 - Tricks History (Updated when a Judge selects a trick)
* 3 - Back and End buttons, Back button goes back 1 tree level, End button finishes the evaluation
* 4 - Trick Tree, here is where judges can create a tree by level and find a trick
* 5 - Grab button, this button opens another window where the Judge can select a grab if needed
* 6 - Off-Axis, Wrapped and Switch buttons, complementary buttons for tricks
* 7 - Fall button, if an athlete fall off the board, this information is added to the history
* 8 - After selecting a trick another window appears, this window show the trick name, and the
* Judge can select options for height and wave placement
*
*/

private lateinit var binding : FragmentJudgeSheetBinding
private lateinit var viewModel : ViewModel
private const val ARG_ATH = "ARG_ATH"

class JudgeSheetFragment : Fragment() {
    private lateinit var athlete : Athlete
    private val adapterHistory = AdapterHistoryList()
    private lateinit var popupTrick : View
    private lateinit var popupGrab : View
    private lateinit var mainLinearLayout : LinearLayout
    private lateinit var trickList : List<Trick>
    private lateinit var tricksLinearLayout : LinearLayout
    private var offAxisClick = ""
    private var wrappedClick = ""
    private var switchClick = ""
    private var curentButtonStack : MutableList<Button> = mutableListOf()
    private var backButtonStack : MutableList<MutableList<Button>> = mutableListOf()
    private var grabs = "No grab"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { athlete = it.getParcelable(ARG_ATH)!! }
        trickList = readJson<List<Trick>>(requireContext(),"tricks.json")
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
        popupGrab = if(athlete.frontfoot == "Left") {
            inflater.inflate(R.layout.grab_popup_regular, container, false)
        }else{
            inflater.inflate(R.layout.grab_popup_goofy, container, false)
        }

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
            selectedGrabs.text = grabs
            buttonGrab.setOnClickListener { setPopUpGrab() }
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
        val tail = popupGrab.findViewById(R.id.grab_tail) as Button

        if(athlete.frontfoot == "Left") {
            val methodRegular = popupGrab.findViewById(R.id.grab_method_regular) as Button
            val method2Regular = popupGrab.findViewById(R.id.grab_method2_regular) as Button
            val stalefishRegular = popupGrab.findViewById(R.id.grab_stalefish_regular) as Button
            val tailfishRegular = popupGrab.findViewById(R.id.grab_tailfish_regular) as Button
            val crailRegular = popupGrab.findViewById(R.id.grab_crail_regular) as Button
            val muteRegular = popupGrab.findViewById(R.id.grab_mute_regular) as Button
            val indyRegular = popupGrab.findViewById(R.id.grab_indy_regular) as Button
            val tindyRegular = popupGrab.findViewById(R.id.grab_tindy_regular) as Button

            methodRegular.setOnClickListener { addGrab(methodRegular.text.toString(), popupWindow) }
            method2Regular.setOnClickListener { addGrab(method2Regular.text.toString(), popupWindow) }
            stalefishRegular.setOnClickListener { addGrab(stalefishRegular.text.toString(), popupWindow) }
            tailfishRegular.setOnClickListener { addGrab(tailfishRegular.text.toString(), popupWindow) }
            crailRegular.setOnClickListener { addGrab(crailRegular.text.toString(), popupWindow) }
            muteRegular.setOnClickListener { addGrab(muteRegular.text.toString(), popupWindow) }
            indyRegular.setOnClickListener { addGrab(indyRegular.text.toString(), popupWindow) }
            tindyRegular.setOnClickListener { addGrab(tindyRegular.text.toString(), popupWindow) }

        }else {
            val methodGoofy = popupGrab.findViewById(R.id.grab_method_goofy) as Button
            val method2Goofy = popupGrab.findViewById(R.id.grab_method2_goofy) as Button
            val stalefishGoofy = popupGrab.findViewById(R.id.grab_stalefish_goofy) as Button
            val tailfishGoofy = popupGrab.findViewById(R.id.grab_tailfish_goofy) as Button
            val crailGoofy = popupGrab.findViewById(R.id.grab_crail_goofy) as Button
            val muteGoofy = popupGrab.findViewById(R.id.grab_mute_goofy) as Button
            val indyGoofy = popupGrab.findViewById(R.id.grab_indy_goofy) as Button
            val tindyGoofy = popupGrab.findViewById(R.id.grab_tindy_goofy) as Button

            nose.setOnClickListener { addGrab(nose.text.toString(), popupWindow) }
            tail.setOnClickListener { addGrab(tail.text.toString(), popupWindow) }

            methodGoofy.setOnClickListener { addGrab(methodGoofy.text.toString(), popupWindow) }
            method2Goofy.setOnClickListener { addGrab(method2Goofy.text.toString(), popupWindow) }
            stalefishGoofy.setOnClickListener { addGrab(stalefishGoofy.text.toString(), popupWindow) }
            tailfishGoofy.setOnClickListener { addGrab(tailfishGoofy.text.toString(), popupWindow) }
            crailGoofy.setOnClickListener { addGrab(crailGoofy.text.toString(), popupWindow) }
            muteGoofy.setOnClickListener { addGrab(muteGoofy.text.toString(), popupWindow) }
            indyGoofy.setOnClickListener { addGrab(indyGoofy.text.toString(), popupWindow) }
            tindyGoofy.setOnClickListener { addGrab(tindyGoofy.text.toString(), popupWindow) }
        }

    }

    private fun addGrab(grab : String, popupWindow: PopupWindow){
        if(grabs == "No grab"){
            grabs = ""
        }
        grabs += grab + "\n"
        binding.selectedGrabs.text = grabs
        closeGrab(popupWindow)
    }

    private fun closeGrab(popupWindow: PopupWindow) {
        popupWindow.dismiss()
        mainLinearLayout.setBackgroundColor(Color.WHITE)
    }

    private fun endJudging(){
        if(validadeSheet()){
            athlete.tricks = viewModel.getAthleteListOfTricks()
            viewModel.updateTricks(athlete.tricks, athlete.name)

            athlete.grabs = viewModel.getAthleteListOfGrabs()
            viewModel.updateGrabs(athlete.grabs, athlete.name)

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
        for (trick in trickList){
            if(trick.parent[0] == botao.tag){
                createButton(trick.id, trick.shortname, trick.isManoeuvre)
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
                viewModel.addListOfGrabs(grabs)
                trickHeight.text = getString(R.string.blank)
                trickWave.text = getString(R.string.blank)
                grabs = "No grab"
                binding.selectedGrabs.text = grabs
                popupWindow.dismiss()
                cleanConfirm()
            }
        }else{
            trickHeight.text = getString(R.string.blank)
            trickWave.text = getString(R.string.blank)
            grabs = "No grab"
            binding.selectedGrabs.text = grabs
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