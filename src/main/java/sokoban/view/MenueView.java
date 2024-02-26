package sokoban.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.NumberStringConverter;
import sokoban.viewmodel.MenueViewModel;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.util.Optional;

public class MenueView {
    private MenuBar menuBar;
    private BoadView boadView;


    public void setBoadView(BoadView boadView) {
        this.boadView = boadView;
    }

    public void showConfirmationDialog1(){
        confirmationDialog1();
    }

    public MenuBar createMenuBar(){
        confirmationDialog1();
        return menuBar;
    }

    private  void confirmationDialog1(){
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");

        newMenuItem.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Your board has been modified.");
            alert.setContentText("Do you want to save your changes ?");
            //alert.showAndWait();
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton,noButton,cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == yesButton){
                System.out.println("yes button has been pressed");
                 newDimension();
            } else if(result.get()== noButton){
                System.out.println("no button has been pressed");
            } else if(result.get()== cancelButton){
                if (boadView != null) {
                    // Redirection vers la BoardView
                    Stage stage = (Stage) menuBar.getScene().getWindow();
                    stage.setScene(boadView.getScene());
                    stage.show();
                }
            }
        });


        fileMenu.getItems().add(newMenuItem);

        menuBar.getMenus().add(fileMenu);

    }

    private void newDimension(){
        MenueViewModel menueViewModel = new MenueViewModel();
        Alert newDimensionGrille = new Alert(Alert.AlertType.NONE);
        newDimensionGrille.setTitle("Sokoban");
        newDimensionGrille.setHeaderText("Give new game dimensions");
        newDimensionGrille.setContentText("This is a new dialog!");

        TextField intField1 = new TextField();
        intField1.setPromptText("Width ");

        TextField intField2 = new TextField();
        intField2.setPromptText("Height ");


        intField1.textProperty().bindBidirectional(menueViewModel.widthProperty(), new NumberStringConverter());
        intField2.textProperty().bindBidirectional(menueViewModel.heightProperty(), new NumberStringConverter());


        Label widthError = new Label("Width must be between 10 and 50");
        widthError.setTextFill(Color.RED);
        widthError.visibleProperty().bind(menueViewModel.isValidWidthProperty().not());


        Label heightError = new Label("Height must be between 10 and 50");
        heightError.setTextFill(Color.RED);
        heightError.visibleProperty().bind(menueViewModel.isValidHeightProperty().not());



        intField1.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                menueViewModel.widthProperty().set(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                menueViewModel.widthProperty().set(0); // Valeur invalide pour forcer l'erreur
            }
            menueViewModel.validateWidth(); // Valide à chaque changement
        });


        intField2.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                menueViewModel.heightProperty().set(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                menueViewModel.heightProperty().set(0); // Valeur invalide pour forcer l'erreur
            }
            menueViewModel.validateHeight(); // Valide à chaque changement
        });



        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.add(new Label("Width "), 0, 0);
        grid.add(intField1, 1, 0);

        grid.add(new Label("Height "), 0, 1);
        grid.add(intField2, 1, 1);


        newDimensionGrille.getDialogPane().setContent(grid);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        newDimensionGrille.getButtonTypes().addAll(okButton,cancelButton);

        Optional<ButtonType> result = newDimensionGrille.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            // L'utilisateur a cliqué sur ok
            menueViewModel.updateModel();
        }
        //else{ gerer si il appuie sur cancel

        newDimensionGrille.showAndWait();


    }
}
