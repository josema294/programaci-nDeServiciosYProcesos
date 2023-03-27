package semaforos;



public class EjercicioSemaforos {
	

	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	
		
		

		//Instanciamos los clientes
		
		Customer c0 = new Customer("Paco");
		Customer c1 = new Customer("Manolo");
		Customer c2 = new Customer("Benito");
		Customer c3 = new Customer("Antonia");
		Customer c4 = new Customer("Herminia");
		Customer c5 = new Customer("Antonia");
		Customer c6 = new Customer("Fulgencio");
		Customer c7 = new Customer("Hermenegilda");
		Customer c8 = new Customer("Eulalia");
		Customer c9 = new Customer(" Gregorio");
		Customer c10 = new Customer("Eustaquio");
		Customer c11 = new Customer("Mike");
		Customer c12 = new Customer("Gonzo");
		Customer c13 = new Customer("Soledad");
		Customer c14 = new Customer("Titina");
		Customer c15 = new Customer("Paca");
		Customer c16 = new Customer("Fernando");
		Customer c17 = new Customer("Diosdado");
		Customer c18 = new Customer("Piedad");
		Customer c19 = new Customer("Soledad");
		Customer c20 = new Customer("Carmelo");
		
		//Iniciamos hilos
		c0.start();
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();
		c8.start();
		c9.start();
		c10.start();
		c11.start();
		c12.start();
		c13.start();
		c14.start();
		c15.start();
		c16.start();
		c17.start();
		c18.start();
		c19.start();
		c20.start();
		
		
		// Reunificamos hilos
		c0.join();
		c1.join();
		c2.join();
		c3.join();
		c4.join();
		c5.join();
		c6.join();
		c7.join();
		c8.join();
		c9.join();
		c10.join();
		c11.join();
		c12.join();
		c13.join();
		c14.join();
		c15.join();
		c16.join();
		c17.join();
		c18.join();
		c19.join();
		c20.join();
		
		System.out.println("Compras terminadas, la tienda cierra");
		System.out.println(String.format("la caja0 ha recaudado %s", Tienda.getRecaudacionCaja0()));
		System.out.println(String.format("la caja1 ha recaudado %s", Tienda.getRecaudacionCaja1()));
		System.out.println(String.format("la caja2 ha recaudado %s", Tienda.getRecaudacionCaja2()));
		System.out.println(String.format("la caja3 ha recaudado %s", Tienda.getRecaudacionCaja3()));
		
		System.out.println(String.format("la recaudacion total ha sido de  %s", Tienda.getRecaudacionTotal()));
		
		
		
	}

}
