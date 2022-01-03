package servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.swing.JOptionPane;

public class AgendaServer {

	public AgendaServer() {

		try {

			Agenda a = new AgendaImpl();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/AgendaService", a);

			int op = 2;

			while (op != JOptionPane.OK_OPTION) {
				op = JOptionPane.showConfirmDialog(null, "Desligar Servidor?",
						"SERVIDOR RMI", JOptionPane.PLAIN_MESSAGE);
			}

			if (op == JOptionPane.OK_OPTION)
				System.exit(0);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Exceções Encontradas:\n"+e, "Aviso",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}

	}

	public static void main(String[] args) {

		new AgendaServer();

	}

}
