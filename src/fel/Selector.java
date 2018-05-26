package fel;

import hospital.Servidor;
import java.util.ArrayList;

/**
 *
 * @author federico
 */
public class Selector {

    private Servidor servidor;
    private Queue colaAsociada;
   

    public Selector() {
        servidor = new Servidor();
        colaAsociada = new Queue();
    }

    public Servidor getServidor() {

        return servidor;
    }

    public Queue getColaAsociada() {
        return colaAsociada;
    }
    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public void setColaAsociada(Queue colaAsociada) {
        this.colaAsociada = colaAsociada;
    }

    public Selector MenorCola(ArrayList<Selector> s, int transito) {
        int i;
        Selector menor = null; // El conjunto Servidor-cola donde la cola sea menor.
        switch (transito) {
            case 0: // Caso Leve va a recorrer de las posicion 0 a la posicion 1. Por haber dos medicos.
                for (i = 0; i <= 1; i++) {
                    menor = s.get(0);
                    if (s.get(i).colaAsociada.getCantidadItems() < menor.colaAsociada.getCantidadItems()) {
                        menor = s.get(i);
                    }

                }
                return menor;

            case 1:  // Caso Medio solamente se fija en la posicion 2.
                menor = s.get(2);
                return menor;

            default:// Caso Grave debe recorrer la posicion 3 a la 4. por haber 2 medicos.
                for (i = 3; i <= 4; i++) {
                    menor = s.get(3);
                    if (s.get(i).colaAsociada.getCantidadItems() < menor.colaAsociada.getCantidadItems()) {
                        menor = s.get(i);
                    }
                }
                return menor;
        }
    }

    public boolean ColaOcupada(ArrayList<Selector> s, int transito) {
        boolean resultado = false;
        int i;
        switch (transito) {
            case 0: // Caso Leve va a recorrer de las posicion 0 a la posicion 1. Por haber dos medicos.
                for (i = 0; i <= 1; i++) {
                    if (s.get(i).colaAsociada.HayCola() == true) {
                        resultado = true;
                    }
                }
                break;

            case 1:  // Caso Medio solamente sse fija en la posicion 2.
                if (s.get(2).colaAsociada.HayCola() == true) {
                    resultado = true;
                }
                break;

            default:// Caso Grave debe recorrer la posicion 3 a la 4. por haber 2 medicos.
                for (i = 3; i <= 4; i++) {
                    if (s.get(i).colaAsociada.HayCola() == true) {
                        resultado = true;
                    }
                }
                break;
        }
        return resultado;
    }

    public Selector ServidorDesocupado(ArrayList<Selector> s, int transito) {
        Selector desocupado = null;
        int i;
        switch (transito) {
            case 0: // Caso Leve va a recorrer de las posicion 0 a la posicion 1. Por haber dos medicos.
                for (i = 0; i <= 1; i++) {
                    if (s.get(i).servidor.isEstado() == false) {
                        desocupado = s.get(i);
                        return desocupado;
                    }
                }
                desocupado = MenorCola(s, transito);
                break;

            case 1:  // Caso Medio solamente sse fija en la posicion 2.
                if (s.get(2).servidor.isEstado() == false) {
                    desocupado = s.get(2);
                    return desocupado;
                }
                break;

            default:// Caso Grave debe recorrer la posicion 3 a la 4. por haber 2 medicos.
                for (i = 3; i <= 4; i++) {
                    if (s.get(i).servidor.isEstado() == false) {
                        desocupado = s.get(i);
                        return desocupado;
                    }

                }
                desocupado = MenorCola(s, transito);
                break;
        }/*
        System.out.println("todos los medicos estan ocupados");
        System.out.println(desocupado);
*/
        return desocupado;
    }
    
}
