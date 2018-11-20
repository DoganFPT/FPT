package view;

import interfaces.Click;
import interfaces.ISong;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Song;

public class View extends BorderPane {
    BorderPane borderPane = new BorderPane();


    private ListView<ISong> library = new ListView<interfaces.ISong>();
    private ListView<interfaces.ISong> playlist = new ListView<interfaces.ISong>();

    private ChoiceBox choiceBox = new ChoiceBox();

    private Button load = new Button("Load");
    private Button save = new Button("Save");
    private Button play = new Button("Play");
    private Button pause = new Button("Pause");
    private Button next = new Button("Next");
    private Button commit = new Button("Commit");
    private Button addAll = new Button("Add All");
    private Button deleteFromPlaylist = new Button("Delete from Playlist");
    private Button addToPlaylist = new Button("Add to Playlist");


    private TextField title = new TextField();
    private Label labelTitle = new Label("Title");
    private TextField interpret = new TextField();
    private Label labelInterpret = new Label("Interpret");
    private TextField album = new TextField();
    private Label labelAlbum = new Label("Album");


    public View() throws Exception {
        HBox hbox1 = new HBox(10, play, pause, next);
        HBox hbox2 = new HBox(10, addAll, addToPlaylist, deleteFromPlaylist);
        HBox hboxTop = new HBox(10, choiceBox, load, save);

        VBox vbox1 = new VBox(labelTitle, title, labelInterpret, interpret, labelAlbum, album, commit);
        VBox vbox2 = new VBox(50, vbox1, hbox1);

        setLeft(library);
        setCenter(playlist);
        setRight(vbox2);
        setBottom(hbox2);
        setTop(hboxTop);


        library.setCellFactory(e -> {
            ListCell<ISong> cell = new ListCell<ISong>() {
                protected void updateItem(Song myObject, boolean b) {
                    super.updateItem(myObject, myObject == null || b);
                    if (myObject != null) {
                        setText(myObject.getTitle());
                    } else { // wichtig da sonst der text stehen bleibt!
                        setText("");
                    }
                }
            };
            return cell;


        });
    }


    private static ListCell<ISong> call(ListView<ISong> c) {
        ListCell<ISong> cell = new ListCell<ISong>() {
            protected void updateItem(Song myObject, boolean b) {
                super.updateItem(myObject, myObject == null || b);
                if (myObject != null) {
                    setText(myObject.getTitle());
                } else { // wichtig da sonst der text stehen bleibt!
                    setText("");
                }
            }
        };
        return cell;
    }
    public void play(Click c){
        play.setOnAction(e-> c.OnClick());
    }
    public void pause(Click c){
        pause.setOnAction(e-> c.OnClick());
    }
    public void next(Click c){
        next.setOnAction(e-> c.OnClick());
    }
    public void load(Click c){
        load.setOnAction(e-> c.OnClick());
    }
    public void save(Click c){
        save.setOnAction(e-> c.OnClick());
    }
    public void commit(Click c){
        commit.setOnAction(e-> c.OnClick());
    }
    public void addAll(Click c){
        addAll.setOnAction(e-> c.OnClick());
    }
    public void deleteFromPlaylist(Click c){
        deleteFromPlaylist.setOnAction(e-> c.OnClick());
    }
    public void addToPlaylist(Click c){
        addToPlaylist.setOnAction(e-> c.OnClick());
    }


    public ListView<ISong> getLibrary() {
        return library;
    }

    public ListView<ISong> getPlaylist() {
        return playlist;
    }

    public TextField getTitleText() {
        return title;
    }

    public TextField getAlbumText() {
        return album;
    }

    public TextField getInterpretText() {
        return interpret;
    }

}
