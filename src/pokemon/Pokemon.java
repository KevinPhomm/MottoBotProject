package pokemon;

import java.io.Serializable;
import java.time.Instant;

public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final PokemonSpecies species;
	private int level;
	private IndividualValues IVs; 
	private Instant birth;
	
	public Pokemon(PokemonSpecies base) {
		this.species = base;
		this.birth = Instant.now();
	}
	
	public Instant getBirth() {
		return this.birth;
	}
}
