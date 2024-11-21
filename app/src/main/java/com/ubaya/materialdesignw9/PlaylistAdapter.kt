package com.ubaya.materialdesignw9

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import com.ubaya.materialdesignw9.databinding.CardPlaylistBinding

class PlaylistAdapter(val playlists:ArrayList<Playlist>):
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    class PlaylistViewHolder(val binding: CardPlaylistBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = CardPlaylistBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaylistViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val url = playlists[position].image_url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imgPlaylist)

        with(holder.binding) {
            txtTitle.text = playlists[position].title
            txtSubtitle.text = playlists[position].subtitle
            txtDescription.text = playlists[position].description
//            btnLikes.text = playlists[position].num_likes.toString()
            btnLikes.text = "${playlists[position].num_likes} LIKES"

            btnLikes.setOnClickListener {
                val queue = Volley.newRequestQueue(holder.itemView.context)
                val url = "http://10.0.2.2/music/set_likes.php"
                val stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                        // make sure to change num_likes to var in Playlist data class
                        playlists[position].num_likes++
                        var newlikes = playlists[holder.adapterPosition].num_likes
                        btnLikes.text = "$newlikes LIKES"
                    }
                    ,
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    }
                ) {
                        override fun getParams() = hashMapOf(
                            "id" to playlists[holder.adapterPosition].id.toString()
                        )
                }
                queue.add(stringRequest)
            }


        }





    }


}