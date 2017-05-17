package pokemon;

import java.io.Serializable;

public class BaseStats implements Serializable {
	private static final long serialVersionUID = 1L;

	public final int hp;
	public final int atk;
	public final int def;
	public final int spAtk;
	public final int spDef;
	public final int speed;
	
	public BaseStats(int baseHP, int baseAtk, int baseDef, int baseSAtk, int baseSDef, int baseSpd) {
		this.hp = baseHP;
		this.atk = baseAtk;
		this.def = baseDef;
		this.spAtk = baseSAtk;
		this.spDef = baseSDef;
		this.speed = baseSpd;
	}
}
