package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sokoban.viewmodel.Board4PlayViewModel;
import sokoban.viewmodel.BoardViewModel;

import java.util.Objects;

abstract public class BoardView<T extends BoardViewModel> extends BorderPane {

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private MenuBar menuBar;

    private static final int SCENE_MIN_WIDTH = 520;
    private static final int SCENE_MIN_HEIGHT = 520;

    private  final int GRID_WIDTH = BoardViewModel.gridWidth();

    public int getGRID_WIDTH() {
        return GRID_WIDTH;
    }

    public T getModel() {
        return model;
    }

    private final T model;

    public BoardView(Stage stage, T model){
        this.model = model;
        start(stage);
    }

    public int getGRID_HEIGHT() {
        return GRID_HEIGHT;
    }

    private  final int GRID_HEIGHT = BoardViewModel.gridHeight();

    public HBox getHeaderBox() {
        return headerBox;
    }

    private void start(Stage stage){
       configMainComponements(stage);

        stage.setTitle("Sokoban");

        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        String cssFile = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    private void configMainComponements(Stage stage){
        stage.setTitle("Sokoban");
        createMenu();
        createGrid();
       createCompteur();
        createButton();

    }

   public abstract void createGrid();

    public abstract void createCompteur();

    public abstract void createMenu();

    public abstract void createButton();


    private final HBox headerBox = new HBox();


}
