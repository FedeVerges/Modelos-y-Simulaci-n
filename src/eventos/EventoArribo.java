package eventos;

import fel.Fel; 
import fel.GeneradorTiempos;
import fel.Queue;
import fel.Selector;
import hospital.Servidor;
import java.util.ArrayList;

public class EventoArribo extends Evento {

    public EventoArribo(float tiempo, int tipoArribo) {
        super(0, tiempo, new Item(Item.getCantidadItems() + 1, tiempo, tipoArribo), tipoArribo);
        Item.setCantidadItems(Item.getCantidadItems() + 1);
        switch (tipoArribo) {
            case 0:
                Item.setCantidadLeves(Item.getCantidadLeves() + 1); 
                break;
            case 1:
                Item.setCantidadMedios(Item.getCantidadMedios() + 1);
                break;
            case 2:
                Item.setCantidadGraves(Item.getCantidadGraves() + 1);
                break;
            default:
                break;
        }

    }

    @Override
    public void planificarEvento(ArrayList<Selector> selector) {
        //planifico el evento de arribo

        float nuevoTiempoA, nuevoTiempoS;
        nuevoTiempoS = (float) GeneradorTiempos.getTiempoDuracionServicio(this.getTransito());
        nuevoTiempoA = GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(), this.getTransito());
        Item nuevoItem = new Item(Item.getCantidadItems(), this.getTiempo() + nuevoTiempoA,this.getTransito());
        Fel fel = Fel.getFel();
        Selector medico = new Selector();
        
        medico= medico.ServidorDesocupado(selector, this.getTransito());
        // El metodo Desocuapdo retorna NULL si estan todos ocupados. Debera colocarlo en cola  
        
        if (medico.getServidor().isEstado() == true) { // todos los servidores estan ocupados. 
            
            //System.out.println(medico.getServidor().getId());// se elige la cola mas corta. 
            
            medico.getColaAsociada().insertarCola(this.getItem()); // inserto en la cola
            
            
           // System.out.println(medico.getColaAsociada().getCantidadItems());

            

            // tiempo de incio en cola // 
            this.getItem().setTiempoArribo(this.getTiempo());
            // System.out.println("\t En cola!!");

        } else {
            // Planificar Salida 

            this.getItem().setTiempoDuracionServicio(nuevoTiempoS);
            this.getItem().setMedico(medico.getServidor().getId());

            EventoSalida NuevaSalida = new EventoSalida(this.getTiempo() + this.getItem().getTiempoDuracionServicio(), this.getItem(), this.getTransito());

            fel.insertarFel(NuevaSalida);

            // Coleccion de Estadisticas //
            
            medico.getServidor().setEstado(true);
           // System.out.println(medico.getServidor().getId());
            

            medico.getServidor().setTiempoOcioso(this.getTiempo() - medico.getServidor().getTiempoInicioOcio());

        }
        EventoArribo NuevoArribo = new EventoArribo(this.getTiempo() + nuevoTiempoA, this.getTransito());
        fel.insertarFel(NuevoArribo);
    }
}
