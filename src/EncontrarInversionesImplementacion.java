import java.util.ArrayList;

public class EncontrarInversionesImplementacion implements EncontrarInversiones {

	public ArrayList<ArrayList<InversionSeleccionada>> obtenerInversiones(ArrayList<Inversion> inversiones, double montoAInvertir, int valorIncremental) {
		ArrayList<ArrayList<InversionSeleccionada>> mejoresCombinaciones  = new ArrayList<>();
		//Ordenar las inversiones por monto minimo de inversion usando Merge Sort
        inversiones = mergeSort(inversiones);
		double[] rentabilidadMaxima = {0};
		backtracking(inversiones, 0, montoAInvertir, valorIncremental, new ArrayList<>(), mejoresCombinaciones, rentabilidadMaxima);
        return mejoresCombinaciones;
    }

	private void backtracking(ArrayList<Inversion> inversiones, int index, double montoRestante, int valorIncremental, ArrayList<InversionSeleccionada> combinacionActual,
	ArrayList<ArrayList<InversionSeleccionada>> mejoresCombinaciones,  double[] rentabilidadMaxima) {
		if (montoRestante < 0) {
            return;
        }

		if (index == inversiones.size()) {
			if (montoRestante >= 0) {
				double rentabilidad = calcularRentabilidad(combinacionActual);
				if (rentabilidad > rentabilidadMaxima[0]) {
	                rentabilidadMaxima[0] = rentabilidad;
	                mejoresCombinaciones.clear();
	                mejoresCombinaciones.add(new ArrayList<>(combinacionActual));
	            } else if (rentabilidad == rentabilidadMaxima[0]) {
	                mejoresCombinaciones.add(new ArrayList<>(combinacionActual));
	            } 
			}
			return;
		}

		Inversion inv = inversiones.get(index);
		for (double montoInvertido = inv.montoMinimoParaInvertir; montoInvertido <= montoRestante; montoInvertido += valorIncremental) {
			if (montoRestante - montoInvertido >= 0) {
				InversionSeleccionada nuevaInversionSeleccionada = new InversionSeleccionada();
				nuevaInversionSeleccionada.nombreInversionSeleccionada = inv.nombreInversion;
				nuevaInversionSeleccionada.montoAInvertir = montoInvertido;
				double porcentajeRentabilidad = (double) inv.porcentajeRentabilidad; 
				nuevaInversionSeleccionada.rentabilidadObtenida =  montoInvertido * (porcentajeRentabilidad / 100);
				nuevaInversionSeleccionada.riesgoPromedio = inv.riesgo;
				combinacionActual.add(nuevaInversionSeleccionada);
				backtracking(inversiones, index + 1, montoRestante - montoInvertido, valorIncremental, combinacionActual, mejoresCombinaciones, rentabilidadMaxima);
				combinacionActual.remove(combinacionActual.size() - 1);
			} 
		}

		//Poda: verificar si la siguiente inversion puede ser considerada
		if (index + 1 < inversiones.size() && montoRestante - inversiones.get(index + 1).montoMinimoParaInvertir < 0) {
			double rentabilidad = calcularRentabilidad(combinacionActual);
			if (rentabilidad > rentabilidadMaxima[0]) {
				rentabilidadMaxima[0] = rentabilidad;
				mejoresCombinaciones.clear();
				mejoresCombinaciones.add(new ArrayList<>(combinacionActual));
				} else if (rentabilidad == rentabilidadMaxima[0]) {
					mejoresCombinaciones.add(new ArrayList<>(combinacionActual));
				}
			return;
		}
		
        //Llamar a la siguiente inversion sin seleccionar la actual
		backtracking(inversiones, index + 1, montoRestante, valorIncremental, combinacionActual, mejoresCombinaciones, rentabilidadMaxima);
	}

	private ArrayList<Inversion> mergeSort(ArrayList<Inversion> inversiones) {
        if (inversiones.size() <= 1) {
            return inversiones;
        }
        int medio = inversiones.size() / 2;
        ArrayList<Inversion> izq = new ArrayList<>(inversiones.subList(0, medio));
        ArrayList<Inversion> der = new ArrayList<>(inversiones.subList(medio, inversiones.size()));
        return merge(mergeSort(izq), mergeSort(der));
    }

	private ArrayList<Inversion> merge(ArrayList<Inversion> izq, ArrayList<Inversion> der) {
        ArrayList<Inversion> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < izq.size() && j < der.size()) {
            if (izq.get(i).montoMinimoParaInvertir <= der.get(j).montoMinimoParaInvertir) {
                merged.add(izq.get(i++));
            } else {
                merged.add(der.get(j++));
            }
        }
        while (i < izq.size()) {
            merged.add(izq.get(i++));
        }
        while (j < der.size()) {
            merged.add(der.get(j++));
        }
        return merged;
    }

	private double calcularRentabilidad(ArrayList<InversionSeleccionada> combinacion) {
        double rentabilidad = 0;
        for (InversionSeleccionada inv : combinacion) {
            rentabilidad += inv.rentabilidadObtenida;
        }
        return rentabilidad;
    }
}