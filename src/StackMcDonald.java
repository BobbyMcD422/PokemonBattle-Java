/**
 * Represents a Stack of Pokemons that can be 
 * pushed and popped from and has a list,
 * top, and Max Size
 * @author <i>Robert McDonald</i>
 */
public class StackMcDonald {
		/**
		 * Instance Variable for Stack's Top
		 */
		private int myTop;
		/**
		 * Instance Variable for Stack's List
		 */
		private PokemonCardMcDonald[] myList;
		/**
		 * Instance Variable for Stack's Size
		 */
		private static final int MAXSTACKSIZE = 52;
		
		/**
		 * Constructor for Stack
		 */
		public StackMcDonald()
		{
			int a = 0;
			myList = new PokemonCardMcDonald[MAXSTACKSIZE];
			myTop = -1;
			for(a = 0; a < myList.length; a++)
				myList[a] = null;
		}//Constructor
		/**
		 * "Pops off" and Give the Current Pokemon Card on Top of
		 *  the Stack, then Removes it from the Stack
		 * 
		 * @return Current Pokemon Card on Top of the Stack
		 */
		public PokemonCardMcDonald pop()
		{
			PokemonCardMcDonald ans = null;
			if(!isEmpty())
			{
				ans = myList[myTop];
				myTop--;
			}//if
			return ans;
		}//pop
		/**
		 * Adds a Pokemon to the Stack if possible
		 * 
		 * @param thing The Pokemon Card being Added to the Stack
		 * @return Whether Adding the Pokemon Card was Successful
		 */
		public boolean push(PokemonCardMcDonald thing)
		{
			boolean success = false;
			if(!isFull())
			{
				myTop++;
				myList[myTop] = thing;
				success = true;
			}//if
			return success;
		}//push
		/**
		 * Determines if the Stack is Empty
		 * 
		 * @return Whether the Stack is Empty
		 */
		public boolean isEmpty()
		{
			return myTop == -1;
		}//isEmpty
		
		/**
		 * Determines if the Stack is Full
		 * 
		 * @return Whether the Stack is Full
		 */
		public boolean isFull()
		{
			return myTop == MAXSTACKSIZE-1;
		}//isEmpty
}//StackMcDonald
