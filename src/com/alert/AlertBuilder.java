/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Thiago Fernandes
 * @email thiagofdso.ufpa@gmail.com
 */
public class AlertBuilder {

    public final static ButtonType buttonSim = new ButtonType("Sim");
    public final static ButtonType buttonNao = new ButtonType("Não");

    public static Alert getRemoveAlert(Window owner, WebView webView, String icon, String titulo, String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(owner);
        ((Stage) alert.getDialogPane().getScene().getWindow()).initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().setEffect(new InnerShadow());
        alert.setTitle(titulo);
        alert.setHeaderText(headerText);
        alert.getDialogPane().setExpandableContent(webView);
        alert.setGraphic(new ImageView(icon));
        alert.getButtonTypes().setAll(buttonSim, buttonNao);
        return alert;
    }
}
