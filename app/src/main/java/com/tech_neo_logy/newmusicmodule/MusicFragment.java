package com.tech_neo_logy.newmusicmodule;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tech_neo_logy.newmusicmodule.musicService.MusicPlayerService;
import com.tech_neo_logy.newmusicmodule.network.VolleySingleton;

import java.io.UnsupportedEncodingException;



public class MusicFragment extends Fragment {

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
        musicUrl = "https://s3-ap-northeast-1.amazonaws.com/audiopraf2/02+-+3G+-+Khalbali+%5BMP3Khan%5D.mp3";
        playPauseButton = (ImageButton) musicFragmentView.findViewById(R.id.playPauseButton);
        nextTrackButton = (ImageButton) musicFragmentView.findViewById(R.id.nextTrackButton);
        if(boolMusicPlaying)
            playPauseButton.setBackgroundResource(R.mipmap.pause);
        else
            playPauseButton.setBackgroundResource(R.mipmap.play);
        Toast.makeText(getContext(), "bool value "+boolMusicPlaying, Toast.LENGTH_SHORT).show();
        setlistener();
        return musicFragmentView;

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
