package org.mitlware.java.value;


//////////////////////////////////////////////////////////////////////

public final class Min< Value extends Comparable< Value > > 
implements DirectedValue< Value, Min< Value > > {

	private final Value value; 
	
	///////////////////////////////

	public Min( Value value ) {
		this.value = value;
	}
	
	///////////////////////////////
	
	public boolean isMinimizing() { return true; }
	
	public Value get() { return value; } 
	
	///////////////////////////////	

	@Override
	public int compareTo( Min< Value > other ) {

		return -value.compareTo( other.value );
	}
	
	///////////////////////////////

	@Override
	public int hashCode() { return value.hashCode(); }

	@Override
	public boolean equals( Object o ) {
		if( !( o instanceof Min ) )
			return false;
		
		@SuppressWarnings("unchecked")
		Min< Value > rhs = (Min< Value >)o; 
		return value.equals( rhs.value ); 
	}
	
	@Override
	public String toString() { return value.toString(); }
}

// End ///////////////////////////////////////////////////////////////
