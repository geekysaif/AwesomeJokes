package logic.mania.awesomejokes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_categories.view.*
import kotlinx.android.synthetic.main.item_data.view.*
import kotlinx.android.synthetic.main.item_sub_categories.view.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.activity.Activity_Data
import logic.mania.awesomejokes.activity.Activity_Sub_Categories

import org.json.JSONArray

class Adapter_SubCategories(val jsonArray1: JSONArray, val context: Context) :
    RecyclerView.Adapter<ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        return ViewHolder2(
            LayoutInflater.from(context).inflate(
                R.layout.item_sub_categories,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder?.sub_category_name?.text = jsonArray1.getJSONObject(position).getString("name")


        holder?.sub_category_name.setOnClickListener {
            val id: String = jsonArray1.getJSONObject(position).getString("id")
            val intent = Intent(context, Activity_Data::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("data_id", id)
            context.startActivity(intent)
        }
        setScaleAnimation(holder.itemView)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return jsonArray1.length()
    }


}

class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val sub_category_name = view.sub_category_name
}
private fun setScaleAnimation(view: View) {
    val anim = ScaleAnimation(
        0.0f,
        1.0f,
        0.0f,
        1.0f,
        Animation.ABSOLUTE,
        0.5f,
        Animation.REVERSE,
        0.5f
    )
    val FADE_DURATION=1000
    anim.duration = FADE_DURATION.toLong()
    view.startAnimation(anim)
}