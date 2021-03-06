package com.validadores;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.validation.ConstraintViolation;
import com.utilidades.FabricaValidadores;

/**
 * Classe abstratta que implementa componente para validar os dados de qualquer
 * objeto que seja um <code>Bean</code> válido.
 *
 * <p>
 * A validação ocorre atraves do Bean Validations, mecanismo de validação padrão
 * do Java baseado em anotações.</p>
 *
 * @version 1.0.0
 *
 * @author Karlay
 *
 * @param <T> Objeto(bean) para ser validado.
 */
public class ValidadorBase<T> implements Validador<T> {

    private final Class<T> entityClass;

    public ValidadorBase() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Implementação do metodo que aplica o mecanismo de validação a
     * objeto(Bean).
     *
     * @param object Objeto(Bean) a ser validado.
     * @return String <i>vazia</i>, caso não exista problemas de validação. Ou *
     * retorna uma string com as mensagens de validação.
     */
    @Override
    public String validar(T object) {
        /**
         * String que irá guardar as mensagens de erro caso ocorra algum.
         */
        StringBuilder erros = new StringBuilder();

        /**
         * Verifica se o parâmetro <code>object</code> é diferente de nulo
         */
        if (object != null) {
            Set<ConstraintViolation<T>> constraintViolations = FabricaValidadores.getValidador().validate(object);
            /**
             * Verifica se algum erro foi lançado
             */
            if (!constraintViolations.isEmpty()) {
                int contador = 0;
                /**
                 * Itera sobre os erros que foram lançados
                 */
                for (ConstraintViolation<T> constraint : constraintViolations) {
                    erros = erros.append(constraint.getMessage());
                    contador++;
                    if (contador < constraintViolations.size()) {
                        erros = erros.append("\n");
                    }
                }
            }
        }
        return erros.toString();
    }

    @Override
    public Map<String, String> getErros(T object) {
        Map<String, String> erros = new HashMap<>();
        /**
         * Verifica se o parâmetro <code>object</code> e diferente de nulo
         */
        if (object != null) {
            /**
             * String que irá guardar as mensagens de erro caso ocorra algum.
             */
            Set<ConstraintViolation<T>> constraintViolations = FabricaValidadores.getValidador().validate(object);
            /**
             * Verifica se algum erro foi lançado
             */
            if (!constraintViolations.isEmpty()) {
                int contador = 0;
                /**
                 * Itera sobre os erros que foram lançados
                 */
                for (ConstraintViolation<T> constraint : constraintViolations) {
                    contador++;
                    //System.out.println(constraint.getPropertyPath().toString());
                    String nome = constraint.getPropertyPath().toString();
                    if (nome != null && !nome.isEmpty()) {
                        erros.put(nome, constraint.getMessage());
                    } else {
                        erros.put(contador + "", constraint.getMessage());
                    }

                }

            }
        }
        return erros;
    }

    @Override
    public boolean isValidar(T object) {
        /**
         * Verifica se o parâmetro <code>object</code> e diferente de nulo
         */
        if (object != null) {
            /**
             * String que irá guardar as mensagens de erro caso ocorra algum.
             */
            StringBuilder erros = new StringBuilder();
            Set<ConstraintViolation<T>> constraintViolations = FabricaValidadores.getValidador().validate(object);
            /**
             * Verifica se algum erro foi lançado
             */
            if (!constraintViolations.isEmpty()) {
                int contador = 0;
                /**
                 * Itera sobre os erros que foram lançados
                 */
                for (ConstraintViolation<T> constraint : constraintViolations) {
                    erros = erros.append(constraint.getMessage());
                    contador++;
                    if (contador < constraintViolations.size()) {
                        erros = erros.append("\n");
                    }
                }
                ImageIcon icon = new ImageIcon(getClass().getResource("/com/brassoftware/braeducaswing/imagens/perigo.png"));
                if (constraintViolations.size() == 1) {
//  implementar                    
//                    TaskDialogs.build(null, "<html><b>Atenção! Existe " + constraintViolations.size() + " erro no registro " + entityClass.getSimpleName() + "</b></html>", erros.toString()).title("Atenção").icon(icon).inform();
                } else {
//                    TaskDialogs.build(null, "<html><b>Atenção! Existem " + constraintViolations.size() + " erros no registro " + entityClass.getSimpleName() + "</b></html>", erros.toString()).title("Atenção").icon(icon).inform();
                }
                return false;
            }
        }
        return true;
    }

}
