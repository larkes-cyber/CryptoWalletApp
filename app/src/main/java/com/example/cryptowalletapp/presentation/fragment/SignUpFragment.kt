package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.cryptowalletapp.App.App
import com.example.cryptowalletapp.R
import com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel.SignUpViewModel
import com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel.SignUpViewModelFactory
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var vmFactory:SignUpViewModelFactory
    val vm by lazy {
        ViewModelProvider(this,vmFactory).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as App).appComponent.injectSignUpFragment(this)

        //check data
        val email:EditText = view.findViewById(R.id.email)
        val password:EditText = view.findViewById(R.id.password)
        val repeatPassword:EditText = view.findViewById(R.id.repeat_pass)

        //to next fragments
        val next: CardView = view.findViewById(R.id.next)
        val turn:TextView = view.findViewById(R.id.turn)
        turn.setOnClickListener {
            view.findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
        }
        next.setOnClickListener {
            toNextFragment(email = email.text.toString(), pass = password.text.toString(), repPass = repeatPassword.text.toString())
        }

    }

    private fun toNextFragment(email:String, pass:String, repPass:String){
        //if input data is normal
        if(vm.checkPass(pass) && (pass == repPass) && vm.checkEmail(email)){

            //setup transfer data to next fragment
            val bundle = Bundle()
            bundle.putString("email", email)
            bundle.putString("password", pass)

            requireView().findNavController().navigate(R.id.action_signUpFragment_to_profileSetupFragment, bundle)
        }
    }

}