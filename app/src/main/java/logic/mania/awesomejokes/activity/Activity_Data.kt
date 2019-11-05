package logic.mania.awesomejokes.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity__data.*
import kotlinx.android.synthetic.main.activity__sub__categories.*
import kotlinx.android.synthetic.main.activity_categories.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.adapter.Adapter_Data
import logic.mania.awesomejokes.adapter.Adapter_SubCategories
import org.json.JSONArray
import org.json.JSONObject

class Activity_Data : AppCompatActivity() {

    private var no_data_list: TextView? = null
    private var no_network: TextView? = null
    var car = "Toyota Matrix"
    lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__data)


        var bundle: Bundle? = intent.extras
        car = bundle?.getString("data_id").toString() // 1


        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.data_list)
        no_data_list = findViewById<TextView>(R.id.no_data_list)
        no_network = findViewById<TextView>(logic.mania.awesomejokes.R.id.no_network)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (isNetworkAvailable()) {
            loadData()
            MobileAds.initialize(this) {}
            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)

            mAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdFailedToLoad(errorCode: Int) {
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

        } else{
            no_network!!.visibility = View.VISIBLE
            category_list!!.visibility = View.GONE
            val toast = Toast.makeText(applicationContext, "Please Check Your Internet Connection....", Toast.LENGTH_LONG)
            toast.show()
        }
    }


    fun loadData() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gofugly.in/api/content/"+car

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj2 = JSONObject(strResp)
                val jsonArray2: JSONArray = jsonObj2.getJSONArray("result")

                data_list.adapter = Adapter_Data(jsonArray2, applicationContext)
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

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}
