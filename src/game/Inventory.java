package game;

// TODO: Auto-generated Javadoc
/**
 * The Class Inventory.
 */
public class Inventory {

	/** The items. */
	private Item[] items;
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public Item[] getItems() { return items; }
	
	/**
	 * Gets the.
	 *
	 * @param i the i
	 * @return the item
	 */
	public Item get(int i) { return items[i]; }
	
	/**
	 * Instantiates a new inventory.
	 *
	 * @param max the max
	 */
	public Inventory(int max){
		items = new Item[max];
	}
	
	/**
	 * Adds the.
	 *
	 * @param item the item
	 */
	public void add(Item item){
		for (int i = 0; i < items.length; i++){
			if (items[i] == null){
				items[i] = item;
				break;
			}
		}
	}

	/**
	 * Removes the.
	 *
	 * @param item the item
	 */
	public void remove(Item item){
		for (int i = 0; i < items.length; i++){
			if (items[i] == item){
				items[i] = null;
				return;
			}
		}
	}

	/**
	 * Checks if is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull(){
		int size = 0;
		for (int i = 0; i < items.length; i++){
			if (items[i] != null)
				size++;
		}
		return size == items.length;
	}
	
	/**
	 * Contains.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean contains(Item item) {
		for (Item i : items){
			if (i == item)
				return true;
		}
		return false;
	}
}
