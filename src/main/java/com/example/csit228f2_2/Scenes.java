package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Scenes {
    public static Scene getMainScene() throws SQLException {
        AnchorPane pnMain = new AnchorPane();
        GridPane grid = new GridPane();
        pnMain.getChildren().add(grid);
        grid.setAlignment(Pos.CENTER);
        Text sceneTitle = new Text("My Notes");
        sceneTitle.setStrokeType(StrokeType.CENTERED);
        sceneTitle.setStrokeWidth(100);
        sceneTitle.setFill(Paint.valueOf("#325622"));
        sceneTitle.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Button btnLogout = new Button("Back");
        btnLogout.setFont(Font.font(15));
        HBox hbRegister = new HBox();
        hbRegister.getChildren().add(btnLogout);
        hbRegister.setAlignment(Pos.CENTER);
        grid.add(hbRegister, 1, 0, 1, 1);

        Label lblNotes = new Label("Create Note: ");
        lblNotes.setTextFill(Paint.valueOf("#c251d5"));
        lblNotes.setFont(Font.font(25));
        grid.add(lblNotes, 1, 1);

        TextField tfNotes = new TextField();
        tfNotes.setFont(Font.font(35));
        grid.add(tfNotes, 1, 1);

        Button btnCreate = new Button("Create");
        btnCreate.setFont(Font.font(15));
        HBox hbCreate = new HBox();
        hbCreate.getChildren().add(btnCreate);
        hbCreate.setAlignment(Pos.CENTER);
        grid.add(hbCreate, 2, 1);

        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement();) {
            String query = "SELECT * FROM notes";
            int rowCount = 2;
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                Label userNotes = new Label(res.getString("notes"));
                lblNotes.setTextFill(Paint.valueOf("#c251d5"));
                lblNotes.setFont(Font.font(45));
                grid.add(userNotes, 0, rowCount);
                rowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(pnMain, 700, 560);

        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });

        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String note = tfNotes.getText();
                CRUD.createNote(note);
                tfNotes.setText("");
            }
        });


        return scene;

    }
}
