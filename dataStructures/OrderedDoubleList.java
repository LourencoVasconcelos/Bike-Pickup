package dataStructures;

/**
 * Ordered Double List implementation
 *
 * @author Lourenco Vasconcelos, 52699
 * @author Goncalo Mateus, 53052
 * 
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 0L;

	/**
	 * Number of elements in the list.
	 */
	protected int currentSize;

	/**
	 * Node at the head of the list.
	 */
	protected DListNode<Entry<K, V>> head;

	/**
	 * Node at the tail of the list.
	 */
	protected DListNode<Entry<K, V>> tail;

	/**
	 * Constructor of an empty Ordered Double List. head and tail are initialized as
	 * null. currentSize is initialized as 0.
	 */
	public OrderedDoubleList() {
		head = null;
		tail = null;
		currentSize = 0;

	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}

	/**
	 * Returns the node with the first occurrence of the specified element in the
	 * list, if the list contains the element. Otherwise, returns null.
	 * 
	 * @param key - key to be searched
	 * @return DListNode<Entry<K, V>> where element was found, null if not found
	 */
	protected DListNode<Entry<K, V>> findNode(K key) {
		DListNode<Entry<K, V>> node = head;
		while (node != null && node.getElement().getKey().compareTo(key) < 0) {
			node = node.getNext();
		}
		return node;
	}

	@Override
	public V find(K key) {

		DListNode<Entry<K, V>> node = findNode(key);

		if (node != null)
			return node.getElement().getValue();
		else
			return null;
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		return tail.getElement();
	}

	@Override
	public V insert(K key, V value) {
		if (head == null || key.compareTo(head.getElement().getKey()) < 0)
			return addFirst(key, value);
		else if (key.compareTo(tail.getElement().getKey()) > 0)
			return addLast(key, value);
		else if (key.compareTo(findNode(key).getElement().getKey()) == 0) {
			return replace(key, value);
		} else
			return addMiddle(key, value);

	}

	/**
	 * Inserts the specified Entry at the first position in the list.
	 * 
	 * @param key   - key of the Entry to be inserted
	 * @param Value - value of the Entry to be inserted
	 * @return null
	 */
	protected V addFirst(K key, V Value) {
		Entry<K, V> pair = new EntryClass<>(key, Value);
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(pair, null, head);

		if (this.isEmpty())
			tail = newNode;
		else
			head.setPrevious(newNode);

		head = newNode;
		currentSize++;
		return null;
	}

	/**
	 * Inserts the specified Entry at the last position in the list.
	 * 
	 * @param key   - key of the Entry to be inserted
	 * @param Value - value of the Entry to be inserted
	 * @return null
	 */
	protected V addLast(K key, V value) {
		Entry<K, V> pair = new EntryClass<>(key, value);
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(pair, tail, null);

		if (this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);

		tail = newNode;
		currentSize++;
		return null;
	}

	/**
	 * Replace the value of Entry with the specified key by the specified value, and
	 * returns the old value
	 * 
	 * @param key   - key of the Entry to be inserted
	 * @param Value - value of the Entry to be inserted
	 * @return null
	 */
	protected V replace(K key, V value) {
		DListNode<Entry<K, V>> oldNode = findNode(key);
		Entry<K, V> pair = new EntryClass<>(key, value);

		V oldValue = oldNode.getElement().getValue();
		oldValue = oldNode.getElement().getValue();
		oldNode.setElement(pair);
		return oldValue;
	}

	/**
	 * Inserts the specified Entry at the middle position in the list.
	 * 
	 * @param key   - key of the Entry to be inserted
	 * @param Value - value of the Entry to be inserted
	 * @return null
	 */
	protected V addMiddle(K key, V value) {
		DListNode<Entry<K, V>> oldNode = findNode(key);
		Entry<K, V> pair = new EntryClass<>(key, value);

		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(pair, oldNode.getPrevious(), oldNode);
		oldNode.getPrevious().setNext(newNode);
		oldNode.setPrevious(newNode);
		currentSize++;
		return null;
	}

	@Override
	public V remove(K key) {
		if (head == null || find(key) == null)
			return null;
		else if (head.getElement().getKey().compareTo(key) == 0)
			return removeFirst(key);
		else if (tail.getElement().getKey().compareTo(key) == 0)
			return removeLast(key);
		else
			return removeMiddle(key);
	}

	/**
	 * Removes the first Entry in the list
	 * 
	 * @param key - key of Entry to be removed
	 * @return - return the value of the Entry removed
	 */
	protected V removeFirst(K key) {
		V oldValue = find(key);
		head = head.getNext();

		if (head == null) {
			tail = null;
		} else
			head.setPrevious(null);

		currentSize--;
		return oldValue;
	}

	/**
	 * Removes the last Entry in the list
	 * 
	 * @param key - key of Entry to be removed
	 * @return - return the value of the Entry removed
	 */
	protected V removeLast(K key) {
		V oldValue = find(key);
		tail = tail.getPrevious();

		if (tail == null) {
			head = null;
		} else
			tail.setNext(null);

		currentSize--;
		return oldValue;
	}

	/**
	 * Removes the Entry with the specified key in the list
	 * 
	 * @param key - key of Entry to be removed
	 * @return - return the value of the Entry removed
	 */
	protected V removeMiddle(K key) {
		V oldValue = find(key);

		DListNode<Entry<K, V>> prevNode = findNode(key).getPrevious();
		DListNode<Entry<K, V>> nextNode = findNode(key).getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);

		currentSize--;
		return oldValue;
	}
}
