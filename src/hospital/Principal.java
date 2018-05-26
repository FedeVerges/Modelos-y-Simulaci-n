package hospital;

import fel.Queue;
import fel.Fel;
import eventos.Evento;
import eventos.EventoArribo;
import eventos.EventoFinSimulacion;
import eventos.Item;
import fel.Selector;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Principal {

    public static void main(String[] args) {
        Evento actual;
        float tiempoSimulacion = 0;
        int cantidadSelectores = 0;
        int cantidadCorridas = 40;
        int ejecucion = 0;

        Fel fel = Fel.getFel();//Creo la lista de eventos futuros

        
        
        

        while (ejecucion < cantidadCorridas) {
            // METODOS PARA LIMPIAR LAS VARIABLES // 
            tiempoSimulacion = 0;
            cantidadSelectores = 0;
            Item.Init();
            fel.LimpiarFel();

            // Selector // 
            ArrayList<Selector> selector = new ArrayList();// 0-1: leve, 2 Medio y 3-4 Grave

            for (cantidadSelectores = 0; cantidadSelectores < 5; cantidadSelectores++) {
                selector.add(new Selector());
                selector.get(cantidadSelectores).getServidor().setId(cantidadSelectores);
                selector.get(cantidadSelectores).getColaAsociada().setId(cantidadSelectores);

            }

            EventoFinSimulacion ef = new EventoFinSimulacion(10080); // Evento fin de simulacion

            // Creacion de los primeros eventos de arribo //
            Evento ActualLeve = new EventoArribo(0, 0);// Leve 

            Evento ActualMedio = new EventoArribo(0, 1); // Medio
            Evento ActualGrave = new EventoArribo(0, 2); // Grave

            fel.insertarFel(ef);
            fel.insertarFel(ActualGrave);
            fel.insertarFel(ActualMedio);
            fel.insertarFel(ActualLeve);

            /* System.out.println("FEL inicial: \n");
            fel.mostrarFel();
            System.out.println("------------------------------");*/
            while (tiempoSimulacion < ef.getTiempo()) {

                actual = fel.suprimirFel();

                tiempoSimulacion = actual.getTiempo();

                // System.out.println("\n EVENTO PROCESADO " + " Tiempo simulacion: " + tiempoSimulacion + " Tipo de Evento:  " + actual.getTipo() + actual.getTransito() + " Tiempo de evento: " + actual.getTiempo() + " Item numero: " + actual.getItem().getNumero());
                actual.planificarEvento(selector);

                // System.out.println("\n");
                // fel.mostrarFel();
                // System.out.println("\n");
                //System.out.println(Item.getCantidadItems());
            }
            ejecucion++;
            // Coleccion de valores Estadisticos Finales //

            Estadisticas.calcularEstadisticas(Item.getTiempoTransito(), selector, tiempoSimulacion, Item.getCantidadItems());
            
            
        }
        Estadisticas.CalcularDatosFinales(cantidadCorridas,tiempoSimulacion);
        
        //System.out.println(mediatiempoEsperaLeve.toString());

    }

}
