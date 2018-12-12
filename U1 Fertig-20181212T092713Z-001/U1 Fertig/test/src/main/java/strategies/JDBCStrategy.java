package strategies;

import interfaces.IPlaylist;
import interfaces.ISong;
import interfaces.SerializableStrategy;

import java.io.IOException;

public class JDBCStrategy implements SerializableStrategy {
    @Override
    public void openWritableLibrary() throws IOException {

    }

    @Override
    public void openReadableLibrary() throws IOException {

    }

    @Override
    public void openWritablePlaylist() throws IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException {

    }

    @Override
    public void writeSong(ISong s) throws IOException {

    }

    @Override
    public ISong readSong() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writeLibrary(IPlaylist p) throws IOException {

    }

    @Override
    public IPlaylist readLibrary() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writePlaylist(IPlaylist p) throws IOException {

    }

    @Override
    public IPlaylist readPlaylist() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void closeWritableLibrary() {

    }

    @Override
    public void closeReadableLibrary() {

    }

    @Override
    public void closeWritablePlaylist() {

    }

    @Override
    public void closeReadablePlaylist() {

    }
}
