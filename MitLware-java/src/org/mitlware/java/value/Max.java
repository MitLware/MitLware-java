package org.mitlware.java.value;


//////////////////////////////////////////////////////////////////////

public final class Max< Value extends Comparable< Value > > 
implements DirectedValue< Value, Max< Value > > {

	private final Value value; 
	
	///////////////////////////////

	public Max( Value value ) {
		this.value = value;
	}
	
	///////////////////////////////
	
	public boolean isMinimizing() { return false; }
	
	public Value get() { return value; } 
	
	///////////////////////////////	

	@Override
	public int compareTo( Max< Value > other ) {

		return value.compareTo( other.value );
	}
	
	///////////////////////////////

	@Override
	public int hashCode() { return value.hashCode(); }

	@Override
	public boolean equals( Object o ) {
		if( !( o instanceof Max ) )
			return false;
		
		@SuppressWarnings("unchecked")
		Max< Value > rhs = (Max< Value >)o; 
		return value.equals( rhs.value ); 
	}
	
	@Override
	public String toString() { return value.toString(); }
}

// End ///////////////////////////////////////////////////////////////
