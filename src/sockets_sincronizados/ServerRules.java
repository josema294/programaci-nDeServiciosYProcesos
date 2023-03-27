package sockets_sincronizados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Interface para implementar datos del servidor, y mensajes predefinidos.
 * 
 * @author jose
 *
 */
public interface ServerRules {

	// Reglas de Conexion

	String host = "localhost";
	int puerto = 15000;
	String path = "src/sockets_sincronizados/log.txt";

	// Genmerador de TimeStamp
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss] ");
	String timestamp = now.format(formatter);

	// Plantillas de mensajes para el log

	String boot = timestamp + "El servidor arrancado a la espera de conexiones entrantes. Puerto: " + puerto;
	String in = timestamp + "Nueva conexión recibida desde: ";
	String error = timestamp + "Error al iniciar el servidor";
	String Cliente = timestamp + "Un cliente ha entrado al servidor y ha dejado un mensaje en el log";
	String hilo = timestamp + "Se inicia un nuevo hilo para atender las peticiones del cliente";

}
