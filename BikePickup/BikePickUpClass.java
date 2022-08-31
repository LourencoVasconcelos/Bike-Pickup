package bikePickUp;

import bikePickUp.Exceptions.*;
import bikePickUp.Utilities.*;
import dataStructures.*;

/**
 * Implementacao da classe topo, onde e feita a gestao do sistema
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class BikePickUpClass implements BikePickUp {
	private int c = 27;
	private static final int DEFAULT_SIZE = 20;

	/**
	 * Serial Version UID da classe
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Constante que define um int (inteiro) com o valor 0
	 */
	private static final int ZERO_VALUE_CONSTANT = 0;

	/**
	 * Dicionario onde irao ser guardadas as informacoes dos varios utilizadores
	 * K - (key) - String - contem os userId dos varios utlizadores
	 * V - (value) - User - classe UserClass que contem todas as informacoes do utilizador  associado a key
	 */
	private Dictionary<String, User> user;

	/**
	 * Dicionario onde irao ser guardadas as informacoes das varias bicicletas
	 * K - (key) - string - contem os bikeId das varias bicicleta
	 * V - (value) - Bike - classe BikeClass que contem todas as informacoes da bicicleta associada a key
	 */
	private Dictionary<String, Bike> bike;

	/**
	 * Classe onde irao ser guardadas as informacoes do parque
	 */
	private Dictionary<String, Park> park;
	
	/**
	 * boolean que verifica se ja foi realizado algum pickUp
	 */
	boolean donePickUps = false;

	private OrderedDictionary<Integer, List<User>> usersDelayed;
	
	private OrderedDictionary<Integer, OrderedDictionary<String, Park>> parksByPicks;
	
	/**
	 * Construtor da BikePickUpClass que cria um novo sistema de PickUps 
	 */
	public BikePickUpClass() {
		user = new ChainedHashTable<String, User>(DEFAULT_SIZE);
		bike = new ChainedHashTable<String, Bike>(DEFAULT_SIZE);
		park = new ChainedHashTable<String, Park>(DEFAULT_SIZE);
		usersDelayed = new BinarySearchTreeOpposite<Integer, List<User>>();
		parksByPicks = new BinarySearchTree<Integer, OrderedDictionary<String,Park>>();
	}

	@Override
	public void addUser(String idUser, int nif, String email, int phone, String userName, String userAddress)
			throws UserExistsException {
		if (searchUser(idUser)) {
			throw new UserExistsException();
		}
		
		User u = new UserClass(idUser, nif, email, phone, userName, userAddress);
		user.insert(idUser.toUpperCase(), u);
		int c = 2;
		System.out.println(c+3);
		System.out.println(this.c+3);
	}

	@Override
	public void removeUser(String idUser) throws UserAlreadyUseSystemException, UserDontExistsException {
		if (!searchUser(idUser)) {
			throw new UserDontExistsException();
		}
		if (!user.find(idUser.toUpperCase()).neverUsed()) {
			throw new UserAlreadyUseSystemException();
		}
		user.remove(idUser.toUpperCase());
	}

	@Override
	public void addPark(String idPark, String parkName, String parkAddress) throws ParkExistException {
		if (searchPark(idPark)) {
			throw new ParkExistException();
		}
		Park p = new ParkClass(idPark, parkName, parkAddress);
		park.insert(idPark.toUpperCase(), p);
	}

	@Override
	public void addBike(String idBike, String idPark, String registration)
			throws BikeExistException, ParkDontExistException {
		if (searchBike(idBike)) {
			throw new BikeExistException();
		}
		if (!searchPark(idPark)) {
			throw new ParkDontExistException();
		}
		Bike b= new BikeClass(idBike, idPark, registration);
		bike.insert(idBike.toUpperCase(), b);
		park.find(idPark.toUpperCase()).addBike(idBike);
	}

	@Override
	public void removeBike(String idBike) throws BikeDontExistException, BikeAlreadyUsedException {
		if (!searchBike(idBike)) {
			throw new BikeDontExistException();
		}
		if (!bike.find(idBike.toUpperCase()).getLastIdUser().equals("")) {
			throw new BikeAlreadyUsedException();
		}
		park.find(bike.find(idBike.toUpperCase()).getBikeParkId().toUpperCase()).removeBike(idBike);
		bike.remove(idBike.toUpperCase());
		
	}

	@Override
	public ParkInformation getParkInfo(String idPark) throws ParkDontExistException {
		if (!searchPark(idPark)) {
			throw new ParkDontExistException();
		}
		return (ParkInformation) park.find(idPark.toUpperCase());
	}

	@Override
	public void pickUp(String idBike, String idUser) throws BikeDontExistException, BikeInMovementException,
			UserDontExistsException, UserInMovementException, NotEnoughMoneyException {
		if (!searchBike(idBike)) {
			throw new BikeDontExistException();
		}
		if (bike.find(idBike.toUpperCase()).getBikeParkId().equals("")) {
			throw new BikeInMovementException();
		}
		if (!searchUser(idUser)) {
			throw new UserDontExistsException();
		}
		if (user.find(idUser.toUpperCase()).hasBike()) {
			throw new UserInMovementException();
		}
		if (user.find(idUser.toUpperCase()).hasMoney()) {
			throw new NotEnoughMoneyException();
		}
		Park p = park.find(bike.find(idBike.toUpperCase()).getBikeParkId().toUpperCase());
		int picks = p.numberOfPicks();
		if(picks>0) {
			parksByPicks.find(picks).remove(p.getparkName());
			if(parksByPicks.find(picks).isEmpty())
				parksByPicks.remove(picks);
		}
		user.find(idUser.toUpperCase()).changeBikeStatus();	
		park.find(bike.find(idBike.toUpperCase()).getBikeParkId().toUpperCase()).removeBike(idBike);
		park.find(bike.find(idBike.toUpperCase()).getBikeParkId().toUpperCase()).addPick();
		bike.find(idBike.toUpperCase()).registerPickUp(idUser);
		if(!donePickUps)
			donePickUps = true;
		
		if(parksByPicks.find(p.numberOfPicks())==null)
			parksByPicks.insert(p.numberOfPicks(), new BinarySearchTree<String, Park>());	
		parksByPicks.find(p.numberOfPicks()).insert(p.getparkName(), p);
	}

	@Override
	public void pickDown(String idBike, String idPark, int minutes)
			throws BikeDontExistException, StopBikeException, ParkDontExistException, InvalidDataException {
		if (!searchBike(idBike)) {
			throw new BikeDontExistException();
		}
		if (!bike.find(idBike.toUpperCase()).getBikeParkId().equals("")) {
			throw new StopBikeException();
		}
		if (!searchPark(idPark)) {
			throw new ParkDontExistException();
		}
		if (minutes <= ZERO_VALUE_CONSTANT) {
			throw new InvalidDataException();
		}
		int delayMin = delayMinutes(minutes);
		int delayPay = delayPayment(delayMin);
		User u = user.find((bike.find(idBike.toUpperCase()).getLastIdUser()).toUpperCase());
		int points = u.getUserPoints();
		if(points>0) {
			usersDelayed.find(points).remove(u);
			if(usersDelayed.find(points).isEmpty())
				usersDelayed.remove(points);
		}
		park.find(idPark.toUpperCase()).addBike(idBike);
		u.registerPickDown(idBike, idPark,minutes, bike.find(idBike.toUpperCase()).getLastPark(), delayMin, delayPay);
		bike.find(idBike.toUpperCase()).registerPickDown(idPark, minutes, delayMin, delayPay);
		if(delayMin>0) {
			if(usersDelayed.find(u.getUserPoints())==null)
				usersDelayed.insert(u.getUserPoints(), new DoublyLinkedList<User>());
			usersDelayed.find(u.getUserPoints()).addLast(u);
		}
	}

	@Override
	public void chargeUser(String idUser, int value) throws UserDontExistsException, InvalidDataException {
		if (!searchUser(idUser)) {
			throw new UserDontExistsException();
		}
		if (value <= ZERO_VALUE_CONSTANT) {
			throw new InvalidDataException();
		}
		user.find(idUser.toUpperCase()).changeUserBalance(value);
	}

	@Override
	public boolean parkedBike(String idBike, String idPark)
			throws BikeDontExistException, ParkDontExistException, BikeNotInParkException {
		if (!searchBike(idBike)) {
			throw new BikeDontExistException();
		}
		if (!searchPark(idPark)) {
			throw new ParkDontExistException();
		}
		if (bike.find(idBike.toUpperCase()).getBikeParkId().equals("")) {
			throw new BikeNotInParkException();
		}
		return bike.find(idBike.toUpperCase()).getBikeParkId().equals(idPark);
	}

	@Override
	public Iterator<PicksList> getUserList(String idUser)
			throws UserDontExistsException, UserNotUsedSystemException, UserInFirstPickUpException {
		if (!searchUser(idUser)) {
			throw new UserDontExistsException();
		}
		if (user.find(idUser.toUpperCase()).neverUsed()) {
			if (user.find(idUser.toUpperCase()).hasBike()) {
				throw new UserInFirstPickUpException();
			}
			throw new UserNotUsedSystemException();
		}
		return user.find(idUser.toUpperCase()).getUserList();
	}

	@Override
	public Iterator<PicksList> getBikeList(String idBike)
			throws BikeDontExistException, NotUsedBikeException, BikeInFirstPickUpException {
		if (!searchBike(idBike)) {
			throw new BikeDontExistException();
		}
		if (bike.find(idBike.toUpperCase()).getBikeFirstPickUp()) {
			if (bike.find(idBike.toUpperCase()).getBikeParkId().equals("")) 
			{
				throw new BikeInFirstPickUpException();
			} 
				else
				throw new NotUsedBikeException();
		}
					
		return bike.find(idBike.toUpperCase()).getBikeList();
	}
	public Iterator<Entry<Integer,List<User>>> listDelayed() throws NoDelaysException{
		if(usersDelayed.isEmpty())
			throw new NoDelaysException();
		return usersDelayed.iterator();
	}

	@Override
	public Iterator<Entry<String, Park>> favoritePark() throws NoPickUpsMadedException {
		if (!donePickUps) {
			throw new NoPickUpsMadedException();
		}
		return parksByPicks.maxEntry().getValue().iterator();
	}
	
	@Override
	public UserInformation getLastIdUser(String idBike) {
		return (UserInformation) user.find((bike.find(idBike.toUpperCase()).getLastIdUser()).toUpperCase());
	}

	@Override
	public UserInformation getUserInformation(String idUser) throws UserDontExistsException {
		if (!searchUser(idUser)) {
			throw new UserDontExistsException();
		}
		return (UserInformation) user.find(idUser.toUpperCase());
	}

	/**
	 * Procura uma bicicleta com o identificador dado e ve se ela existe ou nao
	 * 
	 * @param idBike - identificador da bicicleta
	 * @return true se a bicicleta existir, false caso contrario
	 */
	private boolean searchBike(String idBike) {
		boolean has = false;
		if (bike.find(idBike.toUpperCase()) != null && bike.find(idBike.toUpperCase()).getBikeId().equalsIgnoreCase(idBike)) 
			has = true;
		return has;
	}
	
	/**
	 * Procura o utilizador com o identificador dado e ve se ele existe ou nao
	 * 
	 * @param idUser - identificador do utilizador
	 * @return true se o utilizador existir, false caso contrario
	 */
	private boolean searchUser(String idUser) {
		boolean has = false;
		if ((user.find(idUser.toUpperCase()) != null) && user.find(idUser.toUpperCase()).getUserId().equalsIgnoreCase(idUser)) 
			has = true;
		
		return has;
	}
	
	/**
	 * Procura um parque com o identificador dado e ve se ele existe ou nao
	 * 
	 * @param idPark - identificador do parque
	 * @return true se o parque existir, false caso contrario
	 */
	private boolean searchPark(String idPark) {
		boolean has = false;
			if ((park.find(idPark.toUpperCase()) != null) && park.find(idPark.toUpperCase()).getParkId().equalsIgnoreCase(idPark)) {
				has = true;
			}
		return has;
	}

	/**
	 * Metodo auxiliar que calcula o tempo de atraso na entrada da bicicleta caso exista.
	 * @param minutes - minutos que utilizou a bicicleta
	 * @return - minutos de atraso na entrega da bicicleta
	 */
	private int delayMinutes(int minutes) {
		int aux = minutes-60;
		if(aux<0)
			aux=0;
		return aux;
	}

	/**
	 * Metodo auxiliar que calula o preco que um utilizador vai ter de pagar caso se tenha atrasado na entrega da bicicleta.
	 * @param minutes - minutos de atraso na entrega da bicicleta
	 * @return - preco a pagar pelo utiizador
	 */
	private int delayPayment(int delayMin) {
		int payment = 0; 
		if (delayMin>0) {
			payment = delayMin/30;
			int aux = delayMin-payment*30;
			if(aux>0)
				payment++;
		}
		return payment;
	}


}
