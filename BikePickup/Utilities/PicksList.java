package bikePickUp.Utilities;

import java.io.Serializable;

/**
 * Interface que implementa a classe PicksListClass
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface PicksList extends Serializable {

	/**
	 * Devolve o identificador desta mensagem
	 * @return identificador da bicicleta ou utilizador dependendo da classe em que esta a ser utilizado
	 */
	String getId();
	
	/**
	 * Devolve o parque onde a bicicleta foi levantada
	 * @return parque a bicicleta foi levantada
	 */
	String getInitialPark();
	
	/**
	 * Devolve o parque onde a bicicleta foi devolvida
	 * @return parque onde a bicicleta foi devolvida
	 */
	String getfinalPark();
	
	/**
	 * Devolve o tempo da viagem
	 * @return tempo da viagem
	 */
	int getMinutes();
	
	/**
	 * Devolve o tempo de atraso
	 * @return tempo de atraso
	 */
	int getDelay();
	
	/**
	 * Devolve o valor a pagar pelo atraso
	 * @return valor a pagar pelo atraso
	 */
	int getValue();
}
