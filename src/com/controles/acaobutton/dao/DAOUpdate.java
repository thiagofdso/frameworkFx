/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles.acaobutton.dao;

import com.controles.acaobutton.IlegalArgumentException;
import com.dao.BaseDAO;
import com.modelos.EntidadeBase;

/**
 *
 * @author Thiago
 * @param <T>
 * @param <D>
 */
public interface DAOUpdate <T extends EntidadeBase,D extends BaseDAO<T,Object>>{
    /**
     * Atualiza registro no banco de dados.
     * @param entidade 
     * @param dao 
     * @throws com.controles.acaobutton.IlegalArgumentException 
     */
    public void update(T entidade,D dao) throws IlegalArgumentException;
}
