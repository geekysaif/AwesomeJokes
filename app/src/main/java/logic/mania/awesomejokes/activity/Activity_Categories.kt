package logic.mania.awesomejokes.activity


import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.*
import com.google.firebase.iid.FirebaseInstanceId

import kotlinx.android.synthetic.main.activity_categories.*
import logic.mania.awesomejokes.R

import logic.mania.awesomejokes.adapter.Adapter_Categories
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class Activity_Categories : AppCompatActivity() {

    private val TAG = "MyFirebaseToken"
    private var no_category_list: TextView? = null
    private var no_network: TextView? = null
    private var progressBar: ProgressBar? = null

    lateinit var mAdView : AdView
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        initView()
        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.category_list)
        no_category_list = findViewById<TextView>(logic.mania.awesomejokes.R.id.no_category_list)
        no_network = findViewById<TextView>(logic.mania.awesomejokes.R.id.no_network)
        progressBar = findViewById<ProgressBar>(logic.mania.awesomejokes.R.id.progressBar)

        //adding a layoutmanager
       // recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        setupPermissions()

        if (isNetworkAvailable()){

            loadData()

            MobileAds.initialize(this) {}
            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)

            mAdView.adListener = object: AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdFailedToLoad(errorCode : Int) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            }

            mInterstitialAd = InterstitialAd(this)
            mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
            mInterstitialAd.loadAd(AdRequest.Builder().build())
            mInterstitialAd.show()

            mInterstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }
            }

            /* //set when button clicked
               if (mInterstitialAd.isLoaded) {
                   mInterstitialAd.show()
               } else {
                   Log.d("TAG", "The interstitial wasn't loaded yet.")
               }*/
            mInterstitialAd.adListener = object: AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                override fun onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                }
            }

        }
        else{
            no_network!!.visibility = View.VISIBLE
            category_list!!.visibility = View.GONE
            val toast = Toast.makeText(applicationContext, "Please Check Your Internet Connection....", Toast.LENGTH_LONG)
            toast.show()
        }

    }

    fun loadData() {

        progressBar!!.visibility = View.VISIBLE
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gofugly.in/api/categories/"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("result")

                category_list.adapter = Adapter_Categories(jsonArray, applicationContext)

                progressBar!!.visibility = View.GONE
              /*  var str_user: String = ""

                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner.get("name")
                }
                no_category_list!!.text = "response : $str_user "*/
            },
            Response.ErrorListener { no_category_list!!.text = "That didn't work!" })
        queue.add(stringReq)
    }
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("saif", "Permission to record denied")
        }
    }
/**/
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    private fun initView() {
        //This method will use for fetching Token
        Thread(Runnable {
            try {
                Log.i(TAG, FirebaseInstanceId.getInstance().getToken(getString(R.string.SENDER_ID), "FCM"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {

                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,"Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + applicationContext.packageName
                )
                sendIntent.type = "text/plain"
                //    sendIntent.setPackage("*/*")
                if (sendIntent.resolveActivity(applicationContext.getPackageManager()) != null) {
                    sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    applicationContext.startActivity(sendIntent)
                }

                true
            }
            R.id.action_rate ->{

                rateApp()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    fun rateApp() {
        val uri = Uri.parse("market://details?id=$packageName")
        val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(myAppLinkToMarket)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Impossible to find an application for the market", Toast.LENGTH_LONG).show()
        }
    }
}
