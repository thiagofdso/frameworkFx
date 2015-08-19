/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modelos.properties.factory;

import com.modelos.EntidadeBase;
import com.modelos.properties.PropertyBase;

/**
 *
 * @author Thiago Fernandes
 * @param <T>
 * @param <E>
 * @email thiagofdso.ufpa@gmail.com
 */
public interface PropertyFactory <T extends PropertyBase,E extends EntidadeBase>{
    public abstract T createProperty();
    public abstract T createProperty(E entidade);
}
