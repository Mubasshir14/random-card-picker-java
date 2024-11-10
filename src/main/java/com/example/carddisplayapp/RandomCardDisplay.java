package com.example.carddisplayapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardDisplay extends Application {
    private HBox cardBox;

    @Override
    public void start(Stage primaryStage) {
        // Set up the main layout
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        // Create the HBox to display cards and center it
        cardBox = new HBox(20);
        cardBox.setAlignment(Pos.CENTER);

        // Initial display of random cards
        displayRandomCards();

        // Create the button to refresh the cards
        Button refreshButton = new Button("Shuffle Cards");
        refreshButton.setOnAction(e -> displayRandomCards());

        // Organize the main layout in a VBox
        VBox mainLayout = new VBox(20, cardBox, refreshButton);
        mainLayout.setAlignment(Pos.CENTER);

        // Add the VBox to the root StackPane
        root.getChildren().add(mainLayout);

        // Set up the scene and apply CSS
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Random Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRandomCards() {
        // Clear existing cards
        cardBox.getChildren().clear();

        // Generate a shuffled list of card numbers
        List<Integer> cardNumbers = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cardNumbers.add(i);
        }
        Collections.shuffle(cardNumbers);

        // Select the first three cards from the shuffled list
        for (int i = 0; i < 3; i++) {
            int cardNumber = cardNumbers.get(i);
            String imagePath = "/image/card/" + cardNumber + ".png";
            Image cardImage = new Image(getClass().getResourceAsStream(imagePath));

            if (cardImage.isError()) {
                System.out.println("Error loading image: " + imagePath);
            }

            ImageView cardView = new ImageView(cardImage);
            cardView.setFitWidth(150);  // Adjust the card size as needed
            cardView.setFitHeight(225);

            // Create a label for the card number
            Label cardLabel = new Label("Card No." + cardNumber);
            cardLabel.getStyleClass().add("card-label");

            // Stack card image and label in a VBox
            VBox cardContainer = new VBox(10, cardView, cardLabel);
            cardContainer.setAlignment(Pos.CENTER);

            // Add the card container to the card box
            cardBox.getChildren().add(cardContainer);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}