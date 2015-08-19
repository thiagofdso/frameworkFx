/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controles.acaobutton;

/**
 * Exceção gerada quando argumento(s) for(em) inválido.
 *
 * @author Thiago Fernandes
 * @email thiagofdso.ufpa@gmail.com
 */
public class IlegalArgumentException extends Exception {

    private String[][] exceptionMessage;

    public IlegalArgumentException(String[][] exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        StringBuilder messageBuilder = new StringBuilder("");
        if (exceptionMessage != null) {
            for (String[] exceptionMessage1 : exceptionMessage) {
                messageBuilder.append("</b>").append(exceptionMessage1[0]).append("</b>  ").append(exceptionMessage1[1]).append("\n");
            }
        }
        return messageBuilder.toString();
    }

}
