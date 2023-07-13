package com.example.testapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.testapp.databinding.ActivityCameraBinding
import com.example.testapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val qrbtn = v.findViewById<Button>(R.id.qr_scan)
        val obj_List = v.findViewById<Button>(R.id.objList)
        val reg_btn = v.findViewById<Button>(R.id.registerObj)
        val excel_btn = v.findViewById<Button>(R.id.excelBtn)


        qrbtn.setOnClickListener {
            val intent = Intent(requireContext(), LendObject::class.java)
            startActivity(intent)
        }
        obj_List.setOnClickListener { 
            val intent = Intent(requireContext(), ListActivity::class.java)
            startActivity(intent)
        }

        reg_btn.setOnClickListener {
            // 알림 메시지에서 1. qr생성을 원하는지 2. 카메라로 사진을 찍고 등록하기를 원하는지.


            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
            }
        excel_btn.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/spreadsheets/d/1n-3vWNcAM1Bcy2DZhl0Urs_wxFdfVrPe7LXdwGRM_4U/edit?usp=sharing"))

                startActivity(intent)

            }




        return v

    }
}