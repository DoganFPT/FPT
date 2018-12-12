package controller;

import javafx.collections.FXCollections;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.IDGenerator;
import model.IDOverFlowException;
import model.Model;
import model.Song;
import view.View;
import java.rmi.RemoteException;
import java.util.List;
import java.io.File;
import java.util.Objects;

public class Controller {
    private List<File> fileList;
    private Song songInfo;
    private MediaPlayer mp;
    private Media m;
    private Model model;
    private View view;
    private int playingTrackPosition;
    private IDGenerator idGenerator = new IDGenerator();


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
        view.songImport(this::importSongs);
        view.addToPlaylist(this::addToPlaylist);
        view.addAll(this::addAll);
        view.deleteFromPlaylist(this::deleteFromPlaylist);
    }

    public void next(){
        try {
            pause();
            if (playingTrackPosition + 1 == model.getPlaylist().size()) {
                playingTrackPosition = 0;
            } else {
                playingTrackPosition++;
            }
            view.getPlaylist().getSelectionModel().select(playingTrackPosition);
            mp = new MediaPlayer(new Media(new File(model.getPlaylist().get(playingTrackPosition).getPath()).toURI().toString()));
            getMetadata();
            mp.play();
            mp.setOnEndOfMedia(()->next());
            view.getPlaylist().setItems(FXCollections.observableList(model.getPlaylist().getSongList()));
        }catch (NullPointerException e){e.printStackTrace();}
    }

    public void pause(){
        try {
            mp.pause();
        }catch (NullPointerException e){e.printStackTrace();}
    }

    public void commit(){
        try {
            model.getPlaylist().get(playingTrackPosition).setAlbum(view.getAlbum().getText());
            model.getPlaylist().get(playingTrackPosition).setTitle(view.getTitle().getText());
            model.getPlaylist().get(playingTrackPosition).setInterpret(view.getInterpret().getText());
        }catch (IndexOutOfBoundsException e){e.printStackTrace();}
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
                mp.setOnEndOfMedia(()->next());
            }
            playingTrackPosition = view.getPlaylist().getSelectionModel().getSelectedIndex();
            getMetadata();
            mp.play();
        }catch (NullPointerException e){e.printStackTrace();}
    }

    public void getMetadata(){
        try {
            view.getInterpret().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getInterpret());
            view.getAlbum().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getAlbum());
            view.getTitle().setText(view.getPlaylist().getSelectionModel().getSelectedItem().getTitle());
        }catch(NullPointerException e){e.printStackTrace();}
    }

    public void importSongs(){
        try {
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            fileChooser.setTitle("Song(s) aussuchen");
            fileList = (fileChooser.showOpenMultipleDialog(stage));

            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).getName().endsWith(".mp3")) {
                    Song song = null;
                    try {
                        song = new Song(IDGenerator.getNextID(), fileList.get(i).getAbsolutePath(), fileList.get(i).getName());
                    } catch (IDOverFlowException e) {
                        e.printStackTrace();
                    }
                    try {
                        model.getLibrary().addSong(song);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            this.view.getLibrary().setItems(FXCollections.observableList(model.getLibrary().getSongList()));
        }catch (NullPointerException e){e.printStackTrace();}
    }

    public void addToPlaylist() throws RemoteException {
        if(view.getLibrary().getSelectionModel().getSelectedItem()!=null) {
            model.getPlaylist().addSong(view.getLibrary().getSelectionModel().getSelectedItem());
            this.view.getPlaylist().setItems(FXCollections.observableList(model.getPlaylist().getSongList()));
        }
    }

    public void addAll() throws RemoteException {
        for(int i=0; i<model.getLibrary().getSongList().size(); i++){
            model.getPlaylist().addSong(model.getLibrary().getSongList().get(i));
        }
        this.view.getPlaylist().setItems(FXCollections.observableList(model.getPlaylist().getSongList()));
    }

    public void deleteFromPlaylist() throws RemoteException {
        if(view.getPlaylist().getSelectionModel().getSelectedItem()!=null) {
            model.getPlaylist().deleteSong(view.getPlaylist().getSelectionModel().getSelectedItem());
            view.getPlaylist().setItems(FXCollections.observableList(model.getPlaylist().getSongList()));
            try {
                view.getPlaylist().getSelectionModel().select(playingTrackPosition);
                view.getPlaylist().getSelectionModel().select(0);
                playingTrackPosition = 0;
                if(mp!=null && mp.getStatus() == MediaPlayer.Status.PLAYING) {
                    mp.stop();
                }
                m = new Media(new File(view.getPlaylist().getSelectionModel().getSelectedItem().getPath()).toURI().toString());
                mp = new MediaPlayer(m);
                play();
            }catch(NullPointerException e){e.printStackTrace();}
        }
    }
}
