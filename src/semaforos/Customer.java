package semaforos;

import java.util.Random;

/**
 * 
 * Clase customer, inplementa los metodos necesarios para ir de tiendas, clientes identificables mediante id unico gracias a la variable 
 * de clase contadorid.
 * 
 * @author jose
 * 
 *
 */

public class Customer extends Thread{
	
	private static int contadorid = 0;
	private int id;
	private String nombre;
	
	
	public void run() {
		
		try {
			
			
			do {
				

				Tienda.customerIn(this);
				System.out.println(String.format("El cliente %s esta eligiendo que comprar", this.nombre));
				Thread.sleep(2000);
				
				if (Tienda.pasaPorCaja0(this)) {

					Tienda.atendidoCaja0(this);
					break;

				}

				else if (Tienda.pasaPorCaja1(this)) {

					Tienda.atendidoCaja1(this);
					break;
				}

				else if (Tienda.pasaPorCaja2(this)) {

					Tienda.atendidoCaja2(this);
					break;
				}

				else if (Tienda.pasaPorCaja3(this)) {

					Tienda.atendidoCaja3(this);
					break;
				}

				else {
					
					System.out.println(String.format("El cliente %s no encuentra cajas libres, volvera a mirar", this.nombre));

				} 
			} while (true);
			
			//Tienda.customerExit(this);
			
			
			
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	/**
	 * Constructor de Customer, toma String nombre, y automaticamente toma la variable de clase contadorid para asignar el campo id al ejemplar.
	 * contador id se autoincremente en 1 para dotar a posteriores instancias de la clase de un nuevo id unico.
	 *
	 */
	public Customer(String nombre) {
		this.nombre = nombre;		
		this.id = contadorid;
		contadorid ++;		
		
	}
	
	/**
	 *Devuelve el id de un cliente.
	 *@return id
	 */
	public int getid() {
		return id;
	}
	/**
	 *Devuelve el nmombre de un cliente.
	 *@return nombre
	 */

	public String getNombre() {
		return nombre;
	}
	
	/**
	 *Devuelve el Contadorid de un cliente.
	 *@return id
	 */

	public static int getContadorid() {
		return contadorid;
	}	
	
	/**
	 * Metodo que imprime en salida estandar una cadena de Strings que indican nombre id y dinero gastado en la compra
	 * El dinero gastado se genera aleatoreamente mediante un objeto Random con limites de 0-500
	 * dicho importe rand es devuelto como int
	 * 
	 * @return int 
	 */
	public int compraRealizada ()  {	
		
		int rand = new Random().nextInt(500);
		
		return rand;		
	}
	
	/**
	 * Metodo void que imprime en salida estandar una cadena de Strings que indican nombre e id del cliente, representando su entrada en tienda a comprar
	 */
	public void entraenTienda () {
		
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha entrado a la tienda ", nombre, id));
	}
	
	
	/**
	 * Metodo void que imprime en salida estandar una cadena de Strings que indican nombre e id del cliente, representando su salida de tienda
	 */
	
	
public void saleTienda () {
		
		System.out.println(String.format("El cliente con nombre %s e identificador %s ha salido ", nombre, id));
	}
	
	
	@Override
	public String toString() {
		return String.format("Customer [id=%s, nombre=%s]", id, nombre);
	};
	
	 



}
