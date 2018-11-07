package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Model;
import view.View;

public class controller {
    private Media m ;
    private MediaPlayer mp;
    private Model model;
    private View view;

    public void link( View view,Model model){
        this.view=view;
        this.model=model;
        this.model.getLibrary().this.model.getPlaylist();
        addEventhandler();
    }

    private void addEventhandler(){
        view.

    }

}
