package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowalletapp.App.App
import com.example.cryptowalletapp.R
import com.example.cryptowalletapp.domain.model.UserData
import com.example.cryptowalletapp.presentation.viewmodel.loading_view_model.LoadingViewModel
import com.example.cryptowalletapp.presentation.viewmodel.loading_view_model.LoadingViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoadingFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory:LoadingViewModelFactory

    val vm by lazy {
        ViewModelProvider(this, vmFactory).get(LoadingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.injectLoadingFragment(this)

        Log.d("data_user","email: ${arguments?.get("email")}")
        Log.d("data_user","password: ${arguments?.get("password")}")
        Log.d("data_user","last_name: ${arguments?.get("last_name")}")
        Log.d("data_user","first_name: ${arguments?.get("first_name")}")
        Log.d("data_user","image_src: ${arguments?.get("image_src")}")
        Log.d("data_user","pincode: ${arguments?.get("pincode")}")
        Log.d("data_user","sub: ${arguments?.get("sub")}")

        val kind = arguments?.getInt("kind")

        if(kind == 1){

            val data = UserData(
                email = arguments?.get("email").toString(),
                pass = arguments?.get("password").toString(),
                firstName = arguments?.get("first_name").toString(),
                lastName = arguments?.get("last_name").toString(),
                sub = arguments?.get("sub").toString(),
                pincode = arguments?.get("pincode").toString()
            )

            launch {
                vm.saveData(data)
            }

        }


    }

}