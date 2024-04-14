    package sokoban.view;

    import javafx.scene.control.*;
    import javafx.scene.layout.ColumnConstraints;
    import javafx.scene.layout.GridPane;
    import javafx.scene.paint.Color;
    import javafx.stage.FileChooser;
    import javafx.util.converter.NumberStringConverter;
    import sokoban.model.Cell4Design;
    import sokoban.model.Element;
    import sokoban.viewmodel.Board4DesignViewModel;
    import sokoban.viewmodel.MenueViewModel;
    import javafx.scene.control.Button;

    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.Optional;

    public class MenueView  extends  MenuBar{

        private final MenueViewModel menueViewModel;
        private final Board4DesignViewModel board4DesignViewModel;

        public MenueView(MenueViewModel menueViewModel, Board4DesignViewModel board4DesignViewModel) {
            this.menueViewModel = menueViewModel;
            this.board4DesignViewModel = board4DesignViewModel;
            menueCreation();
        }


        public void showConfirmationDialog1() {
            confirmationDialog1();
        }

        public void menueCreation(){
            Menu fileMenu = new Menu("File");
            MenuItem newMenuItem = dialogueBox();
            MenuItem openMenuItem = new MenuItem("Open...");
            MenuItem saveMenuItem = new MenuItem("Save as...");
            MenuItem exitMenuItem = new MenuItem("Exit");
            fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, exitMenuItem);

            saveMenuItem.setOnAction(event -> {
                saveAs(board4DesignViewModel.getMatrix()); // Appel de la méthode saveAs() lorsque le bouton "Save As" est cliqué
            });

            this.getMenus().add(fileMenu);
        }
        private MenuItem dialogueBox(){

            MenuItem newMenuItem = new MenuItem("New...");
            newMenuItem.setOnAction(event -> {

                if (board4DesignViewModel.getBoard4Design().gridEditedProperty().get()){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Your board has been modified.");
                    alert.setContentText("Do you want to save your changes ?");
                    //alert.showAndWait();
                    ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == yesButton) {
                        saveAs(board4DesignViewModel.getMatrix());
                    } else if (result.get() == noButton) {
                        newDimension();
                    }
                }else {
                    newDimension();
                }

            });

            return newMenuItem;
        }
        private void saveAs(Cell4Design[][] matrix) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer la grille");

            // Définir un filtre pour les fichiers .xsb
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Sokoban Board Files (*.xsb)", "*.xsb");
            fileChooser.getExtensionFilters().add(extFilter);

            // Afficher la boîte de dialogue pour choisir l'emplacement et le nom du fichier
            File file = fileChooser.showSaveDialog(this.getScene().getWindow());

            if (file != null) {
                // Le fichier a été sélectionné
                System.out.println("Fichier sélectionné : " + file.getAbsolutePath());

                // Écrire les éléments de la grille dans le fichier
                writeElementsToFile(file, matrix);
            }
        }


        private void writeElementsToFile(File file, Cell4Design[][] matrix) {
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        for (Element element : matrix[i][j].getCellsElements()) {
                            writer.write(element.toString());
                            writer.write(" "); // Ajouter un espace entre les éléments de la cellule
                        }
                        writer.write("\t"); // Ajouter un séparateur de colonne après chaque cellule
                    }
                    writer.write("\n"); // Ajouter un saut de ligne après chaque ligne de la grille
                }
                System.out.println("Les éléments de la grille ont été écrits dans le fichier.");
            } catch (IOException e) {
                System.out.println("Erreur lors de l'écriture des éléments dans le fichier : " + e.getMessage());
            }
        }



        private void confirmationDialog1() {
            dialogueBox();
        }

        private void newDimension() {

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

            newDimensionGrille.getDialogPane().setPrefSize(250, 250);
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setMinWidth(50);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setMinWidth(200);
            grid.getColumnConstraints().addAll(column1, column2);

            newDimensionGrille.getDialogPane().setContent(grid);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            newDimensionGrille.getButtonTypes().setAll(okButton, cancelButton);

            Button okButtonNode = (Button) newDimensionGrille.getDialogPane().lookupButton(okButton);
            okButtonNode.disableProperty().bind(
                    menueViewModel.isValidWidthProperty().not()
                            .or(menueViewModel.isValidHeightProperty().not())
            );

            newDimensionGrille
                    .showAndWait()
                    .filter(button -> button == okButton)
                    // L'utilisateur a cliqué sur ok
                    .ifPresent(button -> menueViewModel.updateModel());
        }

    }
