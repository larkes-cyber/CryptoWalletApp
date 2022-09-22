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

    private var selectedSub:String = "free"

    lateinit var free:LinearLayout
    lateinit var ten:LinearLayout
    lateinit var fourteen:LinearLayout
    lateinit var hungred:LinearLayout

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


    private fun switchToOption(view:LinearLayout, value:String){

        val array = arrayListOf<LinearLayout>(free, ten, fourteen, hungred)
        array.forEach{it.setBackgroundResource(R.drawable.form_background)}

        view.setBackgroundResource(R.drawable.form_background_selected)

        selectedSub = value
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         free = view.findViewById(R.id.free)
         ten = view.findViewById(R.id.ten)
         fourteen = view.findViewById(R.id.fourteen)
         hungred = view.findViewById(R.id.hungred)

        free.setOnClickListener {
            switchToOption(free, "free")
        }
        ten.setOnClickListener {
            switchToOption(ten, "ten")
        }
        fourteen.setOnClickListener {
            switchToOption(fourteen, "fourteen")
        }
        hungred.setOnClickListener {
            switchToOption(hungred,"hung")
        }

        // to next
        val next: CardView = view.findViewById(R.id.next)
        val back: ImageView = view.findViewById(R.id.back)

        next.setOnClickListener {

            //setup transfer data to next fragment
            val bundle = Bundle()
            bundle.putString("email", arguments?.get("email").toString())
            bundle.putString("password", arguments?.get("password").toString())
            bundle.putString("first_name",arguments?.get("first_name").toString())
            bundle.putString("last_name",arguments?.get("last_name").toString())
            bundle.putString("image_src", arguments?.get("image_src").toString())
            bundle.putString("sub", selectedSub)

            view.findNavController().navigate(R.id.action_subFragment_to_passwordFragment, bundle)
        }
        back.setOnClickListener {
            view.findNavController().navigate(R.id.action_subFragment_to_profileSetupFragment)
        }


    }

}