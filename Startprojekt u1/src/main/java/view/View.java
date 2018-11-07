package view;


import interfaces.ISong;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Song;

    public class View extends BorderPane {

        private controller.controller controller;

        private ListView<interfaces.ISong> library = new ListView<interfaces.ISong>();
        private ListView<interfaces.ISong> playlist = new ListView<interfaces.ISong>();

        BorderPane borderPane = new BorderPane();

        private ChoiceBox choiceBox = new ChoiceBox();

        private Button buttonLoad = new Button("Load");
        private Button buttonSave = new Button("Save");
        private Button buttonPlay = new Button("Play");
        private Button buttonPause = new Button("Pause");
        private Button buttonNext = new Button("Next");
        private Button buttonCommit = new Button("Commit");
        private Button buttonAddAll = new Button("Add All");
        private Button buttonDeleteFromPlaylist = new Button("Delete from Playlist");
        private Button buttonAddToPlaylist = new Button("Add to Playlist");


        private TextField title = new TextField();
        private Label labelTitle = new Label("Title");
        private TextField interpret = new TextField();
        private Label labelInterpret = new Label("Interpret");
        private TextField album = new TextField();
        private Label labelAlbum = new Label("Album");



        public View() throws Exception{
            HBox hbox1 = new HBox(10, buttonPlay, buttonPause, buttonNext);
            HBox hbox2 = new HBox(10, buttonAddAll, buttonAddToPlaylist, buttonDeleteFromPlaylist);
            HBox hboxTop = new HBox(10, choiceBox, buttonLoad, buttonSave);

            VBox vbox1 = new VBox(labelTitle, title, labelInterpret, interpret, labelAlbum, album , buttonCommit);
            VBox vbox2 = new VBox(50, vbox1, hbox1);

            setLeft(library);
            setCenter(playlist);
            setRight(vbox2);
            setBottom(hbox2);
            setTop(hboxTop);



            library.setCellFactory(c -> {
                        ListCell<ISong> c = new ListCell<ISong>() {
                            protected void updateItem(Song song, boolean empty) {
                                super.updateItem(song, empty);
                                if (song == null) {
                                    setText(null);
                                } else {
                                    setText(song.getTitle());
                                }
                            }
                        };
                        return c;
                    });


            playlist.setCellFactory(e -> { ListCell<ISong> c =new ListCell<ISong>(){
                protected void updateItem(Song song, boolean empty) {
                    super.updateItem(song, empty);
                    if (song == null) {
                        setText("");
                    } else {
                        setText(song.getTitle());
                    }
                }
            };
                return c;
        });


        public ListView<interfaces.ISong> getLibrary() {
            return library;
        }

        public ListView<interfaces.ISong> getPlaylist() {
            return playlist;
        }


        public TextField getTextFieldTitel(){
            return title;
        }

        public void setTextFieldTitel(String title){
            this.title.setText(title);
        }

        public TextField getTextFieldInterpret(){
            return interpret;
        }

        public TextField getTextFieldAlbum() {
            return album;
        }

        public void setVisibleMetaData(String title, String interpret,String album   ) {


        }
    }
