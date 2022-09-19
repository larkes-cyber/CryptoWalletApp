package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.cryptowalletapp.R


class LogInFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val next: CardView = view.findViewById(R.id.next)
        val turn: TextView = view.findViewById(R.id.turn)

        turn.setOnClickListener {
            view.findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }
//        next.setOnClickListener {
//            view.findNavController().navigate(R.id.action_signUpFragment_to_profileSetupFragment)
//        }

    }


}