package com.example.controlinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;

import javafx.stage.FileChooser.ExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainController {

    Stage stage;
    Parent root;
    Scene scene;
    Visitante person;


    @FXML
    private ComboBox choiceAge;
    @FXML
    private TextField textFieldId;
    @FXML
    private CheckBox radioAffiliatted;
    @FXML
    private ComboBox selectorCate;
    @FXML
    private Button exportButton;
    @FXML
    private TextField txtNombre;
    @FXML
    private GridPane gridResume;
    @FXML
    private Label btnNew;
    @FXML
    private Label labelPrice;

    @FXML
    private TextArea txtArea;


    @FXML
    private void SwitchScene1(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private void SwitchScene2(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-interface.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("C:/Users/Harold/Desktop/Practices/java/class1/ControlInterface/src/main/resources/CSS/logo.png"));

        radioAffiliatted.setVisible(false);
        selectorCate.setVisible(false);

        ObservableList<Integer> items = FXCollections.observableArrayList();
        for (int i = 1; i <= 120; i++) {
            items.add(i);
        }
        choiceAge.setItems(items);
        choiceAge.setValue("18");


        ObservableList<String> categories = FXCollections.observableArrayList(
                "Categoria: A", "Categoria: B", "Categoria: C"
        );

        selectorCate.setItems(categories);
        selectorCate.setValue("Categoria: A");


    }


    int selectedValue;
    String selectedCate;

    @FXML
    private void getAgeSelected() {
        Object selectedItem = choiceAge.getValue();
        selectedValue = Integer.parseInt(selectedItem.toString());
        System.out.println("Selected value: " + selectedValue);

        if (selectedValue >= 18) {
            radioAffiliatted.setVisible(true);
            radioAffiliatted.setSelected(false);
        } else {
            radioAffiliatted.setVisible(false);
            selectorCate.setVisible(false);
        }
    }

    @FXML
    private void isAffiliattedSelected() {
        if (radioAffiliatted.isSelected() == true) {
            selectorCate.setVisible(true);
        } else {
            selectorCate.setVisible(false);
        }
    }

    @FXML
    private void selectedCategory() {
        Object selectedItem = selectorCate.getValue();
        selectedCate = selectedItem.toString();
        System.out.println("Selected value: " + selectedCate);
    }


    int size = 0;
    Visitante[] arr = new Visitante[10];


    @FXML
    private void SwitchScene3(ActionEvent event) throws IOException {


        String text = txtNombre.getText();
        String textId = textFieldId.getText();


        if (text.isEmpty() || textId.isEmpty()) {


        } else {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order-resume.fxml"));
            fxmlLoader.setController(this);
            root = fxmlLoader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            Boolean isAffiliatted = radioAffiliatted.isSelected();
            String idText = textFieldId.getText();

            String precio = "30000";

            if (isAffiliatted == true) {

                switch (selectedCate) {
                    case "Categoria: A":
                        precio = "25500";
                        break;
                    case "Categoria: B":
                        precio = "21000";
                        break;
                    case "Categoria: C":
                        precio = "15000";
                        break;
                    default:
                        precio = "30000";
                }
            } else {
                precio = "5000";
                if (selectedValue >= 18) {
                    precio = "30000";
                }
            }

            person = new Visitante(txtNombre.getText(), selectedValue, isAffiliatted, selectedCate, precio, idText);

            arr[size] = person;
            size++;

            int priceTotal = 0;

            for (int i = 0; i < size; i++) {
                Visitante person = arr[i];
                priceTotal += Integer.parseInt(person.getPrecio());
                addRow(person.getName(), person.getAge(), person.getAffiliated(), person.getCategory(), person.getPrecio(), person.getId());
            }


            DecimalFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(priceTotal);

            labelPrice.setText("$ " + formattedNumber);
        }


    }


    @FXML
    private void SwitchScene4(ActionEvent event) throws IOException {


        exportJson();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finishing-order.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void SwitchScene5(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("data.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private void addRow(String name, int age, Boolean affiliated, String category, String precio, String id) {
        int numRows = gridResume.getRowCount();
        Label label1 = new Label(name);
        String type;
        if (age >= 18) {
            type = "Adulto";
        } else {
            type = "Infante";
        }
        Label label2 = new Label(type);
        label2.setAlignment(Pos.CENTER);
        label2.setPrefWidth(139);
        String valueAffiliated;
        if (affiliated == true) {
            valueAffiliated = "Afiliado";
        } else {
            valueAffiliated = "No Afiliado";
            category = "Invitado";
        }
        Label label3 = new Label(valueAffiliated);
        label3.setAlignment(Pos.CENTER);
        label3.setPrefWidth(139);
        Label label4 = new Label(category);
        label4.setAlignment(Pos.CENTER);
        label4.setPrefWidth(139);

        int number = Integer.parseInt(precio);
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number);

        Label label5 = new Label("$ " + formattedNumber);
        label5.setAlignment(Pos.CENTER);
        label5.setPrefWidth(139);
        Label label6 = new Label(id);
        label6.setAlignment(Pos.CENTER);
        label6.setPrefWidth(139);
        gridResume.add(label1, 0, numRows);
        gridResume.add(label6, 1, numRows);
        gridResume.add(label2, 2, numRows);
        gridResume.add(label3, 3, numRows);
        gridResume.add(label4, 4, numRows);
        gridResume.add(label5, 5, numRows);
    }


    @FXML
    private void exportJson() {

        JSONArray jsonArray = new JSONArray();

        for (Visitante visitante : arr) {
            if (visitante != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", visitante.getName());
                jsonObject.put("age", visitante.getAge());
                jsonObject.put("affiliated", visitante.getAffiliated());
                jsonObject.put("category", visitante.getCategory());
                jsonObject.put("precio", visitante.getPrecio());
                jsonObject.put("id", visitante.getId());
                jsonArray.put(jsonObject);
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export JSON File");
        fileChooser.setInitialFileName("export.txt"); // Set default file name
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonArray.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void RestrictCharactersId(KeyEvent event) {


        // Restrict the textfield to numbers only
        String text = textFieldId.getText().replaceAll("[^\\d]", "");

        // Format the text to include dots every 3 numbers
        StringBuilder formattedText = new StringBuilder();
        int textLength = text.length();
        for (int i = textLength - 1; i >= 0; i--) {
            formattedText.insert(0, text.charAt(i));
            if ((textLength - i) % 3 == 0 && i != 0) {
                formattedText.insert(0, ".");
            }
        }

        // Set the formatted text to the textfield
        textFieldId.setText(formattedText.toString());
        textFieldId.positionCaret(formattedText.length());
    }


    @FXML
    private void LoadFile() throws IOException {


        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(stage);

        String fileData = Files.readString(selectedFile.toPath());

        txtArea.setText(fileData);


    }


}