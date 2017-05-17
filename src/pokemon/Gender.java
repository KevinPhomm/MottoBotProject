package pokemon;

public enum Gender {
	MALE("♂", "Male"),
	FEMALE("♀", "Femelle"),
	GENDERLESS(" ", " ");
	
	public final String symbol;
	public final String name;
	
	private Gender(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}
}
