package com.controles;

import com.notificacao.Notification;
import com.notificacao.NotificationQueue;







/**
 *
 * @author Suany
 */
public abstract class ControleAplicacao extends Controle {
    
    private final NotificationQueue filaNotificacoes = new NotificationQueue();
    
    public ControleAplicacao() {
    }
    
    /**
     * Contrutor responsável por passar para a sua super classe o pai desse
     * <strong>Controle</strong>.
     *
     * @param pai <strong>Controle</strong> que será o pai.
     */
    public ControleAplicacao(Controle pai) {
        super(pai);
    }     

    public void addNotificacaoFila(Notification notificacao) {
        filaNotificacoes.add(notificacao);
    }
    
    @Override
    protected void cleanUp() {
        filaNotificacoes.limparFila();
        super.cleanUp();
    }

}
