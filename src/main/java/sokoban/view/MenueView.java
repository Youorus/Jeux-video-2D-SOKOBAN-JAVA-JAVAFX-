package sokoban.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

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
        Alert newDimensionGrille = new Alert(Alert.AlertType.NONE);
        newDimensionGrille.setTitle("Sokoban");
        newDimensionGrille.setHeaderText("Give new game dimensions");
        newDimensionGrille.setContentText("This is a new dialog!");

        TextField intField1 = new TextField();
        intField1.setPromptText("Width ");

        TextField intField2 = new TextField();
        intField2.setPromptText("Height ");

        Label widthError = new Label("Width must be between 10 to 50");
        widthError.setTextFill(Color.RED);
        widthError.setVisible(false);
        widthError.setManaged(false);

        Label heightError = new Label("Height must be between 10 to 50");
        heightError.setTextFill(Color.RED);
        heightError.setVisible(false);
        heightError.setManaged(false);

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


        intField1.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int Width = Integer.parseInt(newValue);
                if (Width < 10 || Width > 50) {
                    widthError.setText("Width must be between 10 and 50");
                    widthError.setVisible(true);
                } else {
                    widthError.setVisible(false);
                }
            } catch (NumberFormatException e) {
                widthError.getText();
                widthError.setVisible(true);
            }
        });

        intField2.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int Height = Integer.parseInt(newValue);
                if (Height < 10 || Height > 50) {
                    heightError.setText("Height must be between 10 and 50");
                    heightError.setVisible(true);
                } else {
                    heightError.setVisible(false);
                }
            } catch (NumberFormatException e) {
                heightError.getText();
                heightError.setVisible(true);
            }
        });

        newDimensionGrille.showAndWait();

//        newDimensionGrille.setResultConverter(dialogButton -> {
//            if(dialogButton == okButton) {
//                try {
//                    int Width = Integer.parseInt(intField1.getText());
//                    int Height = Integer.parseInt(intField2.getText());
//
//                    boolean invalidInput = false;
//
//                    if (Width < 10 || Width > 50) {
//                        widthError.setVisible(true);
//                        invalidInput = true;
//                    } else{
//                        widthError.setVisible(false);
//                    }
//
//                    if(Height < 10 || Height > 50){
//                        heightError.setVisible(true);
//                        invalidInput = true;
//                    } else{
//                        heightError.setVisible(false);
//                    }
//
//                    if(invalidInput){
//                        return null;
//                    }
//
//
//
//                    //return new Pair<>(Width, Height);
//                } catch (NumberFormatException e) {
//                    widthError.setText("Width must be between 10 and 50");
//                    heightError.setText("Height must be between 10 and 50");
//                    widthError.setVisible(true);
//                    heightError.setVisible(true);
//                    return null;
//                }
//            }
//            return null;
//        });




        //newDimensionGrille.showAndWait();
        //return newDimensionGrille.getResult();

    }
}
