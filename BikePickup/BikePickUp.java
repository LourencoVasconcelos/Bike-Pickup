package bikePickUp;

import java.io.Serializable;

import bikePickUp.Exceptions.*;
import bikePickUp.Utilities.*;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * Interface da classe topo (BikePickUpClass)
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface BikePickUp extends Serializable {

	/**
	 * Adicina um utilizador ao sistema
	 * 
	 * @param idUser      - identificador do utilizador
	 * @param nif         - numero de identificacao fiscal do utilizador
	 * @param email       - email do utilizador
	 * @param phone       - telefone do utilizador
	 * @param userName    - nome do utilizador
	 * @param userAddress - morada do utilizador
	 * @throws UserExistsException - excepcao caso o utilizador exista
	 */
	void addUser(String idUser, int nif, String email, int phone, String userName, String userAddress) throws UserExistsException;
	
	/**
	 * Remove um utilizador dado o seu identificador
	 *
	 * @param idUser - identificador do utilizador
	 * @throws UserAlreadyUseSystemException - excepcao caso o utilizador ja tenha utilizado o sistema
	 * @throws UserDontExistsException - excepcao caso o utilizador nao exista
	 */
	void removeUser(String idUser) throws UserAlreadyUseSystemException, UserDontExistsException;

	/**
	 * Devolve algumas informacoes de um utilizador, dado o seu identificador
	 * 
	 * @param idUser - identificador do utilizador
	 * @return utilizador com o identificador dado
	 * @throws UserDontExistsException - excepcao caso o utilizador nao exista
	 */
	UserInformation getUserInformation(String idUser) throws UserDontExistsException;

	/**
	 * Adiciona um parque ao sistema
	 *
	 * @param idPark      - identificador do parque
	 * @param parkName    - nome do parque
	 * @param parkAddress - morada do parque
	 * @throws ParkExistException - excepcao caso o parque ja tenha sido registado
	 */
	void addPark(String idPark, String parkName, String parkAddress) throws ParkExistException;
	
	/**
	 * Adiciona uma bicicleta ao sistema e a um parque
	 *
	 * @param idBike - identificador da bicicleta
	 * @param idPark - identificador do parque
	 * @param registration - matricula da bicicleta
	 * @throws BikeExistException - excepcao caso a bicicleta ja tenha sido registada
	 * @throws ParkDontExistException - excepcao caso o parque onde a bicicleta foi registada nao exista
	 */
	void addBike(String idBike, String idPark, String registration) throws BikeExistException, ParkDontExistException;
	
	/**
	 * Remove uma bicicleta do sistema
	 *
	 * @param idBike - identificador da bicicleta
	 * @throws BikeDontExistException - excepcao caso a bicicleta nao exista
	 * @throws BikeAlreadyUsedException -  excepcao caso a bicicleta ja tenha sido usada
	 */
	void removeBike(String idBike) throws BikeDontExistException, BikeAlreadyUsedException;

	/**
	 * Devolve as informacoes do parque com o identificador dado
	 *
	 * @param idPark - identificador do parque
	 * @return parque com o identificador dado
	 * @throws ParkDontExistException - excepcao caso o parque onde a bicicleta foi registada nao exista
	 */
	ParkInformation getParkInfo(String idPark)  throws ParkDontExistException;

	/**
	 * Faz o pick up de uma bicicleta
	 * @param idBike - identificador da bicicleta
	 * @param idUser - identificador do utilizador
	 * @throws BikeDontExistException - excepcao caso a bicicleta nao exista
	 * @throws BikeInMovementException - excecao caso a bicicleta esteja em movimento
	 * @throws UserDontExistsException - excepcao caso o utilizador nao exista
	 * @throws UserInMovementException - excecao caso o utilizador ja esteja a utilizar uma bicicleta
	 * @throws NotEnoughMoneyException - excecao caso o utilizador nao tenha dinheiro suficiente para alugar uma bicicleta
	 */
	void pickUp(String idBike, String idUser) throws BikeDontExistException, BikeInMovementException, UserDontExistsException, UserInMovementException, NotEnoughMoneyException;

	/**
	 * Faz a entrega de uma bicicleta 
	 *
	 * @param idBike - identificador da bicicleta
	 * @param idUser - identificador do utilizador
	 * @param minutes - minutos de utilizacao da bicicleta
	 * @throws BikeDontExistException - excepcao caso a bicicleta nao exista
	 * @throws StopBikeException - excecao caso a bicicleta esteja estacoinada num parque
	 * @throws ParkDontExistException - excepcao caso o parque onde a bicicleta foi registada nao exista
	 * @throws InvalidDataException - excepcao caso os dados inseridos sejam invalidos
	 */
	void pickDown(String idBike, String idPark, int minutes) throws BikeDontExistException, StopBikeException, ParkDontExistException, InvalidDataException;

	/**
	 * Devolve o ultimo utilizador que utilizou a bicicleta com o identificador dado
	 * 
	 * @param idBike - identificador da bicicleta
	 * @return o ultimo utilizador que utilizou a bicicleta com o identificador dado
	 */
	UserInformation getLastIdUser(String idBike);

	/**
	 * Acrescenta o valor dado (<code> value <code>) a conta com o identificador de utilizador dado (<code> idUser <code>)
	 *
	 * @param idUser - identificador do utilizador
	 * @param value - valor a acrescentar na conta
	 * @throws UserDontExistsException - excepcao caso o utilizador nao exista
	 * @throws InvalidDataException - excepcao caso os dados inseridos sejam invalidos
	 */
	void chargeUser(String idUser, int value) throws UserDontExistsException, InvalidDataException;

	/**
	 * Altera o estado da bicicleta
	 * 
	 * @param idBike - identificador da bicicleta
	 * @param idPark - identificador do parque
	 * @return true caso a bicicleta esteja estacionada no parque com o identificador dado, false caso contrario
	 * @throws BikeDontExistException - excepcao caso a bicicleta nao exista
	 * @throws ParkDontExistException - excepcao caso o parque onde a bicicleta foi registada nao exista
	 * @throws BikeNotInParkException - excepcao caso a bicileta nao se encontre estacionada
	 */
	boolean parkedBike(String idBike, String idPark)
			throws BikeDontExistException, ParkDontExistException, BikeNotInParkException;

	/**
	 * Devolve um iterador para a lista de pickups do utilizador
	 * 
	 * @param idUser - identificador do utilizador
	 * @return iterador para a lista de pickups do utilizador
	 * @throws UserDontExistsException - excecao caso o utilizador nao exista
	 * @throws UserNotUsedSystemException - excepcao caso o utilizador nao tenha realizado pickups
	 * @throws UserInFirstPickUpException - excepcao caso o utilizador esteja no seu primeiro pickup
	 */
	Iterator<PicksList> getUserList(String idUser) throws  UserDontExistsException, UserNotUsedSystemException, UserInFirstPickUpException;
	
	/**
	 * Devolve um iterador para a lista de pickups da bicicleta
	 * 
	 * @param idBike - identificador da bicicleta
	 * @return iterador para a lista de pickups da bicicleta
	 * @throws BikeDontExistException - excepcao caso a bicicleta nao exista
	 * @throws NotUsedBikeException - excepcao caso a bicicleta ainda nao tenha sido alvo de pickups
	 * @throws BikeInFirstPickUpException - excepcao caso o primeiro pickup da bicicleta nao tenha terminado
	 */
	Iterator<PicksList> getBikeList(String idBike) throws BikeDontExistException, NotUsedBikeException, BikeInFirstPickUpException;

	/**
	 * Devolve um iterador para a arvore de users com atrasos
	 * @return iterador para a arvore de users com atrasos 
	 * @throws NoDelaysException - excepcao caso nao existam users com atrasos
	 */
	Iterator<Entry<Integer,List<User>>> listDelayed() throws NoDelaysException;
	
	/**
	 * Devolve um iterador para os parques mais utilizados
	 * @return iterador para a arvore de parques mais utilizados
	 * @throws NoPickUpsMadedException - excepcao caso nao tenham feito pickUps
	 */
	Iterator<Entry<String, Park>> favoritePark() throws NoPickUpsMadedException;
}