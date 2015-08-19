/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notificacao;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Thiago Fernandes
 * @email thiagofdso.ufpa@gmail.com
 */
public class NotificacaoApp extends Application {
    public static StringProperty message;
    public static DoubleProperty duration;
    public static BooleanProperty isRunning;
    static{
        message= new SimpleStringProperty(null,"mensagem","mensagem");   
        duration= new SimpleDoubleProperty(null,"duration",1);
        isRunning= new SimpleBooleanProperty(null,"duration",false);
    }

    private FxNotificacaoController controle;
    private WindowPosition position;

    public Scene abreNotificacao() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "FxNotificacao.fxml"));
        Parent parent = (Parent) loader.load();
        controle = loader.getController();
        Scene scene = new Scene(parent);
        controle.setMessage(NotificacaoApp.message.get());
        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        position = WindowPosition.BOTTOMRIGHT;
        Scene scene = abreNotificacao();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        setPosition(stage);
        stage.show();
        animation(stage);
        fechar(stage);
    }

    public void animation(Stage stage) {
        double currentY = stage.getY();
        DoubleProperty y = new SimpleDoubleProperty(currentY);
        y.addListener((obs, oldY, newY) -> stage.setY(newY.doubleValue()));
        KeyFrame primeiro = null;
        switch (position) {
            case BOTTOMLEFT:
            case BOTTOMRIGHT:
                primeiro = new KeyFrame(Duration.seconds(NotificacaoApp.duration.get()), new KeyValue(y, currentY - stage.getHeight()));
                break;
            case TOPLEFT:
            case TOPRIGHT:
                primeiro = new KeyFrame(Duration.seconds(NotificacaoApp.duration.get()), new KeyValue(y, currentY + stage.getHeight()));
                break;
        }
        KeyFrame segundo = new KeyFrame(Duration.seconds(NotificacaoApp.duration.get()), new KeyValue(y, currentY));
        Timeline animation1 = new Timeline(primeiro);
        Timeline animation2 = new Timeline(segundo);
        SequentialTransition sequence = new SequentialTransition(animation1, animation2);
        sequence.play();
    }

    public void fechar(Stage stage) {
        new Thread(new Task<Void>() {
            @Override
            public Void call() {
                try {
                    Thread.sleep((long)NotificacaoApp.duration.get()*2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TranslateWindowExample.class.getName()).log(Level.SEVERE, null, ex);
                }
                NotificacaoApp.isRunning.set(false);
                stage.close();
                return null;
            }
        }).start();
    }

    public void setPosition(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        switch (position) {
            case BOTTOMLEFT:
                stage.setX(0);
                stage.setY(primaryScreenBounds.getHeight());
                break;
            case BOTTOMRIGHT:
                stage.setX(primaryScreenBounds.getWidth() - 275);
                stage.setY(primaryScreenBounds.getHeight());
                break;
            case TOPLEFT:
                stage.setX(0);
                stage.setY(-122);
                break;
            case TOPRIGHT:
                stage.setX(primaryScreenBounds.getWidth() - 275);
                stage.setY(-122);
                break;
        }
    }
}
