package cliente_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		final int puerto = 12345;
		
		System.out.println("Arrancando el servidor por el puerto " + puerto);
		
		ServerSocket sersoc = new ServerSocket(puerto);
		
		Socket sc = sersoc.accept();
		
		System.out.println("conexion entrante aceptada");
		
		DataInputStream in = new DataInputStream(sc.getInputStream());
		DataOutputStream out = new DataOutputStream(sc.getOutputStream());
		
		int edad = in.readInt();
		
		
		
		System.out.println("La edad que me envia el cliente son " + edad);
		
		if (edad >= 18) {
			
			out.writeUTF("Eres mayor de edad");	
			out.close();
			
		}
		
		else {
			out.writeUTF("Eres menor de edad");	
			out.close();
		}
		
		sersoc.close();


	}

}