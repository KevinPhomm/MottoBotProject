package pokemon;

import java.io.Serializable;
import java.util.Random;

public class IndividualValues implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Random RNG = new Random();
	
	public final int hp;
	public final int atk;
	public final int def;
	public final int spAtk;
	public final int spDef;
	public final int speed;
	
	public IndividualValues() {
		this.hp = RNG.nextInt(32);
		this.atk = RNG.nextInt(32);
		this.def = RNG.nextInt(32);
		this.spAtk = RNG.nextInt(32);
		this.spDef = RNG.nextInt(32);
		this.speed = RNG.nextInt(32);
	}
	
	public IndividualValues(int hp, int atk, int def, int spAtk, int spDef, int speed) {
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
	}
	
	public PokemonType hiddenType() {
		int i = (((this.hp&0x1)+(2*(this.atk&0x1))+(4*(this.def&0x1))+(8*(this.speed&0x1))+(16*(this.spAtk&0x1))+(32*(this.spDef&0x1)))*15)/63;
		PokemonType type = PokemonType.values()[i+1];
		return type;
	}
	
	public int hiddenPower() {
		int power = (((((this.hp&0x2)>>1)+(2*(this.atk&0x2)>>1)+(4*(this.def&0x2)>>1)+(8*(this.speed&0x2)>>1)+(16*(this.spAtk&0x2)>>1)+(32*(this.spDef&0x2)>>1))*40)/63)+30;
		return power;
	}
	
}
