package bikePickUp.Utilities;

import dataStructures.*;

/**
 * Classe ParkClass que guarda as informacoes de um utilizador
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class UserClass implements User, UserInformation {

	/**
	 * Serial Version UID da classe
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Memoria da pilha: uma list.
	 * Lista com todos os picks efetuados por este utilizador
	 */
	private List<PicksList> picksList;
	
	/**
	 * Constante que define o saldo minimo que uma conta precisa de ter para fazer e
	 * define o valor inicial que uma conta tem quando e criada Pick Ups.
	 */
	private static final int MINIMUM_BALANCE = 5;

	/**
	 * Constante que define um int (inteiro) com o valor 0
	 */
	private static final int ZERO_VALUE_CONSTANT = 0;
	
	/**
	 * Constante que define um int (inteiro) com o valor 1
	 */
	private static final int NUMBER_ONE_CONSTANT = 1;
	
	/**
	 * Constantes que definem o identificador do utilizador, o email do utilizador,
	 * o nome do utilizador e a morada do utilizador.
	 */
	private String idUser, email, userName, userAddress;

	/**
	 * Constantes que definem o nif do utilizador, o telefone do utilizador, os
	 * pontos da conta do utilizador, e o dinheiro que a conta tem.
	 */
	private int nif, phone, points, balance;

	/**
	 * Constantes que define se este utilizador se encontra ou nao com uma
	 * bicicleta, e se se este utilizador ja realizou alguma fez um pickUp seguido
	 * de PickDown.
	 */
	private boolean hasBike, neverUsed;

	/**
	 * Construtor da classe UserClass que cria um novo utilizador
	 * 
	 * @param idUser      - identificador do utilizador
	 * @param nif         - nif do utilizador
	 * @param email       - email do utilizador
	 * @param phone       - numero de telemovel do utilizador
	 * @param userName    - nome do utilizador
	 * @param userAddress - morada do utilizador
	 */
	public UserClass(String idUser, int nif, String email, int phone, String userName, String userAddress) {
		this.idUser = idUser;
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.userName = userName;
		this.userAddress = userAddress;
		balance = MINIMUM_BALANCE;
		points = ZERO_VALUE_CONSTANT;
		hasBike = false;
		neverUsed = true;
		picksList = new DoublyLinkedList<PicksList>();

	}

	@Override
	public String getUserId() {
		return idUser;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public int getUserNif() {
		return nif;
	}

	@Override
	public String getUserAddress() {
		return userAddress;
	}

	@Override
	public String getUserEmail() {
		return email;
	}

	@Override
	public int getUserPhone() {
		return phone;
	}

	@Override
	public int getUserBalance() {
		return balance;
	}

	@Override
	public int getUserPoints() {
		return points;
	}

	@Override
	public boolean hasBike() {
		return hasBike;
	}

	@Override
	public void changeBikeStatus() {
		hasBike = !hasBike;
	}

	@Override
	public boolean hasMoney() {
		return (balance < MINIMUM_BALANCE);
	}

	@Override
	public void registerPickDown(String idBike, String idPark, int minutes, String lastPark, int delayMin,
			int delayPay) {
		PicksList p = new PicksListClass(idBike, lastPark, idPark, minutes, delayMin, delayPay);
		picksList.addLast(p);
		if (neverUsed == true)
			neverUsed = false;

		if (delayMin > ZERO_VALUE_CONSTANT) {
			balance -= delayPay;
			points += NUMBER_ONE_CONSTANT;
		}
		changeBikeStatus();
	}

	@Override
	public void changeUserBalance(int value) {
		balance += value;
	}

	@Override
	public Iterator<PicksList> getUserList() {
		return picksList.iterator();
	}

	@Override
	public boolean neverUsed() {
		return neverUsed;
	}
}
