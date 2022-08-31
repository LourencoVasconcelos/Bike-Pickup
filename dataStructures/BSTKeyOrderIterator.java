package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K,V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BSTNode<K, V> rootNode;
	private Stack<BSTNode<K,V>> stack;
	
	public BSTKeyOrderIterator(BSTNode<K,V> node) {
		this.rootNode = node;
		rewind();
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		BSTNode<K,V> node = stack.pop();
		pushPathMin(node.getRight());
		
		return node.getEntry();
	}

	@Override
	public void rewind() {
		stack = new StackInList<>();
		pushPathMin(rootNode);
	}
	
	/**
	 * Metodo auxiliar para colocar todos os pais da raiz ate ao no minimo na pilha
	 * @param node - no a partir do qual vamos colocar os filhos na pilha
	 */
	private void pushPathMin(BSTNode<K,V> node) {
		while(node!=null) {
			stack.push(node);
			node = node.getLeft();
		}
	}

}
