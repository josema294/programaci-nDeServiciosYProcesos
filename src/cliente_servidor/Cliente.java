package cliente_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Cual es tu edad: ");
		Scanner scan = new Scanner(System.in);
		
		int edad = scan.nextInt();
		
		
		Socket sc = new Socket("localhost", 12345);
		
		DataOutputStream out = new DataOutputStream(sc.getOutputStream());
		DataInputStream in = new DataInputStream(sc.getInputStream());
		
		out.writeInt(edad);
		
		String respuestaServer = in.readUTF();
		
		System.out.println(respuestaServer);
		
		out.close();
		in.close();
		sc.close();
	}
}