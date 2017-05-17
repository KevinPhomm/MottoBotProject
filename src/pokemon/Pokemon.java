package pokemon;

import java.io.Serializable;
import java.time.Instant;
import java.util.Random;

public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Random RNG = new Random();
	
	public final PokemonSpecies species;
	public final Gender gender;
	private int level;
	private final IndividualValues IVs;
	private EffortValues EVs; 
	public final Nature nature;
	public final Instant encounterDate;
	
	public Pokemon(PokemonSpecies base) {
		this.species = base;
		this.gender = Gender.GENDERLESS;
		this.level = RNG.nextInt(70)+1;
		this.IVs = new IndividualValues();
		this.EVs = new EffortValues();
		this.nature = Nature.values()[RNG.nextInt(Nature.values().length)];
		this.encounterDate = Instant.now();
	}
	
	public Pokemon(PokemonSpecies base, int level) {
		this.species = base;
		this.gender = Gender.GENDERLESS;
		this.level = level;
		this.IVs = new IndividualValues();
		this.EVs = new EffortValues();
		this.nature = Nature.values()[RNG.nextInt(Nature.values().length)];
		this.encounterDate = Instant.now();
	}
	
	public int level() {
		return this.level;
	}
	
	public int hp() {
		if(this.species.equals(PokemonSpecies.MUNJA))
			return 1;
		
		return ((((2*this.species.baseStats.hp)+this.IVs.hp+(this.EVs.hp/4))*this.level)/100)+this.level+10;
	}
	
	public int atk() {
		return (int) ((((((2*this.species.baseStats.atk)+this.IVs.atk+(this.EVs.atk/4))*this.level)/100)+5)*this.nature.atkMod);
	}
	
	public int def() {
		return (int) ((((((2*this.species.baseStats.def)+this.IVs.def+(this.EVs.def/4))*this.level)/100)+5)*this.nature.defMod);
	}
	
	public int spAtk() {
		return (int) ((((((2*this.species.baseStats.spAtk)+this.IVs.spAtk+(this.EVs.spAtk/4))*this.level)/100)+5)*this.nature.spAtkMod);
	}
	
	public int spDef() {
		return (int) ((((((2*this.species.baseStats.spDef)+this.IVs.spDef+(this.EVs.spDef/4))*this.level)/100)+5)*this.nature.spDefMod);
	}
	
	public int speed() {
		return (int) ((((((2*this.species.baseStats.speed)+this.IVs.speed+(this.EVs.speed/4))*this.level)/100)+5)*this.nature.speedMod);
	}
}
