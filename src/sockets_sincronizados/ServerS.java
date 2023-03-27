package sockets_sincronizados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerS extends Thread implements ServerRules {

	// Declaramos el servidor, el logWritter y los data in data out
	private ServerSocket servidor;
	private PrintWriter logWriter = null;
	private BufferedReader logReader = null;
	private Socket cliente = null;
	private String mensajeCliente;
	private DataInputStream in;
	private DataOutputStream out;
	
	/**
	 * Metodo de arrancado del servidor, registra en el log que el servidor se ha rrancado y si hay
	 * algun problema con el puerto registra en el log un error.
	 * @throws IOException
	 */
	public void arrancarServer() throws IOException {

		try {
			servidor = new ServerSocket(ServerRules.puerto);
			logWriter = new PrintWriter(new BufferedWriter(new FileWriter(ServerRules.path, true)));
			logWriter.println(ServerRules.boot);
			logWriter.flush();
		} catch (IOException e) {

			logWriter = new PrintWriter(new BufferedWriter(new FileWriter(ServerRules.path, true)));
			logWriter.println(ServerRules.error + e);
			logWriter.flush();
		}

	}

	/**
	 * Bloque de codigo que ejecuta un bucle para aceptar a los clientes entrantes.
	 * Por cada cliente se generara un hilo que llamara a run()
	 * @throws IOException
	 */
	public void aceptarClientes() throws IOException {

		while (true) {

			cliente = servidor.accept();
			logWriter.println(ServerRules.in + cliente.getInetAddress());
			logWriter.flush();

			Thread hilo = new Thread(this);
			hilo.start();

		}

	}

	/**
	 * Metodo sincronizado, solo un hilo podra acceder a el de forma simultanea, y recibiendo la informacion del
	 * cliente registra en el log que se ha recibido una conexion y su ip, asi como el mensaje que deja el cliente.
	 * @throws IOException
	 */
	private synchronized void escribeLog() throws IOException {

		in = new DataInputStream(cliente.getInputStream());
		mensajeCliente = in.readUTF();
		logWriter = new PrintWriter(new BufferedWriter(new FileWriter(ServerRules.path, true)));
		logWriter.println(ServerRules.Cliente);
		logWriter.println(mensajeCliente);
		logWriter.close();

	}

	/**
	 * Metodo sincronizado, solo un hilo podra acceder a el de forma simultanea, utilizado para que un hilo 
	 * correspondiente a un cliente conectado recoja una pieza de informacion del log y sea mandada a este
	 * para su uso. 
	 * @throws IOException
	 */
	private synchronized void leeLog() throws IOException {

		out = new DataOutputStream(cliente.getOutputStream());
		logReader = (new BufferedReader(new FileReader(ServerRules.path)));
		String piezadeLog = logReader.readLine();
		out.writeUTF(piezadeLog);

	}

	/**
	 *Metodo con la ejecucion del hilo/cliente conectado, llama a escribeLog() y leeLog();
	 *para ejecutar las escrituras lecturas y salidas entradas de datos con el cliente.
	 */
	@Override
	public void run() {

		logWriter.println(ServerRules.hilo);

		try {
			escribeLog();
			leeLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Creamos instancia de servidor
		ServerS server = new ServerS();

		server.arrancarServer();
		server.aceptarClientes();

	}

}
