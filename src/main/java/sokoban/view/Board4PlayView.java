package sokoban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.Board4PlayViewModel;

public class Board4PlayView extends BoardView<Board4PlayViewModel> {

    private Board4PlayViewModel board4PlayViewModel;
    private ButtonFinish4PlayView buttonFinish4PlayView;
    private Label headerLabel;

    public Board4PlayView(Stage secondaryStage, Board4PlayViewModel board4PlayViewModel){
        super(secondaryStage, board4PlayViewModel);
    }



    public void createGrid () {
        int gridWidth = getViewModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().getGridWidth();
        int gridHeight = getViewModel().getGrid4PlayViewModel().getBoard4Play().getGrid4Play().getGridHeight();

        DoubleBinding gridWidthBinding = Bindings.createDoubleBinding(
                () -> {
                    double availableWidth = widthProperty().get() - 55;
                    double availableHeight = heightProperty().get() - getHeaderBox().heightProperty().get() - getFooterBox().heightProperty().get() - 55;
                    double aspectRatio = (double) gridWidth / gridHeight;
                    double maxWidthBasedOnHeight = (availableHeight * aspectRatio);
                    double finalWidth = Math.min(availableWidth, maxWidthBasedOnHeight);

                    return Math.floor(finalWidth);

                },
                widthProperty(),
                heightProperty(),
                getHeaderBox().heightProperty(),
                getHeaderBox().heightProperty(),
                getFooterBox().heightProperty() );

        DoubleBinding gridHeightBinding = gridWidthBinding.divide(gridWidth).multiply(gridHeight);

        Grid4PlayView grid4PlayView = new Grid4PlayView(getViewModel().getGrid4PlayViewModel(),gridWidthBinding, gridHeightBinding);

        grid4PlayView.minHeightProperty().bind(gridHeightBinding);
        grid4PlayView.minWidthProperty().bind(gridWidthBinding);
        grid4PlayView.maxHeightProperty().bind(gridHeightBinding);
        grid4PlayView.maxWidthProperty().bind(gridWidthBinding);

        setCenter(grid4PlayView);

    }


    public void createCompteur(){
        headerLabel = new Label("Score");
        headerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px"); // Augmenter la taille de la police
        VBox.setMargin(headerLabel, new Insets(10, 0, 0, 0)); // Ajouter des marges depuis le haut

        Label moveCount = new Label();
        moveCount.textProperty().bind(getViewModel().moveCountProperty().asString("Number of moves played: %d"));

        Label boxOnGoalCount = new Label();
        boxOnGoalCount.textProperty().bind(getViewModel().boxAndGoalCountProperty().asString("Number of goals reached: %d of " + getViewModel().getGrid4PlayViewModel().getBoard4Play().numberGoal()));

        Label result = new Label();
        result.textProperty().bind(getViewModel().moveCountProperty().asString("You won in %d moves, congratulations !!"));

        result.visibleProperty().bind(getViewModel().playerWinProperty());
        result.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");

        VBox headerScore = new VBox(0); // Espacement vertical de 5 pixels entre les enfants
        headerScore.getChildren().addAll(headerLabel, moveCount, boxOnGoalCount, result);

        getHeaderBox().getChildren().addAll(headerScore);
        getHeaderBox().setAlignment(Pos.CENTER);

        setTop(getHeaderBox());
    }

    @Override
    public void createMenu() {

    }

    @Override
    public void createButton() {
        buttonFinish4PlayView = new ButtonFinish4PlayView(getStage(),getViewModel());
        getFooterBox().setAlignment(Pos.TOP_CENTER);
        getFooterBox().setPrefHeight(70);
        getFooterBox().setPadding(new Insets(0, 0, 0, 0));
        getFooterBox().getChildren().add(buttonFinish4PlayView);
        setBottom(getFooterBox());
    }

}
