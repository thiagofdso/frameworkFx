/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles.acaobutton.dao;

import com.controles.acaobutton.IlegalArgumentException;
import com.dao.BaseDAO;
import com.modelos.EntidadeBase;

public class RemoverDAOUpdate<T extends EntidadeBase, D extends BaseDAO<T, Object>> implements DAOUpdate<T, D> {

    @Override
    public void update(T entidade, D dao) throws IlegalArgumentException {
        if (entidade != null) {
            if (dao != null) {
                dao.remove(entidade);
            } else {
                throw new IlegalArgumentException(new String[][]{{"DAO", "nulo"}});
            }
        } else if (dao != null) {
            throw new IlegalArgumentException(new String[][]{{"Entidade" ,"nula"}});
        }else{
            throw new IlegalArgumentException(new String[][]{{"Entidade"," nula"},{"DAO", "nulo"}});
        }
    }

}
