package logic.mania.awesomejokes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data.view.*

import logic.mania.awesomejokes.R
import org.json.JSONArray


class Adapter_Data(val jsonArray2: JSONArray, val context: Context) :
    RecyclerView.Adapter<ViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        return ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        holder?.data_name?.text = jsonArray2.getJSONObject(position).getString("joke")

        //whtsapp share
        holder?.share_whtsapp.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                id + "\n\n\n\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.whatsapp")
            //    sendIntent.setPackage("com.whatsapp.w4b")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }

        //whtsapp businessshare
        holder?.share_whtsappbusiness.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                id + "\n\n\n\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
            //   sendIntent.setPackage("com.whatsapp")
            sendIntent.setPackage("com.whatsapp.w4b")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }

        //fb share
        holder?.share_facebook.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                id + "\n\n\n\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.facebook.katana")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }

        //instgram share
        holder?.share_instagram.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                id + "\n\n\n\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.instagram.android")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }

        //twitter share
        holder?.share_twitter.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,id + "\n\n\n\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.twitter.android")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }


        //others share
        holder?.share_others.setOnClickListener {
            val id: String = jsonArray2.getJSONObject(position).getString("joke")

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,id + "\n" + "Download this Awesome App and share Jokes, Shayri, Motivational Quotes ,Thoughts, Intresting Stories and so on with your friends and family. Download Now\n " + "https://play.google.com/store/apps/details?id=" + context.packageName
            )
            sendIntent.type = "text/plain"
           //    sendIntent.setPackage("*/*")
            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(sendIntent)
            }
        }

        setScaleAnimation(holder.itemView)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return jsonArray2.length()
    }

}

class ViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val data_name = view.data_name
    val share_whtsapp = view.share_whtsapp
    val share_whtsappbusiness = view.share_whtsappbusiness
    val share_facebook = view.share_facebook
    val share_instagram = view.share_instagram
    val share_twitter = view.share_twitter
    val share_others = view.share_others
}

private fun setScaleAnimation(view: View) {
    val anim = ScaleAnimation(
        0.0f,
        1.0f,
        0.0f,
        1.0f,
        Animation.RELATIVE_TO_PARENT,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    val FADE_DURATION=1000
    anim.duration = FADE_DURATION.toLong()
    view.startAnimation(anim)
}