package deposito_modeloPC;

/**
 * Clase que contiene la logica para mostrar la interfaz de consola en tiempo real
 * 
 * @author jose
 *
 */
public  class Vista {
	
	private Deposito dep;
	private int fps = 60;
	
	
	public Vista(Deposito dep) {
		this.dep = dep;
	}
	
	public void imagen () {
		
		
			
			System.out.print("\n"
					+ "\n"
					+ "==========================================================================================									\n"
					+ "			\n"
					+ "			==============================\n"
					+ "			Porcentaje llenado: "+dep.getCapacidad()/10+"%\n"
					+ "			==============================\n"
					+ "			Ciclo actual: "+dep.getCiclo()+ "\n"
					+ "			==============================\n"
					+ "			Entrada caudal: "+ dep.getCaudalEntrada()+"\n"
					+ "			==============================\n"
					+ "			Salida caudal: "+ dep.getCaudalSalida()+"\n"
					+ "			==============================\n"
					+ "			Caudal Neto: "+ dep.getCaudalNeto()+" \n"
					+ "			==============================\n"
					+ "			Ciclos Realizados: "+dep.getNumLlenados()+" \n"
					+ "			==============================\n"
					+ "			\n"
					+ "			Para salir de la simulacion 	pulse: z e intro\n"
					+ "			\n"
					+ "==========================================================================================\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "");
				
			
		
		
		
		try {
			Thread.sleep((1000/fps));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < 100; i++) {
		    System.out.println();
		}
		
	

		
		
	}

	
	
	
	
	

}
