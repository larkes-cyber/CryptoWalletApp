package com.example.cryptowalletapp.presentation.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.cryptowalletapp.R
import okhttp3.internal.concurrent.Task


class PasswordFragment : Fragment() {

    var pinCode = ArrayList<Int>()

    lateinit var firstCheck:LinearLayout
    lateinit var secondCheck:LinearLayout
    lateinit var thirdCheck:LinearLayout
    lateinit var fourthCheck:LinearLayout

    var checkItems:List<LinearLayout>? = null
    var countOfItems = 0


    private fun numArrayToString(array:MutableList<Int>):String{

        var string = ""
        array.forEach { string += it.toString() }

        return string
    }

    fun toNextFragment(){
        val bundle = Bundle()
        bundle.putString("email", arguments?.get("email").toString())
        bundle.putString("password", arguments?.get("password").toString())
        bundle.putString("first_name",arguments?.get("first_name").toString())
        bundle.putString("last_name",arguments?.get("last_name").toString())
        bundle.putString("image_src", arguments?.get("image_src").toString())
        bundle.putString("sub", arguments?.get("sub").toString())
        bundle.putString("pincode",numArrayToString(pinCode))
        bundle.putInt("kind", 1)

        requireView().findNavController().navigate(R.id.action_passwordFragment_to_loadingFragment, bundle)
    }

    private fun toAnimate(item:TextView){
            // Scale the view up to 4x its default size and back
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f)
            val animator = ObjectAnimator.ofPropertyValuesHolder(item, scaleX, scaleY)
            animator.repeatCount = 1
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.disableViewDuringAnimation(item)
            animator.start()
    }

    private fun toPutNumPass(num:Int){
        pinCode.add(num)
        if(countOfItems == checkItems!!.size-1) toNextFragment()
        checkItems!![countOfItems].setBackgroundResource(R.drawable.checked_password)
        countOfItems++
    }
    private fun toDeleteNum(it:TextView){
        toAnimate(it)
        if(countOfItems == 0) return
        countOfItems--
        checkItems!![countOfItems].setBackgroundResource(R.drawable.check_password)
        pinCode.removeAt(countOfItems)
        Log.d("pincode",pinCode.toString())
    }

    private fun buttonAction(item:TextView){
        toAnimate(item)
        toPutNumPass(item.text.toString().toInt())
        Log.d("pincode",pinCode.toString())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstCheck = view.findViewById(R.id.first_check)
        secondCheck = view.findViewById(R.id.second_check)
        thirdCheck = view.findViewById(R.id.third_check)
        fourthCheck = view.findViewById(R.id.fourth_check)
        checkItems = arrayListOf(firstCheck, secondCheck, thirdCheck, fourthCheck)

        //service buttons
        val next:TextView = view.findViewById(R.id.complete)
        val delete:TextView = view.findViewById(R.id.delete)

        delete.setOnClickListener {
            toDeleteNum(delete)
        }
        next.setOnClickListener {
            toAnimate(next)

            //setup transfer data to next fragment
            toNextFragment()

        }

        // set onclick to pass buttons
        val back:ImageView = view.findViewById(R.id.back)
        val one:TextView = view.findViewById(R.id.one)
        val two:TextView = view.findViewById(R.id.two)
        val three:TextView = view.findViewById(R.id.three)
        val four:TextView = view.findViewById(R.id.four)
        val five:TextView = view.findViewById(R.id.five)
        val six:TextView = view.findViewById(R.id.six)
        val seven:TextView = view.findViewById(R.id.seven)
        val eight:TextView = view.findViewById(R.id.eight)
        val nine:TextView = view.findViewById(R.id.nine)
        val ziro:TextView = view.findViewById(R.id.ziro)

        one.setOnClickListener {
            buttonAction(one)
        }
        two.setOnClickListener {
            buttonAction(two)
        }
        three.setOnClickListener {
            buttonAction(three)
        }
        four.setOnClickListener {
            buttonAction(four)
        }
        five.setOnClickListener {
            buttonAction(five)
        }
        six.setOnClickListener {
            buttonAction(six)
        }
        seven.setOnClickListener {
            buttonAction(seven)
        }
        eight.setOnClickListener {
            buttonAction(eight)
        }
        nine.setOnClickListener {
            buttonAction(nine)
        }
        ziro.setOnClickListener {
            buttonAction(ziro)
        }


        back.setOnClickListener {
            view.findNavController().navigate(R.id.action_passwordFragment_to_subFragment)
        }

    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        // This extension method listens for start/end events on an animation and disables
        // the given view for the entirety of that animation.

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

}