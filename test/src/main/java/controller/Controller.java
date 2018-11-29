package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import model.Song;
import view.View;
import java.rmi.RemoteException;
import java.util.List;
import java.io.File;

public class Controller {
    private List<File> fileList;
    private Song songInfo;
    private MediaPlayer mp;
    private Media m;
    private Model model;
    private View view;
    private int playingTrackPosition;
    private ObservableList<Song> songsInLibrary = FXCollections.observableArrayList();
    private ObservableList<Song> songsInPlaylist = FXCollections.observableArrayList();


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
        view.addToPlaylist(this::addToPlaylist);
        view.addAll(this::addAll);
        view.deleteFromPlaylist(this::deleteFromPlaylist);
    }

    public void next(){
        try {
            pause();
            if (playingTrackPosition + 1 == songsInPlaylist.size()) {
                playingTrackPosition = 0;
            } else {
                playingTrackPosition++;
            }
            view.getPlaylist().getSelectionModel().select(playingTrackPosition);
            mp = new MediaPlayer(new Media(new File(songsInPlaylist.get(playingTrackPosition).getPath()).toURI().toString()));
            getMetadata();
            mp.play();
            view.getPlaylist().setItems(songsInPlaylist);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        try {
            mp.pause();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void commit(){
        try {
            model.getPlaylist().get(playingTrackPosition).setAlbum(view.getAlbum().getText());
            model.getPlaylist().get(playingTrackPosition).setTitle(view.getTitle().getText());
            model.getPlaylist().get(playingTrackPosition).setInterpret(view.getInterpret().getText());
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public void play(){
        try {
            if (mp != null) {
                if (playingTrackPosition != view.getPlaylist().getSelectionModel().getSelectedIndex()) {
                    mp.stop();
                    m = new Media(new File(view.getPlaylist().getSelectionModel().getSelectedItem().getPath()).toURI().toString());
                    mp = new MediaPlayer(m);
                }
            } else {
                m = new Media(new File(view.getPlaylist().getSelectionModel().getSelectedItem().getPath()).toURI().toString());
                mp = new MediaPlayer(m);
            }
            playingTrackPosition = view.getPlaylist().getSelectionModel().getSelectedIndex();
            getMetadata();
            mp.play();
            mp.setOnEndOfMedia(()->next());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void getMetadata(){
        try {
            view.getInterpret().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getInterpret());
            view.getAlbum().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getAlbum());
            view.getTitle().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getTitle());
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    public void load(){
        try {
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            fileChooser.setTitle("Song(s) aussuchen");
            fileList = (fileChooser.showOpenMultipleDialog(stage));

            for (int i = 0; i < fileList.size(); i++) {
                Song song = new Song();
                song.setPath(fileList.get(i).getAbsolutePath());
                song.setTitle(fileList.get(i).getName());
                model.getLibrary().add(song);
                model.getLibrary().getSongList().add(song);
                songsInLibrary.add(song);
            }
            this.view.getLibrary().setItems(songsInLibrary);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void addToPlaylist() throws RemoteException {
        if(view.getLibrary().getSelectionModel().getSelectedItem()!=null) {
            model.getPlaylist().addSong(view.getLibrary().getSelectionModel().getSelectedItem());
            songsInPlaylist.add(view.getLibrary().getSelectionModel().getSelectedItem());
            this.view.getPlaylist().setItems(songsInPlaylist);
        }
    }

    public void addAll() throws RemoteException {
        for(int i=0; i<model.getLibrary().getSongList().size(); i=i+2){
            model.getPlaylist().addSong(model.getLibrary().getSongList().get(i));
            songsInPlaylist.add((model.Song)model.getLibrary().getSongList().get(i));
        }
        this.view.getPlaylist().setItems(songsInPlaylist);
    }

    public void deleteFromPlaylist() throws RemoteException {
        if(view.getPlaylist().getSelectionModel().getSelectedItem()!=null) {
            songsInPlaylist.remove(view.getPlaylist().getSelectionModel().getSelectedItem());
            model.getPlaylist().deleteSong(view.getPlaylist().getSelectionModel().getSelectedItem());
            view.getPlaylist().setItems(songsInPlaylist);
            try {
                view.getPlaylist().getSelectionModel().select(playingTrackPosition);
                view.getPlaylist().getSelectionModel().select(0);
                playingTrackPosition = 0;
                mp.stop();
                m = new Media(new File(view.getPlaylist().getSelectionModel().getSelectedItem().getPath()).toURI().toString());
                mp = new MediaPlayer(m);
                play();
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
    }
}
