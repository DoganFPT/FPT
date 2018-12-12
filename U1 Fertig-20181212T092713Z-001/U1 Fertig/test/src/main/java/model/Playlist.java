package model;

import interfaces.IPlaylist;
import interfaces.ISong;
import javafx.collections.ModifiableObservableListBase;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Playlist extends ModifiableObservableListBase<ISong> implements IPlaylist, Serializable {
    private ArrayList<ISong> songList = new ArrayList<>();


    public ArrayList<ISong> getSongList() {
        return songList;
    }

    @Override
    public boolean addSong(ISong s) throws RemoteException {
        if (songList.contains(s)) {
            return false;
        } else {
            songList.add(s);
            return true;
        }
    }

    @Override
    public boolean deleteSong(ISong s) throws RemoteException {
        if (songList.contains(s)){
            songList.remove(s);
            return true;
        }else return false;
    }

    @Override
    public boolean deleteSongByID(long id) throws RemoteException {
        if(findSongByID(id) != null){
            songList.remove(findSongByID(id));
            return true;
        }else return false;
    }

    @Override
    public void setList(ArrayList<ISong> s) throws RemoteException {
        this.songList=s;
    }

    @Override
    public ArrayList<ISong> getList() throws RemoteException {
        return songList;
    }

    @Override
    public void clearPlaylist() throws RemoteException {
        this.songList.clear();
    }

    @Override
    public int sizeOfPlaylist() throws RemoteException {
        return songList.size();
    }

    @Override
    public ISong findSongByPath(String name) throws RemoteException {
        ISong s = null;
        for (int i = 0; i <= songList.size(); i++) {
            if (songList.get(i).getPath().equals(name)) {
                s = songList.get(i);
                break;
            }
        }
        return s;
    }

    @Override
    public ISong findSongByID(long id) throws RemoteException {
        ISong s = null;
        for(int i = 0; i<= songList.size(); i++){
            if(songList.get(i).getId() == id){
                s = songList.get(i);
                break;
            }
        }
        return s;
    }

    @Override
    public ISong get(int index) {
        return songList.get(index);
    }

    @Override
    public int size() {
        return songList.size();
    }

    @Override
    protected void doAdd(int index, ISong element) {
        songList.add(index,element);
    }

    @Override
    protected ISong doSet(int index, ISong element) {
        return songList.set(index,element);
    }

    @Override
    protected ISong doRemove(int index) {
        if (songList.get(index) != null){
            ISong s = songList.get(index);
            songList.remove(index);
            return s;
        }else return null;
    }
}
