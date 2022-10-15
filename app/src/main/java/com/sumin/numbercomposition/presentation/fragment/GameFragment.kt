package com.sumin.numbercomposition.presentation.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.sumin.numbercomposition.R
import com.sumin.numbercomposition.databinding.FragmentGameBinding
import com.sumin.numbercomposition.domain.entity.GameResult
import com.sumin.numbercomposition.domain.entity.GameSettings
import com.sumin.numbercomposition.domain.entity.Level
import com.sumin.numbercomposition.presentation.viewModel.GameViewModel
import com.sumin.numbercomposition.presentation.viewModel.viewmodelFactory.GameViewModelFactory

class GameFragment : Fragment() {
    private lateinit var level: Level
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
          GameViewModelFactory(
              level,
              requireActivity().application
          )
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            with(binding){
                add(tvOption1)
                add(tvOption2)
                add(tvOption3)
                add(tvOption4)
                add(tvOption5)
                add(tvOption6)
            }
        }
    }
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions()= with(binding){
        for(tvOption in tvOptions){
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel()= with(binding){
        viewModel.question.observe(viewLifecycleOwner){
            tvSum.text = it.sum.toString()
            tvLeftNumber.text = it.visibleNumber.toString()

            for(i in 0 until tvOptions.size){
                tvOptions[i].text = it.options[i].toString()
            }
        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner){
            progressBar.setProgress(it,true)
        }

        viewModel.enoughCount.observe(viewLifecycleOwner){

            tvAnswersProgress.setTextColor(getColorByState(it))
        }

        viewModel.enoughPercent.observe(viewLifecycleOwner){
            val color = getColorByState(it)
            progressBar.progressTintList = ColorStateList.valueOf(color)
        }

        viewModel.formattedTime.observe(viewLifecycleOwner){
            tvTimer.text = it
        }

        viewModel.minPercent.observe(viewLifecycleOwner){
            progressBar.secondaryProgress = it
        }

        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }

        viewModel.progressAnswers.observe(viewLifecycleOwner){
            tvAnswersProgress.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getColorByState(goodState: Boolean):Int{
        val colorResId = if(goodState){
            android.R.color.holo_green_light
        }else{
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(),colorResId)
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}
