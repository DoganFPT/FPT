package model;

import javafx.collections.ModifiableObservableListBase;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Playlist extends ModifiableObservableListBase<model.Song> implements interfaces.IPlaylist {

    private ArrayList<model.Song> songList = new ArrayList<>();

    public void addSong(Song e) throws RemoteException {
        songList.add(e);
    }

    public boolean deleteSong(Song e)throws RemoteException{
        return remove(e);
    }
    public boolean deleteSongByID(Song e) throws RemoteException{
        //TODO
    }
    public void setList(ArrayList<Song> e) throws RemoteException{
        this.songList=e;
    }
    public ArrayList<Song> getList()throws RemoteException{
        return songList;
    }

    public void clearPlaylist() throws RemoteException{
        this.songList.clear();
    }
    public int sizeOfPlaylist()throws RemoteException{
        return songList.size();
    }
    public Song findSongByPath(String name) throws RemoteException{
        //TODO
    }

    public Song findSongByID(long id)throws RemoteException{
        //TODO
    }

    public int size(){
        return songList.size();
    }

    public Song get(int index) {
        return songList.get(index);
    }

    protected void doAdd(int index, Song element) {
        songList.add(index,element);
    }

    protected Song doSet(int index, Song element) {
        return songList.set(index,element);
    }

    protected Song doRemove(int index) {
        return songList.remove(index);
    }

}
