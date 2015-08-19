/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utilidades;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Thiago Fernandes
 * @email thiagofdso.ufpa@gmail.com
 */
public class EffectUtil {
    public static void escaleEffect(Button button, double escale, int duration) {
        button.setOnMouseEntered((MouseEvent event) -> {
            ScaleTransition transition = new ScaleTransition(
                    Duration.millis(duration), button);
            transition.setToX(escale);
            transition.setToY(escale);
            transition.play();
        });
        button.setOnMouseExited((MouseEvent event) -> {
            ScaleTransition transition = new ScaleTransition(
                    Duration.millis(duration), button);
            transition.setToX(1.0);
            transition.setToY(1.0);
            transition.play();
        });
    }
}
