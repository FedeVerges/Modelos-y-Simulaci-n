package eventos;

import fel.Queue;
import fel.Selector;
import hospital.Servidor;
import java.util.ArrayList;

public abstract class Evento {

    private int tipo;//0: Arribo - 1:Fin de Servicio - 2: Fin de Simulacion
    private float tiempo;
    private Item item;
    private int transito; // 0 para leve, 1 para medio  y 2 para grave.

    public Evento(int tipo, float tiempo, Item item, int transito) {
        this.tipo = tipo;
        this.tiempo = tiempo;
        this.item = item;
        this.transito = transito;

    }

    public int getTransito() {
        return transito;
    }
    

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Implementa la planiificaci�n de eventos.
     */
    public abstract void planificarEvento(ArrayList <Selector> selector);
    
}
