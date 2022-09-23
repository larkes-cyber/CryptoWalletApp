package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.cryptowalletapp.App.App
import com.example.cryptowalletapp.R
import com.example.cryptowalletapp.presentation.viewmodel.already_there_view_model.AlreadyThereViewModel
import com.example.cryptowalletapp.presentation.viewmodel.already_there_view_model.AlreadyThereViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class AlreadyThereFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory:AlreadyThereViewModelFactory
    val vm by lazy {
        ViewModelProvider(this,vmFactory).get(AlreadyThereViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_already_there, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.injectAlreadyThere(this)

        vm.lifeIsThereResultConst.observe(viewLifecycleOwner){

            if(it){
                view.findNavController().navigate(R.id.action_alreadyThereFragment_to_homeFragment)
            }else{
                view.findNavController().navigate(R.id.action_alreadyThereFragment_to_welcomeFragment)
            }

        }

        launch {
            vm.checkUser()
        }


    }

}