package com.example.cryptowalletapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.cryptowalletapp.R


class SubFragment : Fragment() {

    private var selectedSub:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val free:LinearLayout = view.findViewById(R.id.free)
        val ten:LinearLayout = view.findViewById(R.id.ten)
        val fourteen:LinearLayout = view.findViewById(R.id.fourteen)
        val hungred:LinearLayout = view.findViewById(R.id.hungred)

        free.setOnClickListener {

        }

        // to next
        val next: CardView = view.findViewById(R.id.next)
        val back: ImageView = view.findViewById(R.id.back)

        next.setOnClickListener {
            view.findNavController().navigate(R.id.action_subFragment_to_passwordFragment)
        }
        back.setOnClickListener {
            view.findNavController().navigate(R.id.action_subFragment_to_profileSetupFragment)
        }


    }

}