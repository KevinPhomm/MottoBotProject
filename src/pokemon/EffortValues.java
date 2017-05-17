package pokemon;

import java.io.Serializable;

public class EffortValues implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int hp;
	public int atk;
	public int def;
	public int spAtk;
	public int spDef;
	public int speed;
	
	public EffortValues() {
		this.hp = 0;
		this.atk = 0;
		this.def = 0;
		this.spAtk = 0;
		this.spDef = 0;
		this.speed = 0;
	}
	
	public EffortValues(int hp, int atk, int def, int spAtk, int spDef, int speed) {
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
	}

	public boolean isMaxed() {
		return (this.hp+this.atk+this.def+this.spAtk+this.spDef+this.speed)>=510;
	}
}
