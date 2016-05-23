package com.tech_neo_logy.newmusicmodule;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.tech_neo_logy.newmusicmodule.musicService.MusicPlayerService;
import com.tech_neo_logy.newmusicmodule.musicService.PlaylistItemAdapter;
import com.tech_neo_logy.newmusicmodule.musicService.PlaylistItems;
import com.tech_neo_logy.newmusicmodule.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MusicFragment extends Fragment {

    public  boolean first_exec = true;
    private OnFragmentInteractionListener mListener;
    private int loader;
    private View musicFragmentView;
    private ImageView albumArt;
    private String image_url;
    private ImageLoader imageLoader;
    private ImageButton playPauseButton;
    private ImageButton nextTrackButton;
    Intent serviceIntent;
    private static boolean boolMusicPlaying = false;
    private String musicUrl;
    private ProgressDialog progressDialog = null;
    private List<PlaylistItems> playlistItemsArrayList = new ArrayList<PlaylistItems>();
    private ListView playlistListView;
    private PlaylistItemAdapter adapter;
    private static final String jsonUrl = "http://api.androidhive.info/json/movies.json";
    private DrawerLayout drawerLayout;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            serviceIntent = new Intent(getActivity(), MusicPlayerService.class);
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MyApplication.getAppcontext(), e.getClass().getName()+" "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        image_url = "http://square.github.io/picasso/static/sample.png";
        musicFragmentView = inflater.inflate(R.layout.fragment_music,container,false);
        albumArt = (ImageView)musicFragmentView.findViewById(R.id.music_albumArt_netView);
        loadImageToView(image_url,albumArt);
        musicUrl = "https://s3-ap-northeast-1.amazonaws.com/audiopraf2/Raavan+-+Behne+De.mp3";
        playPauseButton = (ImageButton) musicFragmentView.findViewById(R.id.playPauseButton);
        nextTrackButton = (ImageButton) musicFragmentView.findViewById(R.id.nextTrackButton);

        if(boolMusicPlaying)
            playPauseButton.setBackgroundResource(R.mipmap.pause);
        else
            playPauseButton.setBackgroundResource(R.mipmap.play);
            //Toast.makeText(getContext(), "bool value "+boolMusicPlaying, Toast.LENGTH_SHORT).show();

        drawerLayout = (DrawerLayout)musicFragmentView.findViewById(R.id.drawer_in_music) ;
        playlistListView = (ListView)drawerLayout.findViewById(R.id.listView_playlist);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading...");
        progressDialog.show();

        adapter = new PlaylistItemAdapter(getActivity(),playlistItemsArrayList);

        //create jsonvolley request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://connect-practicingbee.rhcloud.com/music_fetcher.php?mood="+Navigation.mood, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                //parsing Json
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        PlaylistItems playlistItem = new PlaylistItems();
                        playlistItem.setTitle(jsonObject.getString("song_name"));
                        playlistItem.setAlbum(jsonObject.getString("album"));
                        playlistItem.setUrl(jsonObject.getString("url"));
                        if (first_exec){
                            musicUrl = jsonObject.getString("url");
                            first_exec = false;
                        }
                       // playlistItem.setArtist(jsonObject.getString("releaseYear"));
                       // playlistItem.setImage(jsonObject.getString("image"));
                        // Toast.makeText(getContext(), ""+jsonObject.getString("song_name")+" "+jsonObject.getString("album"), Toast.LENGTH_SHORT).show();
                        //add to array
                        playlistItemsArrayList.add(playlistItem);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        playlistListView.setAdapter(adapter);

        VolleySingleton.getVolleyInstance().addToRequestQueue(jsonArrayRequest);
        setlistener();

        return musicFragmentView;

    }

    public void hideDialog(){
        if (progressDialog!=null);
        progressDialog.dismiss();
        progressDialog=null;
    }

    private void loadImageToView(String url, final ImageView holder)
    {
        imageLoader = VolleySingleton.getVolleyInstance().getImageLoader();
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    holder.setImageBitmap(response.getBitmap());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.setBackground(null);
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(new String(), "Image Load Error: " + error.getMessage());
            }
        });
    }

    void setlistener(){
            playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPauseButtonClick();
                }
            });

            playlistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Toast.makeText(getContext(), ""+((PlaylistItems)parent.getItemAtPosition(position)).getUrl(), Toast.LENGTH_SHORT).show();
                   // if (boolMusicPlaying)
                    stopMusic();
                    musicUrl = ((PlaylistItems)parent.getItemAtPosition(position)).getUrl();
                    streamMusic();
                    playPauseButton.setBackgroundResource(R.mipmap.pause);
                    boolMusicPlaying = true;

                }
            });

    }

    void playPauseButtonClick(){
        if(!boolMusicPlaying){
            playPauseButton.setBackgroundResource(R.mipmap.pause);
            streamMusic();
            boolMusicPlaying = true;

        }
        else{
            playPauseButton.setBackgroundResource(R.mipmap.play);
            stopMusic();
            boolMusicPlaying = false;
        }
        Toast.makeText(getContext(), "bool value "+boolMusicPlaying, Toast.LENGTH_SHORT).show();
    }
    void streamMusic(){
        //code to start music
    serviceIntent.putExtra("musicUrl",musicUrl);
        try{
            getActivity().startService(serviceIntent);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MyApplication.getAppcontext(), e.getClass().getName()+" "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        boolMusicPlaying = true;
    }

    void stopMusic(){
    //code to stop music
        try{
            getActivity().stopService(serviceIntent);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MyApplication.getAppcontext(), e.getClass().getName()+" "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        boolMusicPlaying = false;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
