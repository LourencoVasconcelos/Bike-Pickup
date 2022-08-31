package dataStructures;

public class EntryClass<K, V> implements Entry<K, V> {

	
	private static final long serialVersionUID = 1L;
	private V value;
	private K key;
    EntryClass(K key, V value){
		this.key= key;
		this.value=value;
	}
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

}
