package org.mitlware.util;

import java.util.Objects;

public final class Pair< First, Second > {
	
	public final First _1;
	public final Second _2;
	
	///////////////////////////////
	
	public static < F, S > 
	Pair< F, S > of( F f, S s ) {
		return new Pair< F, S >( f, s );
	}
	
	public Pair( First f, Second s ) {
		this._1 = f;
		this._2 = s;
	}

	///////////////////////////////
	
	@Override
	public int hashCode() {
		return Objects.hash( _1, _2 );
	}

	@Override
	public boolean equals( Object o ) {
		if( !( o instanceof Pair< ?, ? > ) )
			return false;
		else {
			Pair< ?, ? > rhs = (Pair< ?, ? >)o;
			return _1.equals( rhs._1 ) && _2.equals( rhs._2 );
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer( "(" );
		buffer.append(_1).append(',').append(_2);
		return buffer.append(")").toString();
	}
}

// End ///////////////////////////////////////////////////////////////

