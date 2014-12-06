
public class ListExample {
	public static Node<Character> getUppercaseList(Node<Character> head) {
		// check to see that head exists
	    if (head == null) {
	        return null;
	    }
	    
	    // declare root of stack
	    Node<Character> first 	= head;
	    Node<Character> current = first.next;
	    Node<Character> prev 	= first;
	    
	    // check to see if the first value is upper case
	    if(!Character.isUpperCase(first.value)) {
	    	first = current;
	    }
	    
	    // check to see if the rest of values are upper case;
	    while(current != null) {
	    	
	    	// check to see if value of current node is upper case
	    	if(!Character.isUpperCase(current.value)) {
	    		// if value of current node not upper case, skip current value by linking 'over' it
	    		// (prev -> curr -> next = prev -> next)
	    		prev.next = current.next;
	    	} else {
	    		// only increase previous node to current one if we are not 'deleting' the current node
	    		prev = current;
	    	}
	    	
	    	// increase the current node by one
	    	current = current.next;
	    }
	    
	    // if we've iterated through list, and first node contains uppercase
	    // (we skip this check if the first two nodes are lowercase), set it to null
	    if(first != null && !Character.isUpperCase(first.value)) {
	    	first = current;
	    }
	    
	    // return root of node tree
	    return first;
	}
}

