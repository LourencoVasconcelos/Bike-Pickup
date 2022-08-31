package bikePickUp.Utilities;

import java.io.Serializable;

import dataStructures.Iterator;

/**
 * Interface que implementa todos os metodos da classe UserClass
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface User extends Serializable {

	/**
	 * Devolve o identificador da conta do utilizador
	 * 
	 * @return identificador do utilizador
	 */
	String getUserId();

	/**
	 * Devolve o nome da conta do utilizador
	 * 
	 * @return nome da conta do utilizador
	 */
	String getUserName();

	/**
	 * Devolve o nif da conta do utilizador
	 * 
	 * @return nif da conta do utilizador
	 */
	int getUserNif();

	/**
	 * Devolve a morada da conta do utilizador
	 * 
	 * @return morada da conta do utilizador
	 */
	String getUserAddress();

	/**
	 * Devolve o email da conta do utilizador
	 * 
	 * @return email da conta do utilizador
	 */
	String getUserEmail();

	/**
	 * Devolve o numero de telefone da conta do utilizador
	 * 
	 * @return numero de telefone da conta do utilizador
	 */
	int getUserPhone();

	/**
	 * Devolve o saldo da conta da conta do utlizador
	 * 
	 * @return saldo da conta da conta do utlizador
	 */
	int getUserBalance();

	/**
	 * Devolve os pontos da conta do utilizador
	 * 
	 * @return pontos da conta do utilizador
	 */
	int getUserPoints();

	/**
	 * Verifica se o utilizador se encontra com uma bicicleta ou nao
	 * 
	 * @return true se estiver com uma bicicleta, false caso contrario
	 */
	boolean hasBike();

	/**
	 * Altera o estado do utilizador, no caso de ter uma bicileta ou nao
	 */
	void changeBikeStatus();

	/**
	 * Verifica se o utilizador tem dinheiro para requisitar uma bicicleta
	 * 
	 * @return true se o utlizador tiver dinheiro, false caso contrario
	 */
	boolean hasMoney();

	/**
	 * Faz o pick down na conta do utilizador. Altera o estado da variavel hasBike
	 * para false e no caso de ser o primeiro pick Down do utilizador altera a
	 * variavel neverUsed.
	 * 
	 * @param idBike   - identificador da bicicleta
	 * @param idPark   - identificador do parque
	 * @param minutes  - duracao da viagem
	 * @param lastPark - parque de onde a bicicleta partiu
	 * @param delayMin - minutos de atraso na entrega da bicicleta
	 * @param delayPay - pagamento caso tenha existido atraso na entrega da bicicleta
	 */
	void registerPickDown(String idBike, String idPark, int minutes, String lastPark, int delayMin, int delayPay);

	/**
	 * Acrescenta o valor <code> value <code> a conta
	 * 
	 * @param value - valor a acrescentar na conta
	 */
	void changeUserBalance(int value);

	/**
	 * Devolve o iterador da lista de pickups terminados do utilizador
	 * 
	 * @return o iterador da lista de pickups terminados do utilizador
	 */
	Iterator<PicksList> getUserList();

	/**
	 * Verifica se o utilizador ja realizou algum pickup
	 * 
	 * @return true se o utilizador ja tiver realizado algum pickup, false caso
	 *         contrario
	 */
	boolean neverUsed();
}
