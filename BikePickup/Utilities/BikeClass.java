package bikePickUp.Utilities;

import dataStructures.*;

/**
 * Classe BikeClass que guarda as informacoes de uma bicicleta
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class BikeClass implements Bike {

	/**
	 * Serial Version UID da classe
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Constante que define uma string como vazia
	 */
	private static final String EMPTY_STRING_CONSTANT = "";

	/**
	 * Memory of the stack: a list.
	 * Lista com todos os picks efetuados por esta bicicleta
	 */
	private List<PicksList> picksList;

	/**
	 * Constantes que definem o indentificador da bicicleta, o identificador onde
	 * esta bicicleta esta estacionada, a matricula desta bicicleta, o ultimo parque
	 * onde esta bicicleta esta ou esteve estacionada(caso esteja a ser utilizada) e o
	 * ultimo utilizador que utiltizou ou esta a utlizar esta bicicleta.
	 */
	private String idBike, idPark, registration, lastPark, lastUser;
	
	/**
	 * Constantes que define se a bicicleta se encontra ou nao no primeiro Pick Up
	 */
	private boolean firstPick;

	/**
	 * Construtor da BikeClass que cria uma nova bicicleta
	 * 
	 * @param idBike       - identificador da bicicleta
	 * @param idPark       - identificador do parque onde a bicicleta vai ser criada
	 * @param registration - matricula da bicicleta
	 */
	public BikeClass(String idBike, String idPark, String registration) {
		this.idBike = idBike;
		this.registration = registration;
		this.idPark = idPark;
		lastPark = idPark;
		firstPick = true;;
		lastUser = EMPTY_STRING_CONSTANT;
		picksList = new DoublyLinkedList<PicksList>();
	}

	@Override
	public String getBikeId() {
		return idBike;
	}

	@Override
	public String getBikeParkId() {
		return idPark;
	}

	@Override
	public String getBikeRegistracion() {
		return registration;
	}

	@Override
	public void registerPickUp(String idUser) {
		idPark = EMPTY_STRING_CONSTANT;
		lastUser = idUser;
	}

	@Override
	public void registerPickDown(String idPark, int minutes, int delayMin, int delayPay) {
		PicksList p = new PicksListClass(lastUser, lastPark, idPark, minutes, delayMin, delayPay);
		this.idPark = idPark;
		lastPark = idPark;
		firstPick = false;
		picksList.addLast(p);
	}

	@Override
	public String getLastPark() {
		return lastPark;
	}

	@Override
	public String getLastIdUser() {
		return lastUser;
	}

	@Override
	public Iterator<PicksList> getBikeList() {
		return picksList.iterator();
	}

	@Override
	public boolean getBikeFirstPickUp() {
		return firstPick;
	}

}
