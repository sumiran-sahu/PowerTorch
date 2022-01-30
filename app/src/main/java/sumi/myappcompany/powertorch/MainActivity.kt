package sumi.myappcompany.powertorch

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var camera: CameraManager
    private lateinit var powerbtnon: ImageButton
    private lateinit var powerbtnoff: ImageButton
    var isflash = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setViewId()

        powerbtnon.setOnClickListener(View.OnClickListener {
            flashLightOn(it)

        })
        powerbtnoff.setOnClickListener(View.OnClickListener {
            flashLightOFF(it)

        })

    }

    private fun flashLightOFF(v: View) {
       if (isflash) {
           val cameralistid = camera.cameraIdList[0]
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               camera.setTorchMode(cameralistid, false)
               isflash = false
               textMessage("Flash light is off ", this)
           }

       }
    }

    private fun flashLightOn(v: View) {
  if (!isflash) {
      val cameralistid = camera.cameraIdList[0]
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          camera.setTorchMode(cameralistid, true)
          isflash = true
          textMessage("Flash light is on ", this)
      }
  }
    }

    private fun textMessage(s: String, c: Context) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show()


    }

    private fun setViewId() {
        powerbtnon = findViewById(R.id.poweron)
        powerbtnoff = findViewById(R.id.poweroff)
        camera = getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }
}