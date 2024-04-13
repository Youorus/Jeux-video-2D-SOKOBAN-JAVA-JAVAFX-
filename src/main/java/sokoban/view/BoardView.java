package sokoban.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sokoban.viewmodel.BoardViewModel;

import java.util.Objects;

abstract public class BoardView<T extends BoardViewModel> extends BorderPane {

    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;


    public T getViewModel() {
        return viewModel;
    }

    private  T viewModel;

    public Stage getStage() {
        return stage;
    }

    private final Stage stage;

    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
    }

    public BoardView(Stage stage, T viewModel){
        this.viewModel = viewModel;
        this.stage = stage;
        start(stage);
    }


    public HBox getHeaderBox() {
        return headerBox;
    }

    private void start(Stage stage){

        stage.setTitle("Sokoban");
       configMainComponements();

        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        String cssFile = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        createButton();
    }

    public void configMainComponements(){
        createMenu();
        createGrid();
       createCompteur();
    }


   public abstract void createGrid();

    public abstract void createCompteur();

    public abstract void createMenu();

    public abstract void createButton();


    private final HBox headerBox = new HBox();

    public HBox getFooterBox() {
        return footerBox;
    }

    private final HBox footerBox = new HBox();


}
