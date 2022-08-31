package bikePickUp.Utilities;

import java.io.Serializable;

/**
 * Interface da classe UserClass, e que contem apenas os metodos que devolvem
 * informacoes da conta. Interface criada para permitir a maior seguranca da
 * conta do utilizador
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public interface UserInformation extends Serializable {

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


}
