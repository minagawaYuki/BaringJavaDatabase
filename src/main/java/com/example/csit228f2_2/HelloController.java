package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class HelloController {
    public ToggleButton tbNight;
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnNotes;
    @FXML
    private HBox hboxRegister;
    @FXML
    private Button btnRegisterForm;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputEmail;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void onBtnRegisterClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onNightModeClick() {
        if (tbNight.isSelected()) {
            // night mode
            tbNight.getScene().getStylesheets().add(
                    getClass().getResource("styles.css").toExternalForm());
        } else {
            tbNight.getScene().getStylesheets().clear();
        }
    }
    @FXML
    private void onBtnRegisterFormClick(ActionEvent event) throws IOException {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        String email = inputEmail.getText();
        User user1 = new User(username, email, password);
        CRUD.registerUser(user1);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(HelloApplication.scene);
        stage.show();
    }
    @FXML
    private void onBtnLogoutClick(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(HelloApplication.scene);
        stage.show();
    }
    @FXML
    private void onBtnNotesClick(ActionEvent event) throws SQLException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(Scenes.getMainScene());
        stage.show();
    }


}