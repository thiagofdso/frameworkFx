/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modelos.properties;

import com.modelos.EntidadeBase;

/**
 *
 * @author Thiago Fernandes
 * @param <E>
 * @email thiagofdso.ufpa@gmail.com
 */
public abstract class PropertyBase<E extends EntidadeBase>{
    E entidade;
    public PropertyBase(){
        entidade = null;
    }
    public PropertyBase(E entidade){
        this.entidade = entidade;
    }
    public abstract void setEntidade(E entidade);
    public abstract E getEntidade();
}
