package strategies;

import interfaces.IPlaylist;
import interfaces.ISong;
import interfaces.SerializableStrategy;
import main.Main;
import model.Model;
import model.Playlist;
import model.Song;

import java.io.*;

public class BinaryStrategy implements SerializableStrategy {

    Model model = Main.getModel();
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;

    @Override
    public void openWritableLibrary() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("binSer.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(model.getLibrary());
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openReadableLibrary() throws IOException {
        Playlist library = null;
        try (FileInputStream fis = new FileInputStream("d.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            library = (Playlist) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("binSer.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(model.getPlaylist());
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        Playlist playlist = null;
        try (FileInputStream fis = new FileInputStream("d.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            playlist = (Playlist) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeSong(ISong s) throws IOException {
        try {
            oos.writeObject(s);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ISong readSong() throws IOException, ClassNotFoundException {
        ISong songToRead=null;
        try {
            songToRead = (ISong) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return songToRead;
    }

    @Override
    public void writeLibrary(IPlaylist p) throws IOException {
        for (ISong s : p) {
            writeSong(s);
        }
    }

    @Override
    public IPlaylist readLibrary() throws IOException, ClassNotFoundException {
        ISong song;
        model.Playlist library= new model.Playlist();
        while((song=readSong())!=null){
            library.add(song);
        }
        return library;
    }

    @Override
    public void writePlaylist(IPlaylist p) throws IOException {
        for(ISong s:p){
            writeSong(s);
        }
    }

    @Override
    public IPlaylist readPlaylist() throws IOException, ClassNotFoundException {
        ISong song;
        model.Playlist playlist= new model.Playlist();
        while((song=readSong())!=null){
            playlist.add(song);
        }
        return playlist;
    }

    @Override
    public void closeWritableLibrary() {
        try{
            oos.flush();
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadableLibrary() {
        try {
             ois.close();
             fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeWritablePlaylist() {
        try{
            oos.flush();
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {
        try {
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
