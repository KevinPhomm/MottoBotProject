package pokemon;

public enum Nature {
	BOLD("Assuré", 0.9, 1.1, 1.0, 1.0, 1.0),
	QUIRKY("Bizarre", 1.0, 1.0, 1.0, 1.0, 1.0),
	BRAVE("Brave", 1.1, 1.0, 1.0, 1.0, 0.9),
	CALM("Calme", 0.9, 1.0, 1.0, 1.1, 1.0),
	QUIET("Discret", 1.0, 1.0, 1.1, 1.0, 0.9),
	DOCILE("Docile", 1.0, 1.0, 1.0, 1.0, 1.0),
	MILD("Doux", 1.0, 0.9, 1.1, 1.0, 1.0),
	RASH("Foufou", 1.0, 1.0, 1.1, 0.9, 1.0),
	GENTLE("Gentil", 1.0, 0.9, 1.0, 1.1, 1.0),
	HARDY("Hardi", 1.0, 1.0, 1.0, 1.0, 1.0),
	JOLLY("Jovial", 1.0, 1.0, 0.9, 1.0, 1.1),
	LAX("Lâche", 1.0, 1.1, 1.0, 0.9, 1.0),
	IMPISH("Malin", 1.0, 1.1, 0.9, 1.0, 1.0),
	SASSY("Malpoli", 1.0, 1.0, 1.0, 1.1, 0.9),
	NAUGHTY("Mauvais", 1.1, 1.0, 1.0, 0.9, 1.0),
	MODEST("Modeste", 0.9, 1.0, 1.1, 1.0, 1.0),
	NAIVE("Naïf", 1.0, 1.0, 1.0, 0.9, 1.1),
	HASTY("Pressé", 1.0, 0.9, 1.0, 1.0, 1.1),
	CAREFUL("Prudent", 1.0, 1.0, 0.9, 1.1, 1.0),
	BASHFUL("Pudique", 1.0, 1.0, 1.0, 1.0, 1.0),
	RELAXED("Relax", 1.0, 1.1, 1.0, 1.0, 0.9),
	ADAMANT("Rigide", 1.1, 1.0, 0.9, 1.0, 1.0),
	SERIOUS("Sérieux", 1.0, 1.0, 1.0, 1.0, 1.0),
	LONELY("Solo", 1.1, 0.9, 1.0, 1.0, 1.0),
	TIMID("Timide", 0.9, 1.0, 1.0, 1.0, 1.1);
	
	public final String name;
	public final double atkMod;
	public final double defMod;
	public final double spAtkMod;
	public final double spDefMod;
	public final double speedMod;
	
	private Nature(String name, double atkMod, double defMod, double spAtkMod, double spDefMod, double speedMod) {
		this.name = name;
		this.atkMod = atkMod;
		this.defMod = defMod;
		this.spAtkMod = spAtkMod;
		this.spDefMod = spDefMod;
		this.speedMod = speedMod;
	}
}
