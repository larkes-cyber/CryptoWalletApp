package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptowalletapp.App.App
import com.example.cryptowalletapp.R
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.presentation.adapters.top_coins_adapter.TopCoinsAdapter
import com.example.cryptowalletapp.presentation.viewmodel.home_view_model.HomeViewModel
import com.example.cryptowalletapp.presentation.viewmodel.home_view_model.HomeViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Math.round
import java.util.Currency
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory:HomeViewModelFactory
    val vm by lazy {
        ViewModelProvider(this,vmFactory).get(HomeViewModel::class.java)
    }

    private val topCoinAdapter = TopCoinsAdapter()
    private var topCoinRcView:RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.injectHomeFragment(this)

        //for first currency
        val currencyImg:ImageView = view.findViewById(R.id.curr_img)
        val currencyName:TextView = view.findViewById(R.id.curr_name)
        val currencyPrice:TextView = view.findViewById(R.id.curr_price)
        val currencyPerc:TextView = view.findViewById(R.id.curr_perc)
        val progressForFirst:RelativeLayout = view.findViewById(R.id.progress_for_first_curr)

        val progressForTopCoins:ProgressBar = view.findViewById(R.id.progress_top_coins)

        topCoinRcView = view.findViewById(R.id.top_coins_rc)

        //get info about first currency
        vm.lifeFirstCurrencyResultConst.observe(viewLifecycleOwner){
            Picasso.get().load(it.src).into(currencyImg)
            currencyName.text = it.name
            currencyPrice.text = vm.getPrice(it.price)
           // currencyPerc.text = vm.findPerc(it.priceHistory[0],it.priceHistory[it.priceHistory.size - 1]) + "%"

            progressForFirst.visibility = View.GONE
        }

        val lManager = LinearLayoutManager(requireContext())
        lManager.orientation = LinearLayoutManager.HORIZONTAL

        //top coins
        vm.lifeTopCoinsResultConst.observe(viewLifecycleOwner){data ->

            topCoinRcView!!.layoutManager = lManager
            topCoinRcView!!.adapter = topCoinAdapter
            progressForTopCoins.visibility = View.GONE
            topCoinAdapter.addTasks(data as MutableList<CoinInfo>)

        }

        launch {
            vm.getSmallCoinInfo("btc-bitcoin")
            vm.getTopCoins()
        }


    }


}