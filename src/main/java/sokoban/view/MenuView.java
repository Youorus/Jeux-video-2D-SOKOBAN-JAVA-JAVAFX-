package sokoban.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sokoban.viewmodel.BoardViewModel;

import java.util.Objects;
import java.util.Optional;


public class MenuView {
    private MenuBar menuBar;

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
                } else if(result.get()== noButton){
                    System.out.println("no button has been pressed");
                } else if(result.get()== cancelButton){
                    System.out.println("blabl cancel");
                }
            });


            fileMenu.getItems().add(newMenuItem);

            menuBar.getMenus().add(fileMenu);

        }
}
