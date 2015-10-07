package org.mitlware.java.value;

public interface DirectedValue< Value, Derived > 
extends Comparable< Derived > {
	
	public Value get();
	
	public boolean isMinimizing();
}

// End ///////////////////////////////////////////////////////////////

