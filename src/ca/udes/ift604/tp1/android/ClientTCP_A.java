package ca.udes.ift604.tp1.android;

import java.io.IOException;
import java.net.Socket;

public class ClientTCP_A {
	public static void main(String[] zero) {

		Socket socket;
		try {
			socket = new Socket("localhost", 2009);
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
