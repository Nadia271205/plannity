package com.proyek.planity.homeNav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.proyek.planity.LoginActivity
import com.proyek.planity.R

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //menampilkan halaman profile
        super.onViewCreated(view, savedInstanceState)

        val imageViewlogout = view.findViewById<ImageView>(R.id.imageViewlogout)

        imageViewlogout.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent) //pindah ke halaman login
            requireActivity().finish() //menutup activity saat logout
        }
    }
}