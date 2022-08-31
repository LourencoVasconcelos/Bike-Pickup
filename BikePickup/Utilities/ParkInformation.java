package bikePickUp.Utilities;

import java.io.Serializable;

/**
 * Interface da classe ParkClass, e que contem apenas os metodos que devolvem
 * informacoes do parque. Interface criada para permitir a maior seguranca do
 * parque.
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface ParkInformation extends Serializable{

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
	 * Devolve as bicicletas que se encontram estacionadas neste parque
	 * @return das bicicletas que se encontram estacionadas neste parque
	 */
	String getBikes();
	
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
