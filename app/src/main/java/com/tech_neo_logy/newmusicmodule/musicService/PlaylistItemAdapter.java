package com.tech_neo_logy.newmusicmodule.musicService;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tech_neo_logy.newmusicmodule.R;
import com.tech_neo_logy.newmusicmodule.network.VolleySingleton;

import java.util.List;

/**
 * Created by Prafull on 06-May-16.
 */
public class PlaylistItemAdapter extends BaseAdapter{

    private Context context;
    private ImageLoader imageLoader = VolleySingleton.getVolleyInstance().getImageLoader();
    private LayoutInflater layoutInflater;
    private List<PlaylistItems> playlistItems;

    public PlaylistItemAdapter(Context context,List<PlaylistItems> playlistItems){
        this.context = context;
        this.playlistItems = playlistItems;
    }

    @Override
    public int getCount() {
        return playlistItems.size();
    }

    @Override
    public Object getItem(int position) {
        return playlistItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            convertView = layoutInflater.inflate(R.layout.playlist_custom_layout,parent,false );


            imageLoader = VolleySingleton.getVolleyInstance().getImageLoader();
            NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.playlist_albumart);
            TextView song_title_tv = (TextView) convertView.findViewById(R.id.song_title_tv);
            TextView song_album_tv = (TextView) convertView.findViewById(R.id.song_album_tv);
            TextView song_artist_tv = (TextView) convertView.findViewById(R.id.song_artist_tv);
            //getting data for row

           PlaylistItems items =  playlistItems.get(position);
            //networkImageView.setImageUrl(items.getImage(),imageLoader);
            song_title_tv.setText(items.getTitle());
           // song_artist_tv.setText(items.getArtist());
            song_album_tv.setText(items.getAlbum());


        return convertView;
    }
}
