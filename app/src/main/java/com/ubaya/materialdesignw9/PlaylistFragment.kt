package com.ubaya.materialdesignw9

import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.privacysandbox.tools.core.model.Method
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.materialdesignw9.databinding.FragmentPlaylistBinding
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlaylistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaylistFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var playlists:ArrayList<Playlist> = ArrayList()
    private lateinit var binding: FragmentPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/music/get_playlist.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("apiresult", it)
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        q.add(stringRequest)

        Response.Listener<String> {
            Log.d("apiresult", it)
            val obj = JSONObject(it)
            if(obj.getString("result") == "OK") {
                val data = obj.getJSONArray("data")
                val sType = object : TypeToken<List<Playlist>>() { }.type
                playlists = Gson().fromJson(data.toString(), sType) as
                        ArrayList<Playlist>
                updateList()
                Log.d("cekisiarray", playlists.toString())

            }
//            if(obj.getString("result") == "OK") {
//                val data = obj.getJSONArray("data")
//
//                for(i in 0 until data.length()) {
//                    val playObj = data.getJSONObject(i)
//                    val playlist = Playlist(
//                        playObj.getInt("id"),
//                        playObj.getString("title"),
//                        playObj.getString("subtitle"),
//                        playObj.getString("description"),
//                        playObj.getString("image_url"),
//                        playObj.getInt("num_likes")
//                    )
//                    playlists.add(playlist)
//                }
//
//                Log.d("cekisiarray", playlists.toString())
//            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlaylistBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun updateList() {
        val lm = LinearLayoutManager(activity)
        with(binding.playlistRecView) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = PlaylistAdapter(playlists)
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlaylistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlaylistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}