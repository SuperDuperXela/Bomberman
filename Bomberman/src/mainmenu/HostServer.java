package mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HostServer {
	private ServerSocket serverSocket;
	private boolean active = true;

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			while (active) {
				new HostClientHandler(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class HostClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public HostClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);

				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					}
					if ("Ping".equals(inputLine)) {
						inputLine = "Pong";
					}
					out.println(inputLine);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}