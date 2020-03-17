/*
This class is used to generate a DeathReport of a snipe.
It stating the CauseOfDeath, age at the time of death, and year of death.
@author Anupam Dahal, Torunima Taposhi
 */
public class DeathReport {

	public int year;
	public int age;
	CauseOfDeath cause;

	/**
	 * constructs a DeathReport Object
	 * @param year  int
	 * @param age   int
	 * @param cause CauseOfDeath
	 */
	public DeathReport(int  year, int age, CauseOfDeath cause) {
		this.year=year;
		this.age=age;
		this.cause=cause;
	}

}
