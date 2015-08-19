/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controles.acaobutton;

import com.controles.acaobutton.dao.AdicionarEditarDAOUpdate;
import com.controles.acaobutton.dao.DAOUpdate;
import com.controles.acaobutton.dao.RemoverDAOUpdate;
import com.controles.acaobutton.view.AdicionarViewUpdate;
import com.controles.acaobutton.view.EditarViewUpdate;
import com.controles.acaobutton.view.RemoverViewUpdate;
import com.controles.acaobutton.view.ViewUpdate;
import com.dao.BaseDAO;
import com.modelos.EntidadeBase;
import com.modelos.properties.PropertyBase;
import javafx.collections.ObservableList;

/**
 *
 * @author Thiago Fernandes
 * @email thiagofdso.ufpa@gmail.com
 */
public enum EnumButton {
    ADICIONAR(new AdicionarEditarDAOUpdate(),new AdicionarViewUpdate()),
    EDITAR(new AdicionarEditarDAOUpdate(),new EditarViewUpdate()),
    REMOVER(new RemoverDAOUpdate(),new RemoverViewUpdate());
    
    private final DAOUpdate daoUpdate;
    private final ViewUpdate viewUpdate;
    EnumButton(DAOUpdate daoUpdate,ViewUpdate viewUpdate){
        this.daoUpdate = daoUpdate;
        this.viewUpdate = viewUpdate;
    }
    public void atualizaBanco(EntidadeBase entidade,BaseDAO dao) throws IlegalArgumentException{
        daoUpdate.update(entidade, dao);
    }
    public void atualizaTela(ObservableList listaProperties,PropertyBase propriedade,Integer index) throws IlegalArgumentException{
        viewUpdate.update(listaProperties,propriedade,index);
    }
    public void update(BaseDAO dao,ObservableList listaProperties,PropertyBase propriedade,Integer index) throws IlegalArgumentException{
        daoUpdate.update(propriedade.getEntidade(), dao);
        viewUpdate.update(listaProperties,propriedade,index);
    }
}
