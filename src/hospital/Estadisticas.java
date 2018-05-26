 package hospital;

import eventos.Item;
import fel.Selector;
import java.util.ArrayList;
import java.util.ListIterator;

public class Estadisticas {

    // Especialistas //
    // Arreglo de Medias //
    
    // Tiempo Medio Oscioso   
    
    private static ArrayList<Float> mediatiempoOsciosoE1 = new ArrayList();
    private static ArrayList<Float> mediatiempoOsciosoE2 = new ArrayList();
    private static ArrayList<Float> mediatiempoOsciosoE3 = new ArrayList();
    private static ArrayList<Float> mediatiempoOsciosoE4 = new ArrayList();
    private static ArrayList<Float> mediatiempoOsciosoE5 = new ArrayList();

    // Tiempo Espera Cola
    
    private static ArrayList<Float> mediatiempoEsperaLeve = new ArrayList();
    private static ArrayList<Float> mediatiempoEsperaMedio = new ArrayList();
    private static ArrayList<Float> mediatiempoEsperaGrave = new ArrayList();
    
    // Arreglo tiempo osciosoTotal // 
    private static ArrayList<Float> tiempoOscioso = new ArrayList();
    
    // Arreglo tiempo Espera total // 
    private static ArrayList<Float> tiempoEspera = new ArrayList();
    

    // Media de medias//
    private static float mediaTiempoOscioE1;
    private static float mediaTiempoOscioE2;
    private static float mediaTiempoOscioE3;
    private static float mediaTiempoOscioE4;
    private static float mediaTiempoOscioE5;

    private static float tiempoMedioTransitoLeve;
    private static float tiempoMedioTransitoMedio;
    private static float tiempoMedioTransitoGrave;

    public static void calcularEstadisticas(float tiempoTransito, ArrayList<Selector> s,
            float tiempoFinSimulacion, int cantidadItems) {

        // Tiempo ocioso de cada especialista  //
        
                
        mediatiempoOsciosoE1.add(s.get(0).getServidor().getTiempoOcioso());
        mediatiempoOsciosoE2.add(s.get(1).getServidor().getTiempoOcioso());
        mediatiempoOsciosoE3.add(s.get(2).getServidor().getTiempoOcioso());
        mediatiempoOsciosoE4.add(s.get(3).getServidor().getTiempoOcioso());
        mediatiempoOsciosoE5.add(s.get(4).getServidor().getTiempoOcioso());
        /*
        tiempoOsciosoE2 += s.get(1).getServidor().getTiempoOcioso();
        tiempoOsciosoE3 += s.get(2).getServidor().getTiempoOcioso();
        tiempoOsciosoE4 += s.get(3).getServidor().getTiempoOcioso();
        tiempoOsciosoE5 += s.get(4).getServidor().getTiempoOcioso();
*/
        // tiempo medio de espera de pacientes // 
        float tiempoMedioEsperaLeve = (Item.getTiempoEsperaColaLeve() / Item.getCantidadLeves());
        float tiempoMedioEsperaMedio = (Item.getTiempoEsperaColaMedio() / Item.getCantidadMedios());
        float tiempoMedioEsperaGrave = (Item.getTiempoEsperaColaGrave() / Item.getCantidadGraves());
        /*
        tiempoEsperaLeve += tiempoMedioEsperaLeve;
        tiempoEsperaMedio += tiempoMedioEsperaMedio;
        tiempoEsperaGrave += tiempoMedioEsperaGrave;
*/
        
        mediatiempoEsperaLeve.add(tiempoMedioEsperaLeve);
        mediatiempoEsperaMedio.add(tiempoMedioEsperaMedio);
        mediatiempoEsperaGrave.add(tiempoMedioEsperaGrave);
        
        // Tiempo medio de Transito //
        tiempoMedioTransitoLeve = Item.getTiempoTransito() / Item.getCantidadLeves();
        tiempoMedioTransitoMedio = Item.getTiempoTransito() / Item.getCantidadMedios();
        tiempoMedioTransitoGrave = Item.getTiempoTransito() / Item.getCantidadGraves();

    }

    public static void CalcularDatosFinales(int cantidadCorridas, float tiempoSimulacion) {
        int i, x;
        float suma = 0;
        // Calculo de valores finales //
        
        
        // Medias de las medias //
        
        // Tiempo oscioso de cada especialista // 
        
        ArrayList<ArrayList<Float>> arregloDatosOscio = new ArrayList();
        ArrayList<ArrayList<Float>> arregloDatosEspera = new ArrayList();
        
        //Inicializo el arreglo de los datos // 
        
        arregloDatosOscio.add(mediatiempoOsciosoE1);
        arregloDatosOscio.add(mediatiempoOsciosoE2);
        arregloDatosOscio.add(mediatiempoOsciosoE3);
        arregloDatosOscio.add(mediatiempoOsciosoE4);
        arregloDatosOscio.add(mediatiempoOsciosoE5);
        
        arregloDatosEspera.add(mediatiempoEsperaLeve);
        arregloDatosEspera.add(mediatiempoEsperaMedio);
        arregloDatosEspera.add(mediatiempoEsperaGrave);
        
        for(int j=0; j<arregloDatosOscio.size();j++){
            for(i= 0; i<arregloDatosOscio.get(j).size(); i++){                
                suma += arregloDatosOscio.get(j).get(i); // Se suman todos los tiempos de oscio de las 40 corridas. 
            }
            tiempoOscioso.add(j, suma); // Arreglo con los tiempos de oscio totales de las corridas
            suma= 0;
        }
        for(int j=0; j<arregloDatosEspera.size();j++){
            for(x= 0; x<arregloDatosEspera.get(j).size(); x++){
                System.out.println(j + " " + x );
                
                suma += arregloDatosEspera.get(j).get(x); // Se suman todos los tiempos de espera de las 40 corridas
                System.out.println(arregloDatosEspera.get(j).get(x));
            }
            
            tiempoEspera.add(j, suma); // Arreglo con los tiempos de espera totales de las corridas
            suma = 0;
        }
        
        // calculo de desviacion estandar
        
        

        System.out.println(" PORCENTAJE DE TIEMPO OSCIO ESPECIALISTAS: \n");
        System.out.println("Especialista 1: " + (((tiempoOscioso.get(0)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Especialista 2: " + (((tiempoOscioso.get(1)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Especialista 3: " + (((tiempoOscioso.get(2)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Especialista 4: " + (((tiempoOscioso.get(3)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Especialista 5: " + (((tiempoOscioso.get(4)/cantidadCorridas)/tiempoSimulacion)*100));

        // Tiempo Espera de los Pacientes // 

        System.out.println("PORCENTAJE DE TIEMPO ESPERA DE LOS PACIENTES");
        System.out.println("Paicentes Leves :" + (((tiempoEspera.get(0)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Paicentes Medio :" + (((tiempoEspera.get(1)/cantidadCorridas)/tiempoSimulacion)*100));
        System.out.println("Paicentes Grave :" + (((tiempoEspera.get(2)/cantidadCorridas)/tiempoSimulacion)*100));
        

    }


}
