package sockets_sincronizados;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//Conexion al servidor e inicializacion de entradas salidas de datos con este
		Socket sc = new Socket(ServerRules.host, ServerRules.puerto);
		DataOutputStream out = new DataOutputStream(sc.getOutputStream());
		DataInputStream in = new DataInputStream(sc.getInputStream());
		
		//Insercion de mensaje en el log del servidor
		out.writeUTF(ServerRules.timestamp + "Hola soy el cliente 2 que te ha colado un mensaje en tu log");
		
		//Lectura de una pieza del log que traemos del servidor
		String piezadeLog = in.readUTF();
		System.out.println(
				"Soy el cliente 2 demostrando que he podido leer una pieza del log del servidor\n" + piezadeLog);

	}

}
