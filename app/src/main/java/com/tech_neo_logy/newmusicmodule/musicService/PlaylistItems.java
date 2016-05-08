package com.tech_neo_logy.newmusicmodule.musicService;

/**
 * Created by Prafull on 06-May-16.
 */
public class PlaylistItems {
    public String title,image;
    public String artist;
    public String album;


    public String getTitle(){

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist(){


        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum(){


        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
