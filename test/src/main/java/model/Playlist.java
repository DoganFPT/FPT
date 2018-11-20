package model;

import interfaces.IPlaylist;
import interfaces.ISong;
import javafx.collections.ModifiableObservableListBase;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Playlist extends ModifiableObservableListBase<ISong> implements IPlaylist {
    private ArrayList<ISong> songList = new ArrayList<>();

    @Override
    public boolean addSong(ISong s) throws RemoteException {
        return songList.add(s);
    }

    @Override
    public boolean deleteSong(ISong s) throws RemoteException {
        return remove(s);
    }

    @Override
    public boolean deleteSongByID(long id) throws RemoteException {
        ISong deleteByID = null;
        for(ISong song :this){
            if(song.getId()==id){
                deleteByID = song;
            }else{
                return false;
            }
        }
        return remove(deleteByID);
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
        for(ISong temp : songList){
            if(temp.getPath().equals(name)){
                return temp;
            }
        }
        return null;
    }

    @Override
    public ISong findSongByID(long id) throws RemoteException {
        for(ISong temp : songList){
            if(temp.getId()==id ){
                return temp;
            }
        }
        return null;
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
        return songList.remove(index);
    }
}
