package org.mitlware.java.util;

public final class Mutable<T> {

	private T value;
	
	///////////////////////////////
	
	public Mutable( T value ) {
		if( value == null )
			throw new IllegalArgumentException();
		
		this.value = value;
	}
	
	public T get() { return value; }

	public void set( T newValue ) {
		value = newValue;
	}
	
	///////////////////////////////

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals( Object o ) {
		if( ! ( o instanceof Mutable ) )
			return false;
		
		@SuppressWarnings("rawtypes")
		Mutable rhs = (Mutable)o;
		return value.equals( rhs.value );
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + value + ")";
	}
}

// End ///////////////////////////////////////////////////////////////

