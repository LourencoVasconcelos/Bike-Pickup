package bikePickUp.Enum;

/**
 * Classe enumerador que guarda um um conjunto fixo de constantes.
 * 
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 *
 */
public enum Command {
	
	// Constantes que definem os comandos
	EXIT("XS"),
	ADDUSER("AddUser"),
	REMOVEUSER("RemoveUser"),
	GETUSERINFO("GetUserInfo"),
	ADDPARK("AddPark"),
	ADDBIKE("AddBike"),
	REMOVEBIKE("RemoveBike"),
	GETPARKINFO("GetParkInfo"),
	PICKUP("PickUp"),
	PICKDOWN("PickDown"),
	CHARGEUSER("ChargeUser"),
	BIKEPICKUPS("BikePickUps"),
	USERPICKUPS("UserPickUps"),
	PARKEDBIKE("ParkedBike"),
	LISTDELAYED("ListDelayed"),
	FAVORITEPARKS("FavoriteParks"),
	INVALIDCOMMAND("");
		
	private String name;

	private Command(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Command getValue(String command) {
		for (Command c : Command.values()) {
			if (c.getName().equalsIgnoreCase(command)) {
				return c;
			}
		}
		return null;
	}
}
