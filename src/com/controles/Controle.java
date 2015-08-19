package com.controles;


import com.acao.AcaoBase;
import com.evento.EventoBase;
import com.evento.EventoBaseOuvinte;
import com.utilidades.FabricaEntidades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tarefas.TarefaBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/**
 * Classe abstrata que define uma estrutura para componentes da camada controle
 * do padrão arquitetural MVC.
 *
 * <p>
 * <code>Controle</code> é o componente intermediário entre a apresentação
 * (Visão) e os componentes de negócio (Serviços + DAO + Modelo).</p>
 *
 * <p>
 * Habilita:</p>
 * <ul>
 * <li>Definição de <code>eventos</code> e <code>ações</code> para os
 * componentes gráficos.</li>
 * <li>Apresentar mensagens de erros gerados em <code>ações</code>dos
 * componentes gráficos.</li>
 * <li>Liberar recursos do componente no encerramento da janela.</li>
 * </ul>
 *
 * @version 1.0.0
 *
 * @author Karlay
 */
public abstract class Controle implements EventHandler {
   
    /* Pai do Controle atual, caso esse objeto seja nulo o
     * controle atual é um pai.*/
    private Controle controlePai;

    /* Um Controle pode ter qualquer quantidade de sub-controles */
    private final List<Controle> subControles = new ArrayList<>();

    public List<Controle> getSubControles() {
        return subControles;
    }

    /* Guarda todas a ações registradas no controle concreto, para futuramente
     * ser invocada quando ulguma coisa ocorrer na visão */
    private final Map<Button, AcaoBase> acoes = new HashMap<>();
    
    /**
     * Guarda todos os eventos e uma lista de seus ouvintes, um evento pode ter
     * vários ouvintes,...
     */
    private final Map<Class<?>, List<EventoBaseOuvinte<?>>> ouvintesEventos = new HashMap<>();
    
    
    private final List<TarefaBase> tarefas = new ArrayList<>();
    
    public void addTarefa(TarefaBase tarefa) {        
        if(tarefa != null) {
            this.tarefas.add(tarefa);
        }
    }

    public Controle() {
    }

    /**
     * Construtor usado para construir um controle que sempre será filho de um
     * controle pai.
     *
     * @param controlePai <code>Controle</code> pai de controle.
     */
    public Controle(Controle controlePai) {
        /* Testa se o controle passado para o método não é nulo */
        if (controlePai != null) {
            // Informa quem é o controle pai
            this.controlePai = controlePai;
            // Se auto adiciona como filho no controle pai
            this.controlePai.subControles.add(getInstance());
        }
    }
    public void setControlePai(Controle controlePai){
        if(controlePai !=null){
            this.controlePai = controlePai;
            this.controlePai.subControles.add(getInstance());
        }
    }
    /**
     * Aciona o <code>EventoBaseOuvinte</code> relacionado ao
     * <code>EventoBase</code> para que o <code>ouvinte</code> trate o evento.
     *
     * @param evento referência do evento gerado
     */
    @SuppressWarnings("unchecked")
    public void disparaevento(EventoBase<?> evento) {
        boolean trava = true;
        while (controlePai != null && trava) {
            trava = false;
            controlePai.disparaevento(evento);
        }
        if (controlePai == null) {
            if (ouvintesEventos.get(evento.getClass()) != null) {
                for (EventoBaseOuvinte ouvinteEvento : ouvintesEventos.get(evento.getClass())) {
                    ouvinteEvento.notificandoEvento(evento);
                }
            }
        }
    }

