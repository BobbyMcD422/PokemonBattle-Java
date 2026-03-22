/**
 * Represents a Pokemon Card with a Pokemon / Name, 
 * Multiplier, and Power
 * @author <i>Robert McDonald</i>
 */
public class PokemonCardMcDonald {
	/**
	 * Private Instance Variable for Pokemon Card's Pokemon / Name
	 */
	private String myPokemon;
	/**
	 * Private Instance Variable for Pokemon Card's Multiplier
	 */
	private int myMultiplier;
	/**
	 * Private Instance Variable for Pokemon Card's Power
	 */
	private double myPower;
	/**
	 * Null Constructor for Pokemon Card
	 */
	public PokemonCardMcDonald()
	{
		myPokemon = "None";
		myMultiplier = 0;
		myPower = 0;
	}//Null Constructor
	/**
	 * Full Constructor for Pokemon Card
	 * 
	 * @param newPokemon The New Value of Pokemon Card's Pokemon / Name
	 * @param newMultiplier The New Value of Pokemon Card's Multiplier
	 * @param newPower The New Value of Pokemon Card's Power
	 */
	public PokemonCardMcDonald(String newPokemon, int newMultiplier, double newPower)
	{
		myPokemon = newPokemon;
		myMultiplier = newMultiplier;
		myPower = newPower;
	}//Full Constructor
	/**
	 * Getter for Pokemon Card's Pokemon / Name
	 * 
	 * @return Pokemon / Name
	 */
	public String getPokemon()
	{
		return myPokemon;
	}//getPokemon
	/**
	 * Getter for Pokemon Card's Multiplier
	 * 
	 * @return Current Value of Multiplier
	 */
	public int getMultiplier()
	{
		return myMultiplier;
	}//getMultiplier
	/**
	 * Getter for Pokemon Card's Power
	 * 
	 * @return Current Value of Power
	 */
	public double getPower()
	{
		return myPower;
	}//getPower
	/**
	 * Setter for Pokemon Card's Pokemon / Name
	 * 
	 * @param newPokemon New Value of Pokemon Card's Pokemon
	 */
	public void setPokemon(String newPokemon)
	{
		myPokemon = newPokemon;
	}//getPokemon
	/**
	 * Setter for Pokemon Card's Multiplier 
	 * 
	 * @param newMultiplier New Value of Pokemon Card's Multiplier
	 */
	public void setMultiplier(int newMultiplier)
	{
		myMultiplier = newMultiplier;
	}//getMultiplier
	/**
	 * Setter for Pokemon Card's Power
	 * 
	 * @param newPower New Value of Pokemon Card's Power
	 */
	public void setPower(double newPower)
	{
		myPower = newPower;
	}//getPower
	/**
	 * toString Override (used for Debugging)
	 * 
	 * @return toString Description of Pokemon Card
	 */
	public String toString()
	{
		String ans;
		ans = "Name: " + myPokemon + "\n";
		ans += "Power: " + myPower + "\n";
		ans += "Multiplier: " + myMultiplier + "\n";
		return ans;
	}
}//PokemonCardMcDonald