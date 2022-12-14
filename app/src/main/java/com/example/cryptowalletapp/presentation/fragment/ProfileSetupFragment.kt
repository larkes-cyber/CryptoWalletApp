package com.example.cryptowalletapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.cryptowalletapp.R



class ProfileSetupFragment : Fragment() {


    private var SELECT_IMAGE_CODE = 1
    lateinit var image_profile:ImageView

    lateinit var error:TextView

    private fun showGallery(code:Int){
        val intent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )

        intent.setType("image/*")
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT)

        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)



        startActivityForResult(Intent.createChooser(intent,"title"),code )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageButton:CardView = view.findViewById(R.id.profile)



        //an error
        error = view.findViewById(R.id.error)

        //to select image profile
        imageButton.setOnClickListener {
            showGallery(SELECT_IMAGE_CODE)
        }

        //profile image
        image_profile = view.findViewById(R.id.img_profile)
        image_profile.tag = "null"

        //names
        val firstName:EditText = view.findViewById(R.id.first)
        val lastName:EditText = view.findViewById(R.id.last)

        // to next
        val next:CardView = view.findViewById(R.id.next)
        val back:ImageView = view.findViewById(R.id.back)

        next.setOnClickListener {
            toNext(firstName.text.toString(), lastName.text.toString())
        }

        back.setOnClickListener {
            view.findNavController().navigate(R.id.action_profileSetupFragment_to_signUpFragment)
        }


    }

    private fun toShowAnError(text:String){
        error.text = text
        error.visibility = View.VISIBLE
    }

    private fun toNext(first:String, last:String){
        if(first.length >= 3 && last.length >= 3 && image_profile.tag != "null"){

            //setup transfer data to next fragment
            val bundle = Bundle()
            bundle.putString("email", arguments?.get("email").toString())
            bundle.putString("password", arguments?.get("password").toString())
            bundle.putString("first_name",first)
            bundle.putString("last_name",last)
            bundle.putString("image_src", image_profile.tag.toString())

            requireView().findNavController().navigate(R.id.action_profileSetupFragment_to_subFragment, bundle)
        }else{
            if(first.length < 3 && last.length < 3) toShowAnError("short name")

            if(image_profile.tag == "null") toShowAnError("didn't select the picture")

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val url = data?.getData()

        val contentResolver = requireActivity().contentResolver

        val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION
// Check for the freshest data.

        if (url == null) return

        contentResolver.takePersistableUriPermission(url!!, takeFlags)
        image_profile.visibility = View.VISIBLE
        image_profile.setImageURI(url)
        image_profile.tag = url

    }

}