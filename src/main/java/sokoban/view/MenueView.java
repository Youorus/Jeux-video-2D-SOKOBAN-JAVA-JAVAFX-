package sokoban.view;

import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import sokoban.model.Element;
import sokoban.model.Cell4Design;
import sokoban.viewmodel.Board4DesignViewModel;
import sokoban.viewmodel.MenueViewModel;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class MenueView {
    private MenuBar menuBar;
    private Board4DesignView board4DesignView;
    private Board4DesignViewModel board4DesignViewModel;

    public MenueView(Board4DesignView board4DesignView){
        this.board4DesignView = board4DesignView;
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
        MenuItem newMenuItem = new MenuItem("New...");
        MenuItem openMenuItem = new MenuItem("Open...");
        MenuItem saveMenuItem = new MenuItem("Save as...");
        MenuItem exitMenuItem = new MenuItem("Exit");

        saveMenuItem.setOnAction(event -> {
            saveAs(board4DesignView.getMatrix()); // Appel de la méthode saveAs() lorsque le bouton "Save As" est cliqué
        });

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
                newDimension();
            } else if(result.get()== cancelButton){
                if (board4DesignView != null) {
                    // Redirection vers la BoardView
                    Stage stage = (Stage) menuBar.getScene().getWindow();
                    stage.setScene(board4DesignView.getScene());
                    stage.show();
                }
            }
        });


        fileMenu.getItems().addAll(newMenuItem,openMenuItem,saveMenuItem,exitMenuItem);

        menuBar.getMenus().add(fileMenu);

    }

    private void saveAs(Cell4Design[][] matrix) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer la grille");

        // Définir un filtre pour les fichiers .xsb
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Sokoban Board Files (*.xsb)", "*.xsb");
        fileChooser.getExtensionFilters().add(extFilter);

        // Afficher la boîte de dialogue pour choisir l'emplacement et le nom du fichier
        File file = fileChooser.showSaveDialog(menuBar.getScene().getWindow());

        if (file != null) {
            // Le fichier a été sélectionné
            System.out.println("Fichier sélectionné : " + file.getAbsolutePath());

            // Écrire les éléments de la grille dans le fichier
            writeElementsToFile(file, matrix);
        }
    }
//    private void writeElementsToFile(File file, Cell[][] matrix) {
//        try (FileWriter writer = new FileWriter(file)) {
//            // Parcourir la matrice de cellules
//            for (int i = 0; i < matrix.length; i++) {
//                for (int j = 0; j < matrix[i].length; j++) {
//                    // Parcourir les éléments de chaque cellule et les écrire dans le fichier
//                    for (Element element : matrix[i][j].getCellsElements()) {
//                        writer.write(element.toString()); // Écrivez l'élément dans le fichier
//                        writer.write(" "); // Ajoutez un espace entre les éléments
//                    }
//                    writer.write("\n"); // Saut de ligne à la fin de chaque ligne de la grille
//                }
//            }
//            System.out.println("Les éléments de la grille ont été écrits dans le fichier.");
//        } catch (IOException e) {
//            System.out.println("Erreur lors de l'écriture des éléments dans le fichier : " + e.getMessage());
//        }
//    }

    private void writeElementsToFile(File file, Cell4Design[][] matrix) {
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    Element element = matrix[i][j].getValue(); // Récupérer l'élément de la cellule
                    writer.write(element.toString()); // Écrire l'élément dans le fichier
                }
                writer.write("\n"); // Ajouter un saut de ligne après chaque ligne de la grille
            }
            System.out.println("Les éléments de la grille ont été écrits dans le fichier.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture des éléments dans le fichier : " + e.getMessage());
        }
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



        Label heightError = new Label("Height must be between 10 and 50");
        heightError.setTextFill(Color.RED);


        intField1.textProperty().addListener((observable, oldValue, newValue) -> {
            menueViewModel.validateWidth(newValue);
        });


        intField2.textProperty().addListener((observable, oldValue, newValue) -> {
            menueViewModel.validateHeight(newValue);
        });



        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.add(new Label("Width "), 0, 0);
        grid.add(intField1, 1, 0);

        grid.add(new Label("Height "), 0, 2);
        grid.add(intField2, 1, 2);

        grid.add(widthError, 1, 1);
        widthError.managedProperty().bind(widthError.visibleProperty());
        widthError.visibleProperty().bind(menueViewModel.isValidWidthProperty().not());



        grid.add(heightError, 1, 3);
        heightError.managedProperty().bind(heightError.visibleProperty());
        heightError.visibleProperty().bind(menueViewModel.isValidHeightProperty().not());


        widthError.setWrapText(true);
        heightError.setWrapText(true);

        newDimensionGrille.getDialogPane().setPrefSize(250,250);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMinWidth(200);
        grid.getColumnConstraints().addAll(column1, column2);

        newDimensionGrille.getDialogPane().setContent(grid);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        newDimensionGrille.getButtonTypes().setAll(okButton,cancelButton);

        Button okButtonNode = (Button) newDimensionGrille.getDialogPane().lookupButton(okButton);
        okButtonNode.disableProperty().bind(
                menueViewModel.isValidWidthProperty().not()
                        .or(menueViewModel.isValidHeightProperty().not())
        );

        Optional<ButtonType> result = newDimensionGrille.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            // L'utilisateur a cliqué sur ok
            menueViewModel.updateModel();
            int newWidth = menueViewModel.getWidth();
            int newHeight = menueViewModel.getHeight();
            //boardViewModel.updateBoardDimensions(newWidth,newHeight);
        }


    }


}
