import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//Hora inicial
		getHora();
		Instant nanoSegInicial = Instant.now();
		
		//Crear "resoluci�n"
		EncontrarInversionesImplementacion res = new EncontrarInversionesImplementacion();

		//Inicializar inversiones ejemplo tpo - Hardcodeo

		/* Otro ejemplo   
		ArrayList<Inversion> inversiones = new ArrayList<Inversion>();
		Inversion inversion = new Inversion();
		inversion.nombreInversion = "A";
		inversion.montoMinimoParaInvertir= 10;
	    inversion.porcentajeRentabilidad = 10;
		inversion.riesgo = 15;
		inversiones.add(inversion);

		inversion = new Inversion();
		inversion.nombreInversion = "B";
		inversion.montoMinimoParaInvertir= 20;
		inversion.porcentajeRentabilidad = 7;
		inversion.riesgo = 20;
		inversiones.add(inversion);
		
		inversion = new Inversion();
		inversion.nombreInversion = "C";
		inversion.montoMinimoParaInvertir= 30;
		inversion.porcentajeRentabilidad = 15;
		inversion.riesgo = 18;
		inversiones.add(inversion);

		//Entrada
		double montoAInvertir = 40;
		int valorIncremental = 10;
*/
		//Inicializar inversiones ejemplo tpo - Hardcodeo		
		ArrayList<Inversion> inversiones = new ArrayList<Inversion>();
		Inversion inversion = new Inversion();
		inversion.nombreInversion = "A";
		inversion.montoMinimoParaInvertir = 100000;
	    inversion.porcentajeRentabilidad = 50;
		inversion.riesgo = 5;
		inversiones.add(inversion);

		inversion = new Inversion();
		inversion.nombreInversion = "B";
		inversion.montoMinimoParaInvertir = 100000;
		inversion.porcentajeRentabilidad = 50;
		inversion.riesgo = 4;
		inversiones.add(inversion);
		
		inversion = new Inversion();
		inversion.nombreInversion = "C";
		inversion.montoMinimoParaInvertir = 200000;
		inversion.porcentajeRentabilidad = 25;
		inversion.riesgo = 3;
		inversiones.add(inversion);
		
		inversion = new Inversion();
		inversion.nombreInversion = "D";
		inversion.montoMinimoParaInvertir = 300000;
		inversion.porcentajeRentabilidad = 50;
		inversion.riesgo = 3;
		inversiones.add(inversion);
		
		//Entrada
		double montoAInvertir = 400000;
		int valorIncremental = 100000;

	    //Llamar al m�todo obtenerInversiones
		ArrayList<ArrayList<InversionSeleccionada>> combinaciones = res.obtenerInversiones(inversiones, montoAInvertir, valorIncremental);

        //Imprimir los resultados
		imprimirResultado(combinaciones, montoAInvertir);
        
		//Hora final
		getHora();
		Instant nanoSegFinal = Instant.now();
		//Tiempo en ejecutarse
		Duration duracion = Duration.between(nanoSegInicial, nanoSegFinal);
		System.out.println("El algoritmo tard� " + duracion.toHours() + " horas en ejecutarse.");
		System.out.println("El algoritmo tard� " + duracion.toMinutes() + " minutos en ejecutarse.");
		System.out.println("El algoritmo tard� " + duracion.toSeconds() + " segundos en ejecutarse.");
		System.out.println("El algoritmo tard� " + duracion.toNanos() + " nano segundos en ejecutarse.");
	}
	
	private static void imprimirResultado(ArrayList<ArrayList<InversionSeleccionada>> res, double montoTotalInvertido) {
		for (int i=0; i < res.size();i++) {
			ArrayList<InversionSeleccionada> resIndividual = res.get(i);
			System.out.println("Inversion "+(i+1));
			long rentabilidadTotal = 0;
	        long riesgoPromedio = 0;
	        for (InversionSeleccionada invRes : resIndividual) {
                rentabilidadTotal += invRes.rentabilidadObtenida;
                double porcentajeInvertido = invRes.montoAInvertir / montoTotalInvertido;
                riesgoPromedio += invRes.riesgoPromedio * porcentajeInvertido;
            }
			for (int j=0; j < resIndividual.size();j++) {
			     InversionSeleccionada invRes = resIndividual.get(j);
			     System.out.println("Inv "+ (j+1) + " - Nombre "+invRes.nombreInversionSeleccionada+ " Monto "+
			     invRes.montoAInvertir+ " Rentabilidad "+invRes.rentabilidadObtenida+" Riesgo " + invRes.riesgoPromedio);
			}
			System.out.println("Rentabilidad Total: " + rentabilidadTotal);
            System.out.println("Riesgo Promedio: " + riesgoPromedio);
            System.out.println();
		}
	}
	
	private static void getHora() {
		System.out.println("La hora actual es:");
		//HORA
		int hora = LocalTime.now().getHour();
		System.out.print(hora + "hs - ");
		//MINUTOS
		int minutos = LocalTime.now().getMinute();
		System.out.print(minutos + " min - ");
		//SEGUNDOS
		int segundos = LocalTime.now().getSecond();
		System.out.print(segundos + " seg - ");
		//NANO SEGUNDOS
		int nanoseg = LocalTime.now().getNano();
		System.out.println(nanoseg + " nano seg");
	}
}