package bikePickUp.Utilities;

/**
 * Classe PicksListClass que guarda as informacoes sobre cada pickUp. Objeto
 * criado para facilitar o armazenamento das varias viagens que um utilizador ou
 * bibicleta ja fizeram.
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public class PicksListClass implements PicksList {

	/**
	 * Serial Version UID da classe
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Constantes que definem o identificador, o parque inicial e o parque final
	 */
	private String id, initialPark, finalPark;

	/**
	 * Constantes que definem os minutos, o atraso e o valor a pagar pelo atraso.
	 */
	private int minutes, delay, value;

	/**
	 * Construtor da classe PicksListClass que cria um novo registo dum PickUp
	 * 
	 * @param id          - identificador da bicicleta ou utilizador, dependendo de
	 *                    onde for utilizado
	 * @param initialPark - parque onde foi realizado o PickUp
	 * @param finalPark   - parque onde foi deixada a bicicleta
	 * @param minutes     - tempo da viagem em minutos
	 * @param delay       - tempo de atraso
	 * @param value       - valor a pagar pelo atraso
	 */
	public PicksListClass(String id, String initialPark, String finalPark, int minutes, int delay, int value) {
		this.id = id;
		this.initialPark = initialPark;
		this.finalPark = finalPark;
		this.minutes = minutes;
		this.delay = delay;
		this.value = value;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getInitialPark() {
		return initialPark;
	}

	@Override
	public String getfinalPark() {
		return finalPark;
	}

	@Override
	public int getMinutes() {
		return minutes;
	}

	@Override
	public int getDelay() {
		return delay;
	}

	@Override
	public int getValue() {
		return value;
	}

}
