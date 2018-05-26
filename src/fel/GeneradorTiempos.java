package fel;

import java.math.*;

import java.util.Random;

public class GeneradorTiempos {

    private static Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static int getTiempoEntreArribos(float tiempoActual, int tipo) {
        // 0 para leve, 1 para medio  y 2 para grave. 
        float numeroA = random.nextFloat();
        int tiempoEntreArribos = 0;
        int flag; // Flag para la hora pico del evento.
        float tiempoReal;
        tiempoReal = (tiempoActual / 60) % 24;
        /*  
        LEVE:	Hora pico: 7-9 hs y 20 a 22 hs
        
		[0- 0.5]--> 10 min.
		[0.51-0.85]---> 20 min.
		[0.86- 1] ---> 30 min
        
                Horario normal:
        
		[0- 0.3]--> 20 min.
		[0.31- 70]--> 30 min
		[0.71- 1]---> 40 min

         */
        if (tipo == 0) {
            if ((tiempoReal >= 7 && tiempoReal <= 9) || (tiempoReal >= 20 && tiempoReal <= 22)) {
                flag = 1;
            } else {
                flag = 2;
            }
            switch (flag) {
                case 1:
                    if (numeroA <= 0.5) {
                        tiempoEntreArribos = 10;

                    } else if (numeroA > 0.5 && numeroA <= 0.85) {
                        tiempoEntreArribos = 20;

                    } else {
                        tiempoEntreArribos = 30;
                    }

                    break;
                case 2:
                    if (numeroA <= 0.3) {
                        tiempoEntreArribos = 20;
                    } else if (numeroA > 0.3 && numeroA < 0.7) {
                        tiempoEntreArribos = 30;
                    } else {
                        tiempoEntreArribos = 40;
                    }

                    break;

            }
            /*
        MEDIO:	Hora pico: 7-9 hs y 20 a 22 hs
		[0 - 0.35]--> 40 min.
		[0.36 - 1]---> 50 min
            
                Horario normal:
		[0- 0.25]--> 60 min.
		[0.26- 1]--> 70 min

		
        
             */
        } else if (tipo == 1) {

            if ((tiempoReal >= 7 && tiempoReal <= 9) || (tiempoReal >= 20 && tiempoReal <= 22)) {
                flag = 1;
            } else {
                flag = 2;
            }
            switch (flag) {
                case 1:
                    if (numeroA <= 0.35) {
                        tiempoEntreArribos = 40;

                    } else if (numeroA > 0.35) {
                        tiempoEntreArribos = 50;
                    }

                    break;

                case 2:
                    if (numeroA <= 0.25) {
                        tiempoEntreArribos = 60;
                    } else if (numeroA > 0.25) {
                        tiempoEntreArribos = 70;
                    }

                    break;

            }

        } /*
        GRAVE:	Hora pico: 7-9 hs y 20 a 22 hs
		[0 - 0.4]--> 60 min.
		[0.41 - 1]---> 90 min.
                
        
                Horario normal:
		[0 - 0.5]--> 120 min.
		[0.51 - 1]--> 180 min

         */ else {
            if ((tiempoReal >= 7 && tiempoReal <= 9) || (tiempoReal >= 20 && tiempoReal <= 22)) {
                flag = 1;
            } else {
                flag = 2;
            }
            switch (flag) {
                case 1:
                    if (numeroA <= 0.4) {
                        tiempoEntreArribos = 60;

                    } else if (numeroA > 0.4) {
                        tiempoEntreArribos = 90;
                    }

                    break;

                case 2:
                    if (numeroA <= 0.5) {
                        tiempoEntreArribos = 120;
                    } else if (numeroA > 0.5) {
                        tiempoEntreArribos = 180;
                    }
                    break;

            }

        }

        return tiempoEntreArribos;
    }

    public static double getTiempoDuracionServicio(int tipo) {
        // 0 para leve, 1 para medio  y 2 para grave. 

        double tiempoServicio = 0;
        // Variables Exponencial //  
        float r = random.nextFloat();


        /*
        Leve Exponencial μ = 30.
        
        
        Medio Uniforme [10..20].
        
        
        Grave Normal (120,30).
         
         */
        switch (tipo) {
            case 0:
                tiempoServicio = ((-30) * (Math.log(1 - r)));

                //System.out.println("LEVE !!");

                break;
            case 1:
                tiempoServicio = 10 + (20 - 10) * r;
                //System.out.println(" MEDIO !!");

                break;
            case 2:
                int i = 0;
                float suma = 0;
                float z = 0;// variable Z normalizada
                double y = 0; // variable normal
                while (i < 12) {
                    suma += r;
                    r = random.nextFloat();
                    i++;
                }
                z = ((suma - 6) / 1);
                y = ((z * Math.sqrt(30)) + 120);
                tiempoServicio= y;

                // System.out.println(" GRAVE !!");
                break;
        }

        return tiempoServicio;
    }

}
