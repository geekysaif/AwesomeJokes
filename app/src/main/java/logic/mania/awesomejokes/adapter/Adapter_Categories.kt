package logic.mania.awesomejokes.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_categories.view.*
import logic.mania.awesomejokes.R
import logic.mania.awesomejokes.activity.Activity_Sub_Categories
import org.json.JSONArray


  class Adapter_Categories(val jsonArray: JSONArray, val context: Context) : RecyclerView.Adapter<ViewHolder>() {



      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false))
      }

      override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder?.category_name?.text = jsonArray.getJSONObject(position).getString("name")


          holder?.category_name.setOnClickListener {
              val id:String =jsonArray.getJSONObject(position).getString("id")
              val intent = Intent(context, Activity_Sub_Categories::class.java)
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
              intent.putExtra("subcat_id", id)
              context.startActivity(intent)
          }
          setScaleAnimation(holder.itemView)
      }

      // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return jsonArray.length()
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val category_name = view.category_name

}

private fun setScaleAnimation(view: View) {
    val anim = ScaleAnimation(
        0.0f,
        1.0f,
        0.0f,
        1.0f,
        Animation.START_ON_FIRST_FRAME,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    val FADE_DURATION=1000
    anim.duration = FADE_DURATION.toLong()
    view.startAnimation(anim)
}