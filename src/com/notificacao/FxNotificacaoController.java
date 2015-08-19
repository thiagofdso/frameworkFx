/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notificacao;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Thiago
 */
public class FxNotificacaoController implements Initializable {
    @FXML
    private Button fecharButton;
    @FXML
    private Label textoLabel;
    private double duration;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fecharButton.setOnAction((ActionEvent event) -> {
            ((Stage)fecharButton.getScene().getWindow()).close();
        });
    }

    public void setMessage(String mensagem){
        textoLabel.setText(mensagem);
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    public SequentialTransition getAnimation(Stage primaryStage){
        double currentY = primaryStage.getY();
        DoubleProperty y = new SimpleDoubleProperty(currentY);
        y.addListener((obs, oldY, newY) -> primaryStage.setY(newY.doubleValue()));
        KeyFrame sobe = new KeyFrame(Duration.seconds(duration), new KeyValue(y, currentY - 122.0));
        KeyFrame desce = new KeyFrame(Duration.seconds(duration), new KeyValue(y, currentY));
        Timeline animationSobe = new Timeline(sobe);
        Timeline animationDesce = new Timeline(desce);
        return new SequentialTransition(animationSobe, animationDesce);
    }
    public Task getTask(Stage primaryStage){
        return new Task<Void>() {
            @Override
            public Void call() {
                try {
                    Thread.sleep((long) duration*4000);                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(TranslateWindowExample.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                return null;
            }
        };
    }
}