    package sokoban.view;

    import javafx.scene.control.*;
    import javafx.scene.layout.ColumnConstraints;
    import javafx.scene.layout.GridPane;
    import javafx.scene.paint.Color;
    import javafx.stage.FileChooser;
    import javafx.stage.Stage;
    import javafx.util.converter.NumberStringConverter;
    import sokoban.model.*;
    import sokoban.viewmodel.Board4DesignViewModel;
    import sokoban.viewmodel.MenuViewModel;
    import javafx.scene.control.Button;

    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.*;

    public class MenuView extends  MenuBar{

        private int newWidth = 0;
        private int newHeight = 0;

        private final MenuViewModel menuViewModel;

        public Alert getAlert() {
            return alert;
        }

        private Alert alert;
        private final Board4DesignViewModel board4DesignViewModel;

        public MenuView(MenuViewModel menuViewModel, Board4DesignViewModel board4DesignViewModel) {
            this.menuViewModel = menuViewModel;
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

            exitMenuItem.setOnAction(event -> {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            });

            openMenuItem.setOnAction(event -> {
                openFile();
            });


            saveMenuItem.setOnAction(event -> {
                saveAs(board4DesignViewModel.getMatrix()); // Appel de la méthode saveAs() lorsque le bouton "Save As" est cliqué
            });

            this.getMenus().add(fileMenu);
        }
        private MenuItem dialogueBox(){

            MenuItem newMenuItem = new MenuItem("New...");
            newMenuItem.setOnAction(event -> {

                if (board4DesignViewModel.getBoard4Design().gridEditedProperty().get()){

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
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

            fileChooser.setInitialDirectory(new File("boards"));

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
                        boolean hasBox = false;
                        boolean hasGoal = false;
                        boolean hasPlayer = false;

                        // Vérifier si la cellule contient une boîte et/ou un objectif
                        for (Element element : matrix[i][j].getCellsElements()) {
                            if (element instanceof Box) {
                                hasBox = true;
                            } else if (element instanceof Goal) {
                                hasGoal = true;
                            }else if (element instanceof Player) {
                                hasPlayer = true;
                            }
                        }

                        if (matrix[i][j].getCellsElements().isEmpty()) {
                            // Si la cellule est vide, écrire un espace
                            writer.write(" ");
                        }
                        // Écrire le caractère correspondant en fonction des éléments présents dans la cellule
                        if (hasBox && hasGoal) {
                            writer.write("*"); // Écrire "*" si la cellule contient à la fois une boîte et un objectif
                        } else if (hasPlayer && hasGoal) {
                            writer.write("+");
                        } else {
                            // Si la cellule ne contient pas à la fois une boîte et un objectif,
                            // écrire les éléments individuellement
                            for (Element element : matrix[i][j].getCellsElements()) {
                                writer.write(element.toString());
                            }
                        }
                    }
                    writer.write("\n"); // Ajouter un saut de ligne après chaque ligne de la grille
                }
                System.out.println("Les éléments de la grille ont été écrits dans le fichier.");
            } catch (IOException e) {
                System.out.println("Erreur lors de l'écriture des éléments dans le fichier : " + e.getMessage());
            }
        }




        private void openFile() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Sokoban Board");
            fileChooser.setInitialDirectory(new File("boards"));

            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (file != null) {
                // Le fichier a été sélectionné, maintenant, lisez son contenu et remplissez la grille
                int newWidth = 0;
                int newHeight = 0;
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        newHeight++;
                        newWidth = Math.max(newWidth, line.length());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (newHeight != board4DesignViewModel.getGridViewModel().getBoard4Design().getGrid4Design().getGridHeight() ||
                        newWidth != board4DesignViewModel.getGridViewModel().getBoard4Design().getGrid4Design().getGridWidth()) {
                    // Les dimensions de la grille chargée sont différentes des dimensions actuelles
                    menuViewModel.setHeight(newHeight);
                    menuViewModel.setWidth(newWidth);
                    menuViewModel.updateModel();
                    readElementsFromFile(file);
                }else {
                    readElementsFromFile(file);
                }


            }
        }

        private void readElementsFromFile(File file) {
            try (Scanner scanner = new Scanner(file)) {
                for (int i = 0; scanner.hasNextLine() && i < board4DesignViewModel.getMatrix().length; i++) {
                    String line = scanner.nextLine();
                    for (int j = 0; j < line.length() && j < board4DesignViewModel.getMatrix()[i].length; j++) {
                        char elementSymbol = line.charAt(j);

                        // Créer une liste d'éléments à ajouter à la cellule
                        List<Element> elementsToAdd = new ArrayList<>();

                        switch (elementSymbol) {
                            case '*':
                                // Ajouter une Box et un Goal à la liste d'éléments
                                elementsToAdd.add(new Box());
                                elementsToAdd.add(new Goal());
                                break;
                            case '+':
                                // Ajouter un Player et un Goal à la liste d'éléments
                                elementsToAdd.add(new Player());
                                elementsToAdd.add(new Goal());
                                break;
                            default:
                                // Si le symbole correspond à un élément normal
                                Element element = Element.fromSymbol(elementSymbol);
                                if (element != null) {
                                    elementsToAdd.add(element);
                                }
                                break;
                        }

                        // Ajouter les éléments à la cellule correspondante
                        for (Element element : elementsToAdd) {
                            board4DesignViewModel.getGridViewModel().getBoard4Design().getGrid4Design().getCell4Design().add(i, j, element);
                        }

                        // Marquer la grille comme non modifiée
                        board4DesignViewModel.getBoard4Design().gridEditedProperty().set(false);
                    }
                }
                System.out.println("Les éléments du fichier ont été copiés dans la grille actuelle.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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


            intField1.textProperty().bindBidirectional(menuViewModel.widthProperty(), new NumberStringConverter());
            intField2.textProperty().bindBidirectional(menuViewModel.heightProperty(), new NumberStringConverter());


            Label widthError = new Label("Width must be between 10 and 50");
            widthError.setTextFill(Color.RED);


            Label heightError = new Label("Height must be between 10 and 50");
            heightError.setTextFill(Color.RED);


            intField1.textProperty().addListener((observable, oldValue, newValue) -> {
                menuViewModel.validateWidth(newValue);
            });


            intField2.textProperty().addListener((observable, oldValue, newValue) -> {
                menuViewModel.validateHeight(newValue);
            });


            GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.add(new Label("Width "), 0, 0);
            grid.add(intField1, 1, 0);

            grid.add(new Label("Height "), 0, 2);
            grid.add(intField2, 1, 2);

            grid.add(widthError, 1, 1);
            widthError.managedProperty().bind(widthError.visibleProperty());
            widthError.visibleProperty().bind(menuViewModel.isValidWidthProperty().not());


            grid.add(heightError, 1, 3);
            heightError.managedProperty().bind(heightError.visibleProperty());
            heightError.visibleProperty().bind(menuViewModel.isValidHeightProperty().not());


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
                    menuViewModel.isValidWidthProperty().not()
                            .or(menuViewModel.isValidHeightProperty().not())
            );

            newDimensionGrille
                    .showAndWait()
                    .filter(button -> button == okButton)
                    // L'utilisateur a cliqué sur ok
                    .ifPresent(button -> menuViewModel.updateModel());
        }

    }
