package tema2;

import java.util.concurrent.Semaphore;

public class EjercicioChar extends Thread {
	private char c;
	private Semaphore sem;

	public EjercicioChar(char c, Semaphore s) {
		this.c = c;
		this.sem = s;
	}

	public void run() {
		if (c == 'a')
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		for (int i = 0; i < 10; i++)
			System.out.println((char) (c + i));
		if (c == '0')
			sem.release();
	}

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(0);
		Thread t1 = new EjercicioChar('a', semaphore);
		Thread t2 = new EjercicioChar('0', semaphore);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("Hilo principal del proceso interrumpido.");
		}
		System.out.println("Proceso terminado.");
	}
}

