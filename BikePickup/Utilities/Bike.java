package bikePickUp.Utilities;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * Interface que implementa a classe BikeClass
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface Bike extends Serializable {

	/**
	 * Devolve o identificador da bicicleta
	 * @return identificador da bicicletaS
	 */
	String getBikeId();
	
	/**
	 * Devolve o identificador do parque onde a bicicleta se encontra, caso se encontre estacionada
	 * @return identificador do parque onde a bicicleta se encontra, caso se encontre estacionada
	 */
	String getBikeParkId();
	
	/**
	 * Devolve a matricula da bicicleta
	 * @return matricula da bicicleta
	 */
	String getBikeRegistracion();
	
	/**
	 * Devolve o ultimo parque onde a bicicleta se encontrou parada
	 * @return ultimo parque onde a bicicleta se encontrou parada
	 */
	String getLastPark();
	
	/**
	 * Faz o registo de um pick up da bicicleta, dando o identificador do utilizador
	 * @param idUser - identificador do utilizador
	 */
	void registerPickUp(String idUser);

	/**
	 * Faz o registo de um pick down da bicicleta, adicionando a bicicleta o parque onde foi estacionada
	 * Adiciona a viagem a lista de viagens ja efetuadas com esta bicicleta
	 * @param idPark - identificador do parque
	 * @param minutes - duracao da viagem em minutos
	 * @param delayMin - minutos de atraso na entrega da bicicleta
	 * @param delayPay - pagamento caso tenha existido atraso na entrega da bicicleta
	 */
	void registerPickDown(String idPark, int minutes, int delayMin, int delayPay);
	
	/**
	 * Devolve o identificador do ultimo utilizador a utilizar esta bicicleta
	 * @return o identificador do ultimo utilizador a utilizar esta bicicleta
	 */
	String getLastIdUser();
	
	/**
	 * Devolve o iterador da lista de pickups terminados da bicicleta
	 * @return iterador da lista de pickups terminados da bicicleta
	 */
	Iterator<PicksList> getBikeList();
	
	/**
	 * Devolve se a bicicleta esta no primeiro pickUp
	 * @return true se a bicicleta esta no primeiro pickUp, false caso contrario;
	 */
	boolean getBikeFirstPickUp();
}
