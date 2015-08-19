/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;

/**
 *
 * @author thiag
 */
public class BindingUtils {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static StringBinding getStringBinding(ObjectProperty<LocalDate> propriedadeData) {
        return Bindings.createStringBinding(()
                -> propriedadeData.get() == null ? "" : dateTimeFormatter.format(propriedadeData.get()),
                propriedadeData);
    }
}
