package deposito_modeloPC;

public class Principal {
	
	
	
	public static void main(String[] args)  {
		
		//Instanciamos clases del programa, e iniciamos hilos.
		Deposito dep = new Deposito(1000);
		Llenador llen = new Llenador(dep);
		Vaciador vac = new Vaciador(dep);
		Vista vis = new Vista(dep);
		InputListener in = new InputListener();
		
		in.start();
		llen.start();
		vac.start();
		
		
		
		
		//Bucle ejecucion del programa.
		while (in.isEjecucion()) {
			
						
			vis.imagen();		
		}

		
		System.exit(0);
		
		
		
		
	}
	
	
	
	
}
