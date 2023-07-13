package com.example.testapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.testapp.databinding.ActivityLendObjectBinding
import com.example.testapp.databinding.ActivityMainBinding

var flag : Boolean = true
var flagS : Boolean = false
var flagString : Boolean = true

class LendObject : AppCompatActivity() {
    private lateinit var binding: ActivityLendObjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lend_object)


        binding = ActivityLendObjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var qrCodeScanActivity = QRCodeScan(this)
        val profile_btn: Button = findViewById<Button>(R.id.qr_btn)
        val qr_dataText: TextView = findViewById<TextView>(R.id.qr_data)

        /** Click */
        binding.qrBtn.setOnClickListener {
            qrCodeScanActivity.startQRScan()
            if (flagS == true)
            {
                flagS = false
                if (flag == true) {
                    val builder = AlertDialog.Builder(this )
                    builder.setTitle("안내 메시지")
                        .setMessage("물품을 대여하시겠습니까?")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog, id ->
                                binding.qrData.text = "대여 완료"
                                flag = false
                            })
                        .setNegativeButton("취소",
                            DialogInterface.OnClickListener { dialog, id ->

                            })
                    // 다이얼로그를 띄워주기
                    builder.show()
                }
                if (flag == false) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("안내 메시지")
                        .setMessage("물품을 반납하시겠습니까?")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog, id ->
                                binding.qrData.text = "반납 완료"
                                flag = true
                            })
                        .setNegativeButton("취소",
                            DialogInterface.OnClickListener { dialog, id ->

                            })
                    // 다이얼로그를 띄워주기
                    builder.show()
                }
            }

        }
    }
}
