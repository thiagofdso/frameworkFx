/**
 * <b>Notification.java</b>: Fornece os recursos de notificação para qualquer
 * objeto <code>javax.swing.JFrame</code> ou <code>javax.swing.JWindow</code>.
 */
package com.notificacao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

/**
 * Fornece os recursos de notificação para qualquer objeto
 * <code>javax.swing.JFrame</code> ou <code>javax.swing.JWindow</code>. A fim de
 * alcançar um efeito melhor, é altamente recomendável remover a borda da janela
 * fornecida pelo <code>javax.swing.JFrame</code>. Você não precisa se preocupar
 * com isso quando se utiliza <code>javax.swing.JWindow</code>, uma vez que ela
 * não tem bordas. Verifique o seguinte exemplo:
 * @code
 * JWindow window = new JWindow();
 * Notification note = new Notification(window, WindowPosition.BOTTOMRIGHT, 25, 25, 1000);
 * NotificationQueue queue = new NotificationQueue();
 * queue.add(note);
 * @endcode
 *
 * @author Paulo Roberto Massa Cereda
 * @version 1.3
 * @since 1.3
 */
public class Notification {

    private final String mensagem;
    private final double duration;
    private final BooleanProperty isRunning = new SimpleBooleanProperty(this, "isRunning", false);

    public Notification(String mensagem, double duration) {
        this.mensagem = mensagem;
        this.duration = duration;
    }

    public synchronized void animate() {
        NotificacaoApp.isRunning.set(true);
        isRunning.bind(NotificacaoApp.isRunning);
        NotificacaoApp.message.set(mensagem);
        NotificacaoApp.duration.set(duration);
        Platform.runLater(() -> {
            try {
                new NotificacaoApp().start(new Stage());
            } catch (Exception ex) {
                NotificacaoApp.isRunning.set(false);
                Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public boolean isRunning() {
        return isRunning.get();
    }

}
