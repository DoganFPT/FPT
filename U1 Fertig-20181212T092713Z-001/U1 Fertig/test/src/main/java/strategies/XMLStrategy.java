package strategies;

import interfaces.IPlaylist;
import interfaces.ISong;
import interfaces.SerializableStrategy;
import model.Playlist;
import model.Song;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XMLStrategy implements SerializableStrategy {
    private BufferedInputStream XmlBufferedInputStream;
    private BufferedOutputStream XmlBufferedOutputStream;
    private XMLDecoder decoder;
    private XMLEncoder encoder;

    @Override
    public void openWritableLibrary() throws IOException {
        XmlBufferedOutputStream =new BufferedOutputStream(new FileOutputStream("XmlSer.xml"));
        encoder= new XMLEncoder(XmlBufferedOutputStream);
    }

    @Override
    public void openReadableLibrary() throws IOException {
        XmlBufferedInputStream=new BufferedInputStream(new FileInputStream("XmlD.xml"));
        decoder = new XMLDecoder(XmlBufferedInputStream);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        XmlBufferedOutputStream =new BufferedOutputStream(new FileOutputStream("XmlSer.xml"));
        encoder= new XMLEncoder(XmlBufferedOutputStream);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        XmlBufferedInputStream=new BufferedInputStream(new FileInputStream("XmlD.xml"));
        decoder = new XMLDecoder(XmlBufferedInputStream);
    }

    @Override
    public void writeSong(ISong s) throws IOException {
        try (FileOutputStream fo = new FileOutputStream("XmlSer.xml");
             XMLEncoder encoder = new XMLEncoder(fo)) {
            encoder.writeObject(s); // write Object
            encoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ISong readSong() throws IOException, ClassNotFoundException {
        ISong songToRead = null;

        try (FileInputStream fi = new FileInputStream("XmlD.xml");
             XMLDecoder decoder = new XMLDecoder(fi)) {
            songToRead = (ISong) decoder.readObject(); // Read Object
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songToRead;
    }

    @Override
    public void writeLibrary(IPlaylist p) throws IOException {
        encoder.writeObject(p);
    }

    @Override
    public IPlaylist readLibrary() throws IOException, ClassNotFoundException {
        return (Playlist) decoder.readObject();
    }

    @Override
    public void writePlaylist(IPlaylist p) throws IOException {
        encoder.writeObject(p);
    }

    @Override
    public IPlaylist readPlaylist() throws IOException, ClassNotFoundException {
        return (Playlist) decoder.readObject();
    }

    @Override
    public void closeWritableLibrary() {
        try {
            XmlBufferedOutputStream.close();
            encoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadableLibrary() {
        try {
            XmlBufferedInputStream.close();
            decoder.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void closeWritablePlaylist() {
        try {
            XmlBufferedOutputStream.close();
            encoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {
        try {
            XmlBufferedInputStream.close();
            decoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
