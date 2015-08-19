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

public class EditarViewUpdate<P extends PropertyBase<T>, T extends EntidadeBase> implements ViewUpdate<P, T> {

    @Override
    public void update(ObservableList<P> listaProperties, P propriedade, Integer index) throws IlegalArgumentException {
        if (propriedade != null) {
            if (listaProperties != null) {
                if (index != null && index>-1 &&  index < listaProperties.size()) {
                    listaProperties.get(index).setEntidade(propriedade.getEntidade());
                }else{
                    throw new IlegalArgumentException(new String[][]{{"Posição da lista","inválida"}});
                }
            } else {
                throw new IlegalArgumentException(new String[][]{{"Lista","nula"}});
            }
        } else if (listaProperties != null) {
            throw new IlegalArgumentException(new String[][]{{"Propriedade","nula"}});
        } else {
            throw new IlegalArgumentException(new String[][]{{"Propriedade","nula"},{"Lista","nula"}});
        }
    }

}
