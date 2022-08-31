package bikePickUp.Utilities;

import java.io.Serializable;

/**
 * Interface que implementa a classe ParkClass
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface Park extends Serializable {

	/**
	 * Devolve o identificador do parque
	 * @return identificador do parque
	 */
	String getParkId();
	
	/**
	 * Devolve o nome do parque
	 * @return nome do parque
	 */
	String getparkName();
	
	/**
	 * Devolve a morada do parque
	 * @return morada do parque
	 */
	String getParkAddress();
	
	/**
	 * Adicona uma bicicleta ao parque, dando o identificador da bicicleta
	 * @param idBike - identificado da bicicleta
	 */
	void addBike(String idBike);
	
	/**
	 * Remove a bicicleta do parque
	 * @param idBike - identificado da bicicleta
	 */
	void removeBike(String idBike);
	
	/**
	 * Devolve as bicicletas que se encontram estacionadas neste parque
	 * @return das bicicletas que se encontram estacionadas neste parque
	 */
	String getBikes();
	
	/**
	 * Adiciona um ao contador de pickups realizados no parque
	 */
	void addPick();
	
	/**
	 * Devolve o numero de pickups realizados no parque
	 * @return numero de pickups realizados no parque
	 */
	int numberOfPicks();
	
	/**
	 * Devolve o numero de bicicletas que este parque tem
	 * @return numero de bicicletas que este parque tem
	 */
	int getParkNumberOfBikes();
}
