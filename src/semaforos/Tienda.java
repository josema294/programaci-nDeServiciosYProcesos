package semaforos;

import java.util.concurrent.Semaphore;

/**
 * Clase representacion de la tienda, dadas las caracteristicas del ejercicio en
 * el que solo existe una tienda, se crea la clase como abstracta para no poder
 * ser instanciada, y se trabajara sobre esta con metodos staticos
 * 
 * 
 * @author jose
 * 
 */
public abstract class Tienda {
	
	//Limites que representan aforo del local y maximas personas que se atiende ne una caja simultaneamente
	private final static int aforo = 10;
	private final static int atencionCaja = 1;
	
	//Recaudaciones de cajas y total
	private static int recaudacionTotal;
	private static int recaudacionCaja0;
	private static int recaudacionCaja1;
	private static int recaudacionCaja2;
	private static int recaudacionCaja3;
	

	//Creacion de semaforos
	private static Semaphore enTienda = new Semaphore(aforo);
	private static Semaphore caja0 = new Semaphore(atencionCaja);
	private static Semaphore caja1 = new Semaphore(atencionCaja);
	private static Semaphore caja2 = new Semaphore(atencionCaja);
	private static Semaphore caja3 = new Semaphore(atencionCaja);
	
	

	
	/**
	 * 
	 * Metodo que representa la entrada a Tienda, recoge objeto tipo cliente y le otorga un permiso de semaphore.
	 * El metodo posteriormente llama a entraentienda() para que el cliente realice sus acciones.
	 * 
	 * @param cliente
	 * @throws InterruptedException
	 * 
	 */
	public static void customerIn(Customer cliente) throws InterruptedException {

		enTienda.acquire();

		cliente.entraenTienda();

	}
	

	/**
	 * 
	 * Metodo que representa la salida de Tienda, recoge objeto tipo cliente, Tienda libera un permiso de semaphore.
	 * El metodo posteriormente llama a saleTienda() para que el cliente realice sus acciones.
	 * 
	 * @param cliente
	 * 
	 * 
	 */

	public static void customerExit(Customer cliente) {

		enTienda.release();
		cliente.saleTienda();

	}
	
	/**
	 * 
	 * Metodo que representa la llegada a caja numero 0 de un Cliente, recoge objeto tipo Cliente 
	 * cliente intenta adquirir permiso de semaforo con tryAcquire, y retorna booleano indicando exito o no.
	 * 
	 * @param cliente
	 * @return boolean
	 * 
	 */
	
	public static boolean pasaPorCaja0(Customer cliente) {
		
		return caja0.tryAcquire(1);
		
	}
	
	
	
	/**
	 * Veer pasaPorCaja0(), replica metodo para la caja 1
	 *
	 * @see pasaPorCaja0 
	 * @param cliente
	 * @return boolean
	 */
	public static boolean pasaPorCaja1(Customer cliente) {
		
		return caja1.tryAcquire(1);
		
	}
	

	/**
	 * Veer pasaPorCaja0(), replica metodo para la caja 2
	 *
	 * @see pasaPorCaja0 
	 * @param cliente
	 * @return boolean
	 */
	
	public static boolean pasaPorCaja2(Customer cliente) {
		
		return caja2.tryAcquire(1);
		
	}
	

	/**
	 * Veer pasaPorCaja0(), replica metodo para la caja 3
	 *
	 * @see pasaPorCaja0 
	 * @param cliente
	 * @return boolean
	 */
	
	public static boolean pasaPorCaja3(Customer cliente) {
		
		return caja3.tryAcquire(1);
		
	}
	
	
	
	/**
	 * 
	 * El metodo representa la atencion en la caja0 de un cliente. La atencion toma 5s y guarda la compra realizada por el metodo comprarealizada()
	 * imprime resultado y libera semaforo de la caja0
	 * tras 2s se ejecutrana las acciones de customerExit()
	 * * 
	 * @param cliente
	 * @throws InterruptedException
	 */
	public static void atendidoCaja0(Customer cliente) throws InterruptedException {

		
		Thread.sleep(5000);
		recaudacionCaja0 = recaudacionCaja0 + cliente.compraRealizada();
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha pasado por caja 0 y ha gastado: %s ", cliente.getNombre(), cliente.getid(), cliente.compraRealizada()));
		caja0.release();
		Thread.sleep(2000);
		Tienda.customerExit(cliente);

	}
	
	/**
	 * 
	 * Equivalente de atendidoCaja0 para el caso de la caja1
	 * 
	 * @see atendidoCaja0
	 * @param cliente
	 * @throws InterruptedException
	 */

	public static void atendidoCaja1(Customer cliente) throws InterruptedException {

		
		Thread.sleep(10000);
		recaudacionCaja1 = recaudacionCaja1 + cliente.compraRealizada();
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha pasado por caja 1 y ha gastado: %s ", cliente.getNombre(), cliente.getid(), cliente.compraRealizada()));
		caja1.release();
		Thread.sleep(2000);
		Tienda.customerExit(cliente);

	}
	
	
	/**
	 * 
	 * Equivalente de atendidoCaja0 para el caso de la caja2
	 * 
	 * @see atendidoCaja0
	 * @param cliente
	 * @throws InterruptedException
	 */

	public static void atendidoCaja2(Customer cliente) throws InterruptedException {

		
		Thread.sleep(10000);
		recaudacionCaja2 = recaudacionCaja2 + cliente.compraRealizada();
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha pasado por caja 2 y ha gastado: %s ", cliente.getNombre(), cliente.getid(), cliente.compraRealizada()));
		caja2.release();
		Thread.sleep(2000);
		Tienda.customerExit(cliente);

	}
	
	
	/**
	 * 
	 * Equivalente de atendidoCaja0 para el caso de la caja3
	 * 
	 * @see atendidoCaja0
	 * @param cliente
	 * @throws InterruptedException
	 */

	public static void atendidoCaja3(Customer cliente) throws InterruptedException {

		
		Thread.sleep(10000);
		recaudacionCaja3 = recaudacionCaja3 + cliente.compraRealizada();
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha pasado por caja 3 y ha gastado: %s ", cliente.getNombre(), cliente.getid(), cliente.compraRealizada()));
		caja3.release();
		Thread.sleep(2000);
		Tienda.customerExit(cliente);

	}
	
	
	
	/**
	 * 
	 * Suma las recaudacuiones de todas las cajas
	 * 
	 * 
	 * @return int
	 *
	 */

	public static int getRecaudacionTotal() {
		recaudacionTotal = recaudacionCaja0 + recaudacionCaja1 + recaudacionCaja2 + recaudacionCaja3;
		return recaudacionTotal;
	}

	/**
	 * 
	 * Recaudacion de caja0
	 *  
	 * @return int
	 *
	 */


	public static int getRecaudacionCaja0() {
		return recaudacionCaja0;
	}


	/**0
	 * 
	 * Recaudacion de caja1
	 *  
	 * @return int
	 *
	 */

	public static int getRecaudacionCaja1() {
		return recaudacionCaja1;
	}


	/**
	 * 
	 * Recaudacion de caja 2
	 *  
	 * @return int
	 *
	 */

	public static int getRecaudacionCaja2() {
		return recaudacionCaja2;
	}


	/**
	 * 
	 * Recaudacion de caja 3
	 *  
	 * @return int
	 *
	 */

	public static int getRecaudacionCaja3() {
		return recaudacionCaja3;
	}




	@Override
	public String toString() {
		return String.format("Tienda []");
	}

}
