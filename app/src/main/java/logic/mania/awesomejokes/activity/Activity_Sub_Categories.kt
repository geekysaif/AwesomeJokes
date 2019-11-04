package logic.mania.awesomejokes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity__sub__categories.*
import kotlinx.android.synthetic.main.activity_categories.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.adapter.Adapter_Categories
import logic.mania.awesomejokes.adapter.Adapter_SubCategories
import org.json.JSONArray
import org.json.JSONObject

class Activity_Sub_Categories : AppCompatActivity() {

    private var no_sub_category_list: TextView? = null

    var car = "Toyota Matrix"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__sub__categories)

        var bundle: Bundle? = intent.extras
        car = bundle?.getString("subcat_id").toString() // 1


        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.sub_category_list)
        no_sub_category_list = findViewById<TextView>(R.id.no_sub_category_list)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData()
    }


    // function for network call
    fun loadData() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gofugly.in/api/sub_categories/"+car

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj1 = JSONObject(strResp)
                val jsonArray1: JSONArray = jsonObj1.getJSONArray("result")

                sub_category_list.adapter = Adapter_SubCategories(jsonArray1, applicationContext)
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
}