    /**
     * Registra um <code>ouvinte</code> que deve ser notificado de acordo com o
     * tipo do <code>evento</code>, ou seja quando um evento é disparado quem
     * estiver registrado será notificado.
     *
     * @param classeEvento Classe do evento, geralmente uma classe concreta de
     * <code>EventoBase</code>.
     * @param ouvinteEvento Objeto que será um (<code>ouvinte</code>) do evento
     * geralmente um <code>objeto</code> que implementa a interface
     * <code>EventoBaseOuvinte</code>.
     *
     * @see EventoBase
     * @see EventoBaseOuvinte
     */
    protected void registraOuvinteEvento(Class<?> classeEvento, EventoBaseOuvinte<?> ouvinteEvento) {
        boolean trava = true;
        while (controlePai != null && trava) {
            trava = false;
            controlePai.registraOuvinteEvento(classeEvento, ouvinteEvento);
        }
        if (controlePai == null) {
            // Recebe os ouvintes do evento passado como parâmetro se houver
            // na lista de ouvintes (ouvintesEventos).
            List<EventoBaseOuvinte<?>> ouvintesParaEvento = ouvintesEventos.get(classeEvento);
            // Caso não haja ouvintes para o evento, será gerado uma lista vazia
            if (ouvintesParaEvento == null) {
                ouvintesParaEvento = new ArrayList<>();
            }
            ouvintesParaEvento.add(ouvinteEvento);
            ouvintesEventos.put(classeEvento, ouvintesParaEvento);
        }
    }

    /**
     * Método final que devolve a instância da própia classe para ser usada no
     * construtor para não haver vazamento no mesmo.
     *
     * @return Uma intância do controle a ser criado.
     */
    protected final Controle getInstance() {
        return this;
    }

    /**
     * Registra uma ação junto com o seu respectivo botão, além de fazer com que
     * o controle seja um observador desse botão;
     *
     * @param origem Botão a ser ouvido
     * @param acao Ação a ser vinculada ao seu botão de origem
     */
    protected void registraAcao(Button origem, AcaoBase acao) {
        // Testa se a origem que é um botão tem uma comando para ele

        // Registra o controle como ouvinte do botão
        origem.setOnAction(this);
        /* Guarda uma referência do comando do botão junto com uma ação, o 
         * controle está observando o botão agora e sabe que ação deve executar
         * caso esse botão seja precionado. */
        acoes.put(origem, acao);
    }

    /**
     * <p>
     * Filtra e retorna a ação correspondente na lista de ações do controle.<br>
     * Esse método auxilia o <strong>Invocador</strong> do padrão
     * <strong>Command</strong>
     * </p>
     *
     * @param e Contém informações da origem da ação
     * @return Ação correspondente que foi registrada na lista de ações
     */
    private AcaoBase getAcao(Event e) {
        Button botao = (Button) e.getSource();
        return acoes.get(botao);
    }

    /**
     * <p>
     * Quando uma ação ocorrer na visão o controle é notificado e executa a sua
     * ação correspondente que foi registrada em uma lista de ações no controle.
     * </p>
     * <p>
     * Método <strong>Invocador</strong> do padrão <strong>Command.</strong>
     * </p>
     * <p>
     * Implementação da interface <strong>ActionListener</strong> do padrão
     * <strong>Observer.</strong><br>
     * Tornando essa Classe uma observadora de Acões.
     * </p>
     *
     * @param e Contém informações da origem da ação
     */
    
    @Override
    public void handle(Event e){
        // Recupera a ação na lista de ações
        AcaoBase acao = getAcao(e);
        if (acao != null) {
            acao.actionPerformed();
        }        
    };


    /**
     * Método utilizado para liberar recursos carregados pelo
     * <code>Controle</code>.
     */
    protected void cleanUp() {
        for (TarefaBase tarefa : tarefas) {
            tarefa.finalizarTarefa();
        }
        List<Controle> cloneSubControles = new ArrayList<>(subControles);
        for (Controle subControle : cloneSubControles) {
            subControle.cleanUp();
        }
        
        if (controlePai == null && this instanceof ControleAplicacao) {
            FabricaEntidades.closeEntityManagerFactory();
        } else {
            System.err.println("Removido " + this);
            getControlePai().subControles.remove(this);
        }
    }

    public Controle getControlePai() {
        return controlePai;
    }

}
