package dataStructures;

public class BinarySearchTreeOpposite<K extends Comparable<K>, V> extends BinarySearchTree<K ,V> implements OrderedDictionary<K,V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	
	public BinarySearchTreeOpposite() {
		super();
	}

	 /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.                                
     * Moreover, stores the last step of the path in lastStep.
     * @param key to be searched
     * @param lastStep - PathStep object referring to parent 
     * @return the found node, when the search is successful
     
     */
	protected BSTNode<K,V> findNode( K key, PathStep<K,V> lastStep )
    {      
        BSTNode<K,V> node = root;
        while ( node != null )
        {
     
            int compResult = -key.compareTo( node.getKey() );
            if ( compResult == 0 )
                return node;
            else if ( compResult < 0 )
            {
                lastStep.set(node, true);
                node = node.getLeft();
            }
            else
            {
                lastStep.set(node, false);
                node = node.getRight();
            }
        }
        return null;                                                    
    }  
	
	/**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.        
     *                         
     * @param node where the search starts 
     * @param key to be found
     * @return the found node, when the search is successful
     */
	   protected BSTNode<K,V> findNode( BSTNode<K,V> node, K key )
	    {                                                                   
	        if ( node == null )
	            return null;
	        else
	        {
	            int compResult = -key.compareTo( node.getKey() );
	            if ( compResult == 0 )
	                return node;                                         
	            else if ( compResult < 0 )
	                return this.findNode(node.getLeft(), key);
	            else                                                     
	                return this.findNode(node.getRight(), key); 
	        }                 
	    }
}
