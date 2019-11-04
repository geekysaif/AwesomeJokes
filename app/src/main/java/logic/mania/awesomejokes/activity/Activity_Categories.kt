package logic.mania.awesomejokes.activity


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_categories.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.adapter.Adapter_Categories
import org.json.JSONArray
import org.json.JSONObject


class Activity_Categories : AppCompatActivity() {

    private var no_category_list: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.category_list)
        no_category_list = findViewById<TextView>(R.id.no_category_list)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        setupPermissions()
        loadData()

    }

    // function for network call
    fun loadData() {
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

}
