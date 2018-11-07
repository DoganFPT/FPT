package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Song implements interfaces.ISong {
        private long id;
        private SimpleStringProperty path= new SimpleStringProperty("Path");
        private SimpleStringProperty title = new SimpleStringProperty("Title");
        private SimpleStringProperty interpret= new SimpleStringProperty("Singer");
        private SimpleStringProperty album = new SimpleStringProperty("Album");

        public Song(){

        }


        public Song(long id, String path, String title, String interpret, String album){
            this.id=id;
            this.path.set(path);
            this.title.set(title);
            this.interpret.set(interpret);
            this.album.set(album);
        }

        public String getAlbum() {
                return album.get();
        }
        public void setAlbum(String album) {
                this.album.set(album);
        }

        public String getPath() {
            return path.get();
        }

        public void setPath(String path) {
            this.path.set(path);
        }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
        public String getTitle() {
            return title.get();
        }

        public void setTitle(String title) {
            this.title.set(title);
        }
        public String getInterpret() {
            return interpret.get();
        }

        public void setInterpret(String interpret) {
            this.interpret.set(interpret);
        }

        public String toString(){
                return getTitle();
        }
        @Override
        public ObservableValue<String> pathProperty() {
                return path;
        }

        @Override
        public ObservableValue<String> albumProperty() {
                return album;
        }

        @Override
        public ObservableValue<String> interpretProperty() {
                return interpret;
        }

        @Override
        public ObservableValue<String> titleProperty() {
                return title;
        }





    }

