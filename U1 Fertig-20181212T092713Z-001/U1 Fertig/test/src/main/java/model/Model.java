package model;

public class Model {
    private Playlist playlist= new Playlist();
    private Playlist library = new Playlist();

    public Playlist getLibrary() {
        return library;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
