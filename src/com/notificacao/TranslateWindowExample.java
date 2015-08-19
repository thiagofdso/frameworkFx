/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notificacao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TranslateWindowExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button moveButton = new Button("Move");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        StackPane root = new StackPane(moveButton);
        Scene scene = new Scene(root, 250, 150);
        primaryStage.setX(primaryScreenBounds.getWidth() - 250);
        primaryStage.setY(primaryScreenBounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        double currentY = primaryStage.getY();
        DoubleProperty y = new SimpleDoubleProperty(currentY);
        y.addListener((obs, oldY, newY) -> primaryStage.setY(newY.doubleValue()));
        KeyFrame sobe = new KeyFrame(Duration.seconds(2), new KeyValue(y, currentY - scene.getHeight()));
        KeyFrame desce = new KeyFrame(Duration.seconds(2), new KeyValue(y, currentY));
        Timeline animationSobe = new Timeline(sobe);
        Timeline animationDesce = new Timeline(desce);
        SequentialTransition sequence = new SequentialTransition(animationSobe, animationDesce);
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                try {
                    Thread.sleep(4000);                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(TranslateWindowExample.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                return null;
            }
        };
        sequence.play();
        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
