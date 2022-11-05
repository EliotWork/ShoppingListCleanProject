package com.example.bitok.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bitok.R
import com.example.bitok.databinding.FragmentCoinPriceListBinding
import com.example.bitok.domain.entity.CoinInfo
import com.example.bitok.presentation.adapters.CoinInfoAdapter
import com.example.bitok.presentation.viewModel.CoinViewModel

class CoinPriceListFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding: FragmentCoinPriceListBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinPriceListBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter()
    }

    private fun adapter() = with(binding) {
        val adapter = CoinInfoAdapter(requireContext())
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if(fragContainer == null){
                    launchFrag(R.id.container,coinPriceInfo.fromSymbol)
                }else{
                    requireActivity().supportFragmentManager.popBackStack()
                    launchFrag(R.id.frag_container,coinPriceInfo.fromSymbol)
                }
            }
        }
        rvCoinPriceList.adapter = adapter
        rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this@CoinPriceListFragment)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun launchFrag(id: Int,fromSymbol: String){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                id,
                CoinDetailFragment.newInstance(fromSymbol)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance():Fragment{
            return CoinPriceListFragment()
        }
    }
}