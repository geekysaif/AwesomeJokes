package logic.mania.awesomejokes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import logic.mania.awesomejokes.R

class Activity_Splash : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=2000 // 5 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__splash)


         Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this,Activity_Categories::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

    }
}
