package pistadeportiva;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// Instanciamos el club donde se ira a jugar los partidos, e indicamos el numero de pistas y su precio.

		ClubPadel clubPepito = new ClubPadel("ClubDePadelPepito", 4, 20);

		Cuarteto c1 = new Cuarteto(2, clubPepito); // Solo 2 jugadores, no llegaran a jugar
		Cuarteto c2 = new Cuarteto(4, clubPepito);
		Cuarteto c3 = new Cuarteto(4, clubPepito);
		Cuarteto c4 = new Cuarteto(4, clubPepito);
		Cuarteto c5 = new Cuarteto(4, clubPepito);
		Cuarteto c6 = new Cuarteto(4, clubPepito);
		Cuarteto c7 = new Cuarteto(4, clubPepito);

		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();

		c1.join();
		c2.join();
		c3.join();
		c4.join();
		c5.join();
		c6.join();
		c7.join();

		System.out
		.println(String.format("Finalizan todos los partidos, el club de padel %s ha recaudado un total de %s",
				clubPepito.getNombre(), clubPepito.getRecaudacion()));

	}

}
