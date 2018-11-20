package controller;


import interfaces.ISong;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import model.Song;
import view.View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.ListView;
import java.io.File;

public class Controller {
    ArrayList<String> filePath;
    String file;
    private List<File> fileList;
    private Song songInfo;
    private MediaPlayer mp;
    private Media m;
    private Model model;
    private View view;
    private int currentPosition=0;


    public void link(View view, Model model) {
        this.view = view;
        this.model = model;
        addEventhandler();
    }
    public void addEventhandler(){
        view.next(this::next);
        view.pause(this::pause);
        view.play(this::play);
        view.commit(this::commit);
        view.load(this::load);
    }

    public void next(){
        if(++currentPosition >= model.getPlaylist().size()){
            currentPosition=0;
            mp=new MediaPlayer(new Media(new File(model.getPlaylist().get(currentPosition).getPath()).toURI().toString()));
            mp.play();
        }
    }
    public void pause(){
        mp.pause();
    }
    public void play() {
        if (m == null) {
            m = new Media(new File(model.getPlaylist().get(0).getPath()).toURI().toString());
        }
        if (mp == null) {
            mp = new MediaPlayer(m);
        }
    }
    public void commit(){
        songInfo.setInterpret(view.getInterpretText().getText());
        songInfo.setAlbum(view.getAlbumText().getText());
        songInfo.setTitle(view.getTitleText().getText());
        view.getLibrary().refresh();
        view.getPlaylist().refresh();
    }
    public void load(){
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        fileChooser.setTitle("Song(s) aussuchen");
        fileList = (fileChooser.showOpenMultipleDialog(stage));

        for (int i = 0; i < fileList.size(); i++) {
            file=(fileList.get(i).toString());
            file = file.replace("\\", "/");
            file = file.replace(" ", "%20");
            file = "file:///"+file;
            filePath.add(i,file);

        }

    }


}
