/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles.acaobutton.view;

import com.controles.acaobutton.IlegalArgumentException;
import com.modelos.EntidadeBase;
import com.modelos.properties.PropertyBase;
import javafx.collections.ObservableList;

/**
 *
 * @author Thiago
 * @param <P>
 * @param <T>
 */
public interface ViewUpdate <P extends PropertyBase<T>,T extends EntidadeBase>{
    
    /**
     * Atualiza tabela.
     * @param listaProperties
     * @param propriedade
     * @param index
     * @throws com.controles.acaobutton.IlegalArgumentException
     */
    public void update(ObservableList<P> listaProperties,P propriedade,Integer index)  throws IlegalArgumentException;
}
