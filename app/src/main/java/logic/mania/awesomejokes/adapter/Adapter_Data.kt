package logic.mania.awesomejokes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sub_categories.view.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.activity.Activity_Data
import org.json.JSONArray

class Adapter_Data (val jsonArray2: JSONArray, val context: Context) : RecyclerView.Adapter<ViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        return ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_sub_categories, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        holder?.sub_category_name?.text = jsonArray2.getJSONObject(position).getString("joke")


        holder?.sub_category_name.setOnClickListener {
          /*  val id:String =jsonArray2.getJSONObject(position).getString("id")
            val intent = Intent(context, Activity_Data::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("data_id", id)
            context.startActivity(intent)*/
        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return jsonArray2.length()
    }



}

class ViewHolder1 (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val sub_category_name = view.sub_category_name
}

