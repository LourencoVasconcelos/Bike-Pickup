package dataStructures;

/**
 * Chained Hash table Iterator implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 */
public class ChainedHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int currentPosition;
	private Iterator<Entry<K, V>> itList;

	private Dictionary<K, V>[] table;

	public ChainedHashTableIterator(Dictionary<K, V>[] table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {
		if (!itList.hasNext())
			advance();
		return itList.hasNext();

	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (!this.hasNext())
			throw new NoSuchElementException();
		
		return itList.next();
	}

	@Override
	public void rewind() {
		currentPosition = 0;
		itList = table[currentPosition].iterator();

		advance();

	}

	private void advance() {

		while (!itList.hasNext() && currentPosition != this.table.length) {
			itList = table[currentPosition++].iterator();
		}

	}

}
