package sokoban.view;

import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class MenueView {
    private MenuBar menuBar;

    private void start(Stage stage){
        Button newButton = new Button("New");
    }
    private void confirmationDialog(){
        Stage confirmDialogStage = new Stage();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Confirmation Dialog");


        alert.getDialogPane().setContentText("Do you want to save your changes ?");
        alert.getDialogPane().setHeaderText("Your board has been modified.");

        Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.YES){
                System.out.println("yes button has been pressed");
            } else if(result.get()== ButtonType.NO){
                System.out.println("no button has been pressed");
            } else if(result.get()== ButtonType.CANCEL){
                System.out.println("blabl cancel");
            }

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
            alert.showAndWait();
        });

        fileMenu.getItems().add(newMenuItem);

        menuBar.getMenus().add(fileMenu);

    }
}
