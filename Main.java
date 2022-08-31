import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import bikePickUp.*;
import bikePickUp.Enum.*;
import bikePickUp.Exceptions.*;
import bikePickUp.Utilities.Park;
import bikePickUp.Utilities.ParkInformation;
import bikePickUp.Utilities.PicksList;
import bikePickUp.Utilities.User;
import bikePickUp.Utilities.UserInformation;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * BIKEPICKUP - Programa que implementa um sistema de partilha gratuita de bicicletas para a
 * Cidade da Costa de Caparica.
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class Main {

	// Constantes que definem as mensagens para ao utilizador
	private static final String BICICLETA_NAO_ESTA_EM_PARQUE = "Bicicleta nao esta em parque.";
	private static final String BICICLETA_ESTACIONADA_NO_PARQUE = "Bicicleta estacionada no parque.";
	private static final String BICICLETA_NAO_FOI_UTILIZADA = "Bicicleta nao foi utilizada.";
	private static final String BICICLETA_PARADA = "Bicicleta parada.";
	private static final String BICICLETA_EM_MOVIMENTO = "Bicicleta em movimento.";
	private static final String BICICLETA_EM_PRIMEIRA_PICK_UP = "Bicicleta em movimento em primeiro pickup.";
	private static final String BICICLETA_JA_FOI_UTILIZADA = "Bicicleta ja foi utilizada.";
	private static final String BICICLETA_INEXISTENTE = "Bicicleta inexistente.";
	private static final String BICICLETA_REMOVIDA_COM_SUCESSO = "Bicicleta removida com sucesso.";
	private static final String BICICLETA_ADICIONADA_COM_SUCESSO = "Bicicleta adicionada com sucesso.";
	private static final String BICICLETA_EXISTENTE = "Bicicleta existente.";
	private static final String UTILIZADOR_NAO_UTILIZOU_O_SISTEMA = "Utilizador nao utilizou o sistema.";
	private static final String UTILIZADOR_EM_PRIMEIRO_PICK_UP = "Utilizador em primeiro PickUp.";
	private static final String UTILIZADOR_EM_MOVIMENTO = "Utilizador em movimento.";
	private static final String UTILIZADOR_JA_UTILIZOU_O_SISTEMA = "Utilizador ja utilizou o sistema.";
	private static final String UTILIZADOR_INEXISTENTE = "Utilizador inexistente.";
	private static final String UTILIZADOR_REMOVIDO_COM_SUCESSO = "Utilizador removido com sucesso.";
	private static final String UTILIZADOR_EXISTENTE = "Utilizador existente.";
	private static final String UTILIZADOR_INSERIDO_COM_SUCESSO = "Insercao de utilizador com sucesso.";
	private static final String PARQUE_INEXISTENTE = "Parque inexistente.";
	private static final String PARQUE_EXISTENTE = "Parque existente.";
	private static final String PARQUE_ADICIONADO_COM_SUCESSO = "Parque adicionado com sucesso.";
	private static final String DADOS_INVALIDOS = "Dados invalidos.";
	private static final String SALDO_INSUFICIENTE = "Saldo insuficiente.";
	private static final String PICK_UP_COM_SUCESSO = "PickUp com sucesso.";
	private static final String NAO_FORAM_EFETUADOS_PICKUPS = "Nao foram efetuados pickups.";
	private static final String NAO_SE_REGISTARAM_ATRASOS = "Nao se registaram atrasos.";
	private static final String PARQUE_FAVORITO = "%s: %s, %d%n";
	private static final String PICK_UPS = "%s %s %s %d %d %d%n";
	private static final String SALDO = "Saldo: %d euros%n";
	private static final String PICK_DOWN = "Pickdown com sucesso: %d euros, %d pontos%n";
	private static final String INFO_PARQUE = "%s: %s, %d%n";
	private static final String INFO_USER = "%s: %d, %s, %s, %d, %d, %d%n";
	private static final String SAVE_AND_EXIT = "Gravando e terminando...\n";
	private static final String ERROR = "error occured";

	// Constante que define o nome do ficheiro onde o programa sera guardado entre utilizacoes
	private static final String DATA_FILE = "storedqueuetests.data";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		BikePickUp b = load();
		Scanner in = new Scanner(System.in);
		Command cmd = readCommand(in);

		while (!cmd.equals(Command.EXIT)) {
			switch (cmd) {
			case ADDUSER:
				addUser(in, b);
				break;
			case REMOVEUSER:
				removeUser(in, b);
				break;
			case GETUSERINFO:
				getUserInfo(in, b);
				break;
			case ADDPARK:
				addPark(in, b);
				break;
			case ADDBIKE:
				addBike(in, b);
				break;
			case REMOVEBIKE:
				removeBike(in, b);
				break;
			case GETPARKINFO:
				getParkInfo(in, b);
				break;
			case PICKUP:
				pickUp(in, b);
				break;
			case PICKDOWN:
				pickDown(in, b);
				break;
			case CHARGEUSER:
				chargeUser(in, b);
				break;
			case BIKEPICKUPS:
				bikePickUps(in, b);
				break;
			case USERPICKUPS:
				userPickUps(in, b);
				break;
			case PARKEDBIKE:
				parkedBike(in, b);
				break;
			case LISTDELAYED:
				listDelayed(b);
				break;
			case FAVORITEPARKS:
				favoriteParks(b);
				break;
			default:
				System.out.println("");
				break;
			}
			System.out.println();
			cmd = readCommand(in);
		}
		save(b);
		System.out.println(SAVE_AND_EXIT);

	}

	/**
	 * Metodo que le o input do utilizador
	 * 
	 * @param input - input do utilizador
	 * @return comando dado pelo utilizador
	 */
	private final static Command readCommand(Scanner input) {
		Command c = Command.getValue(input.next());
		if (c == null)
			return Command.INVALIDCOMMAND;
		else
			return c;
	}

	/**
	 * Guarda as informacoes do programa
	 * 
	 * @param b - classe a guardar
	 * @throws IOException - excecao caso corra alguma falha na gravacao
	 */
	private static void save(BikePickUp b) throws IOException {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(DATA_FILE));
			file.writeObject(b);
			file.flush();
			file.close();
		} catch (IOException exception) {
			System.out.println(ERROR);
		}
	}

	/**
	 * Faz o load das informacoes ja guardadas no programa , senao cria um programa vazio
	 * 
	 * @return das afirmacoes ja guardadas no programa caso existam, senao cria um programa vazio
	 */
	private static BikePickUp load() {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(DATA_FILE));
			BikePickUp b = (BikePickUp) file.readObject();
			file.close();
			return b;
		} catch (IOException exception) {
			return new BikePickUpClass();
		} catch (ClassNotFoundException exception) {
			System.out.println(ERROR);
		}
		return null;
	}

	/**
	 * Metodo que permite adicionar um novo utilizador ao sistema
	 * 
	 * @param in - Scanner para ler input
	 * @param b  - classe topo
	 */
	private static void addUser(Scanner in, BikePickUp b) {
		String idUser = in.next();
		int nif = in.nextInt();
		String email = in.next();
		int phone = in.nextInt();
		String userName = in.nextLine().trim();
		String userAddress = in.nextLine();

		try {
			b.addUser(idUser, nif, email, phone, userName, userAddress);
			System.out.println(UTILIZADOR_INSERIDO_COM_SUCESSO);
		} catch (UserExistsException E) {
			System.out.println(UTILIZADOR_EXISTENTE);
		}
	}

	/**
	 * Metodo que permite remover um utilizador do sistema
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - classe topo
	 */
	private static void removeUser(Scanner in, BikePickUp b) {
		String idUser = in.next();

		try {
			b.removeUser(idUser);
			System.out.println(UTILIZADOR_REMOVIDO_COM_SUCESSO);
		} catch (UserDontExistsException E) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (UserAlreadyUseSystemException E) {
			System.out.println(UTILIZADOR_JA_UTILIZOU_O_SISTEMA);
		}

	}

	/**
	 * Metodo que devolve a informacao do utilizador pedido caso este exista
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - classe topo
	 */
	private static void getUserInfo(Scanner in, BikePickUp b) {
		String idUser = in.next();
		try {
			UserInformation a = b.getUserInformation(idUser);
			System.out.printf(INFO_USER, a.getUserName(), a.getUserNif(), a.getUserAddress(), a.getUserEmail(),
					a.getUserPhone(), a.getUserBalance(), a.getUserPoints());
		} catch (UserDontExistsException E) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		}
	}

	/**
	 * Metodo que permite adicionar um novo parque ao sistema
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - classe topo
	 */
	private static void addPark(Scanner in, BikePickUp b) {
		String idPark = in.next();
		String parkName = in.nextLine().trim();
		String parkAddress = in.nextLine();

		try {
			b.addPark(idPark, parkName, parkAddress);
			System.out.println(PARQUE_ADICIONADO_COM_SUCESSO);
		} catch (ParkExistException E) {
			System.out.println(PARQUE_EXISTENTE);
		}
	}

	/**
	 * Metodo que permite adicionar uma nova bicicleta ao sistema
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void addBike(Scanner in, BikePickUp b) {
		String idBike = in.next();
		String idPark = in.next();
		String registration = in.nextLine().trim();

		try {
			b.addBike(idBike, idPark, registration);
			System.out.println(BICICLETA_ADICIONADA_COM_SUCESSO);
		} catch (BikeExistException E) {
			System.out.println(BICICLETA_EXISTENTE);
		} catch (ParkDontExistException E) {
			System.out.println(PARQUE_INEXISTENTE);
		}

	}

	/**
	 * Metodo que permite retirar uma bicicleta do sistema
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void removeBike(Scanner in, BikePickUp b) {
		String idBike = in.next();

		try {
			b.removeBike(idBike);
			System.out.println(BICICLETA_REMOVIDA_COM_SUCESSO);
		} catch (BikeDontExistException E) {
			System.out.println(BICICLETA_INEXISTENTE);
		} catch (BikeAlreadyUsedException E) {
			System.out.println(BICICLETA_JA_FOI_UTILIZADA);
		}

	}

	/**
	 * Metodo que devolve ao utilizador a informacao do parque pedido
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void getParkInfo(Scanner in, BikePickUp b) {
		String idPark = in.next();
		try {
			ParkInformation p = b.getParkInfo(idPark);
			System.out.printf(INFO_PARQUE, p.getparkName(), p.getParkAddress(), p.getParkNumberOfBikes());
		} catch (ParkDontExistException E) {
			System.out.println(PARQUE_INEXISTENTE);
		}
	}

	/**
	 * Metodo que permite ao utilizador realizar o pickup de uma bicicleta
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void pickUp(Scanner in, BikePickUp b) {
		String idBike = in.next();
		String idUser = in.next();

		try {
			b.pickUp(idBike, idUser);
			System.out.println(PICK_UP_COM_SUCESSO);
		} catch (BikeDontExistException E) {
			System.out.println(BICICLETA_INEXISTENTE);
		} catch (BikeInMovementException E) {
			System.out.println(BICICLETA_EM_MOVIMENTO);
		} catch (UserDontExistsException E) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (UserInMovementException E) {
			System.out.println(UTILIZADOR_EM_MOVIMENTO);
		} catch (NotEnoughMoneyException E) {
			System.out.println(SALDO_INSUFICIENTE);
		}

	}

	/**
	 * Metodo que permite ao utilizador devolver uma bicicleta, terminar uma viagem
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void pickDown(Scanner in, BikePickUp b) {
		String idBike = in.next();
		String idPark = in.next();
		int minutes = in.nextInt();

		try {
			b.pickDown(idBike, idPark, minutes);
			UserInformation u = b.getLastIdUser(idBike);
			System.out.printf(PICK_DOWN, u.getUserBalance(), u.getUserPoints());
		} catch (BikeDontExistException E) {
			System.out.println(BICICLETA_INEXISTENTE);
		} catch (StopBikeException E) {
			System.out.println(BICICLETA_PARADA);
		} catch (ParkDontExistException E) {
			System.out.println(PARQUE_INEXISTENTE);
		} catch (InvalidDataException E) {
			System.out.println(DADOS_INVALIDOS);
		}

	}

	/**
	 * Metodo que permite carregar o saldo de um utilizador
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void chargeUser(Scanner in, BikePickUp b) {
		String idUser = in.next();
		int value = in.nextInt();

		try {
			b.chargeUser(idUser, value);
			UserInformation u = b.getUserInformation(idUser);
			System.out.printf(SALDO, u.getUserBalance());
		} catch (UserDontExistsException E) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (InvalidDataException E) {
			System.out.println(DADOS_INVALIDOS);
		}
	}

	/**
	 * Metodo que devolve a lista dos pickups realizados por uma bicicleta
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void bikePickUps(Scanner in, BikePickUp b) {
		String idBike = in.next();

		try {
			Iterator<PicksList> it = b.getBikeList(idBike);
			itAux(it);
		} catch (BikeDontExistException E) {
			System.out.println(BICICLETA_INEXISTENTE);
		} catch (NotUsedBikeException E) {
			System.out.println(BICICLETA_NAO_FOI_UTILIZADA);
		} catch (BikeInFirstPickUpException E) {
			System.out.println(BICICLETA_EM_PRIMEIRA_PICK_UP);
		}
	}

	/**
	 * Metodo auxiliar dos metodos bikePickUps e userPickUps para percorrer um
	 * iterador
	 * 
	 * @param it - iterador a ser percorrido
	 */
	private static void itAux(Iterator<PicksList> it) {
		while (it.hasNext()) {
			PicksList a = it.next();
			System.out.printf(PICK_UPS, a.getId(), a.getInitialPark(), a.getfinalPark(), a.getMinutes(), a.getDelay(),
					a.getValue());
		}
	}

	/**
	 * Metodo que devolve a lista dos pickups realizados por um utilizador
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void userPickUps(Scanner in, BikePickUp b) {
		String idUser = in.next();

		try {
			Iterator<PicksList> it = b.getUserList(idUser);
			itAux(it);
		} catch (UserDontExistsException E) {
			System.out.println(UTILIZADOR_INEXISTENTE);
		} catch (UserNotUsedSystemException E) {
			System.out.println(UTILIZADOR_NAO_UTILIZOU_O_SISTEMA);
		} catch (UserInFirstPickUpException E) {
			System.out.println(UTILIZADOR_EM_PRIMEIRO_PICK_UP);
		}

	}

	/**
	 * Metodo que informa o utilizador se a bicicleta esta ou nao estacionada num
	 * parque
	 * 
	 * @param in - Scanner para ler o input
	 * @param b  - Classe topo
	 */
	private static void parkedBike(Scanner in, BikePickUp b) {
		String idBike = in.next();
		String idPark = in.next();

		try {
			if (b.parkedBike(idBike, idPark))
				System.out.println(BICICLETA_ESTACIONADA_NO_PARQUE);
		} catch (BikeDontExistException E) {
			System.out.println(BICICLETA_INEXISTENTE);
		} catch (ParkDontExistException E) {
			System.out.println(PARQUE_INEXISTENTE);
		} catch (BikeNotInParkException E) {
			System.out.println(BICICLETA_NAO_ESTA_EM_PARQUE);
		}
	}

	/**
	 * Metodo que devolve a lista dos utilizadores que ja se atrasaram a devolver
	 * bicicletas
	 * 
	 * @param b - Classe topo
	 */
	private static void listDelayed(BikePickUp b) {
		try {
			Iterator<Entry<Integer, List<User>>> it = b.listDelayed();
			while(it.hasNext()) {
				Entry<Integer,List<User>> e = it.next();
				List<User> lu = e.getValue();
				Iterator<User> itu = lu.iterator();
				while(itu.hasNext()) {
					User u = itu.next();
					System.out.println(u.getUserName()+": "+u.getUserNif()+", "+u.getUserAddress()+", "+u.getUserEmail()+", "+u.getUserPhone()+", "+u.getUserBalance()+", "+u.getUserPoints());
				}
			}
		}catch(NoDelaysException E) {
		System.out.println(NAO_SE_REGISTARAM_ATRASOS);
		}
	}

	/**
	 * Metodo que lista os parques mais movimentados
	 * 
	 * @param b - Classe topo
	 */
	private static void favoriteParks(BikePickUp b) {
		try {
			Iterator<Entry<String, Park>> it = b.favoritePark();
			while(it.hasNext()){
				Entry<String, Park> e = it.next();
				Park p = e.getValue();
				System.out.printf(PARQUE_FAVORITO, p.getparkName(), p.getParkAddress(), p.numberOfPicks());
			}
		} catch (NoPickUpsMadedException E) {
			System.out.println(NAO_FORAM_EFETUADOS_PICKUPS);
		}
	}

}