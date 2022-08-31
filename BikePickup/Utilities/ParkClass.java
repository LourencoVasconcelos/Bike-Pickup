package bikePickUp.Utilities;

import dataStructures.DoublyLinkedList;
import dataStructures.List;

/**
 * Classe ParkClass que guarda as informacoes de um parque
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class ParkClass implements Park, ParkInformation {

	/**
	 * Serial Version UID da classe
	 */
    static final long serialVersionUID = 0L;

	/**
	 * Constantes que definem o identificador deste parque, o nome deste parque e a
	 * morada deste parque
	 */
	private String idPark, parkName, parkAddress;
	
	/**
	 * Constante que define o numero de Pick Ups que este parque ja efetuou
	 */
	private int numberOfPicks;

	/**
	 * Memoria da pilha: uma list.
	 * Lista com as bicicletas que se encontram estacionadas neste parque
	 */
	private List<String> idBike;

	/**
	 * Constante que define um int (inteiro) com o valor 0
	 */
	private static final int ZERO_VALUE_CONSTANT = 0;

	/**
	 * Construtor da classe ParkClass que cria um novo parque
	 * 
	 * @param idPark      - identificador do parque
	 * @param parkName    - nome do parque
	 * @param parkAddress - morada do parque
	 */
	public ParkClass(String idPark, String parkName, String parkAddress) {
		this.idPark = idPark;
		this.parkName = parkName;
		this.parkAddress = parkAddress;
		this.numberOfPicks = ZERO_VALUE_CONSTANT;
		idBike = new DoublyLinkedList<String>();

	}

	@Override
	public String getParkId() {
		return idPark;
	}

	@Override
	public String getparkName() {
		return parkName;
	}

	@Override
	public String getParkAddress() {
		return parkAddress;
	}

	@Override
	public void addBike(String idBike) {
		this.idBike.addFirst(idBike.toUpperCase());

	}

	@Override
	public void removeBike(String idBike) {
		this.idBike.remove(idBike.toUpperCase());
	}

	@Override
	public String getBikes() {
		return "";
	}

	@Override
	public void addPick() {
		numberOfPicks++;
	}

	@Override
	public int numberOfPicks() {
		return numberOfPicks;
	}

	@Override
	public int getParkNumberOfBikes() {
		return this.idBike.size();
	}

}
