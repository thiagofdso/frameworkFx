/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelos;

/**
 *
 * @author Casa
 */
public enum EnumTipoAcao {
    SALVAR, EXCLUIR, ATUALIZAR,EDITAR;

    @Override
    public String toString() {
        switch (this) {
            case SALVAR:
                return "Salvar";
            case EXCLUIR:
                return "Excluir";
            case ATUALIZAR:
                return "Atualizar";
        }
        return "Erro";
    }
}
