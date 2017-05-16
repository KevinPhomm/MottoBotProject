package pokemon;

import java.io.Serializable;

public class IndividualValues implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final int hp;
	public final int atk;
	public final int def;
	public final int spAtk;
	public final int spDef;
	public final int speed;

	public IndividualValues(int hp, int atk, int def, int spAtk, int spDef, int speed) {
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
	}
}
