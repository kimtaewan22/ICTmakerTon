package com.example.testapp

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

data class ObjProfile(var addition: String? = null,
                        var count: String? = null,
                        var name: String? = null,
                        var img: String? = null)

class CameraActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap
    lateinit var  imageView: ImageView
    private val album_btn = 1
    var fbStorage : FirebaseStorage? = null
    var ImnageData: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        //객체 생성.
        imageView = findViewById(R.id.objImg)

        val picBtn: Button = findViewById(R.id.camera_btn)
        val albumBtn: Button = findViewById(R.id.album_btn)
        val nextBtn: Button = findViewById(R.id.nextbt)
        val et_objname: TextView = findViewById(R.id.et_objName)
        val et_objcount: TextView = findViewById(R.id.et_objCount)



        val db = Firebase.firestore


        picBtn.setOnClickListener {
            //사진 촬영.
            val intent : Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            activityResult.launch(intent)

        }

        albumBtn.setOnClickListener {
            val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, album_btn)
        }

//        upload_btn.setOnClickListener {
//            //데이터 베이스에 저장.
//           var name = et_objname.text.toString()
//           var count = et_objcount.text.toString()
//            var img = ""
//            val obInfo = hashMapOf(
//                "name" to name,
//                "count" to count,
//                "img" to img,
//            )
//
//            // firebase에 이미지 업로드
//            ImageUpload(ImnageData)
//
//            db.collection("ObjectData")
//                .add(obInfo)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }
//
//
//        }
        nextBtn.setOnClickListener {
            val intent: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    fun createImageUri(filename:String, mimeType:String):Uri?{
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( resultCode == Activity.RESULT_OK){
            if( requestCode ==  album_btn)
            {
                ImnageData = data?.data


                Toast.makeText(this,ImnageData.toString(), Toast.LENGTH_SHORT ).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    imageView.setImageBitmap(bitmap)
                }
                catch (e:Exception)
                {
                    e.printStackTrace()
                }
            }
        }
    }
    fun returnUri(uri: Uri?):Uri? {
        return uri
    }
    //결과 가져오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode == RESULT_OK && it.data != null){
            // 값 담기
            val extras = it.data!!.extras
            // bitmap으로 타입 변경.
            bitmap = extras?.get("data") as Bitmap
            // 화면에 보여주기
            imageView.setImageBitmap(bitmap)
        }
    }
    fun getImageUri(inContext: Context?, inImage: Bitmap?): Uri? {
        val bytes = ByteArrayOutputStream()
        if (inImage != null) {
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        }
        val path = MediaStore.Images.Media.insertImage(inContext?.getContentResolver(), inImage, "Title" + " - " + Calendar.getInstance().getTime(), null)
        return Uri.parse(path)
    }


    private fun ImageUpload(uri: Uri?) {
        fbStorage = FirebaseStorage.getInstance()
        var timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        var imgFileName = "IMAGE_" + timeStamp + "_.png"
        var storageRef = fbStorage!!.reference?.child("images")?.child(imgFileName)

        storageRef?.putFile(uri!!)?.addOnSuccessListener {
            Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show()
        }
    }


}
