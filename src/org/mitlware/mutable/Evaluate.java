package org.mitlware.mutable;

import jeep.lang.Diag;

import org.mitlware.Preference;
import org.mitlware.SearchDirection;

@FunctionalInterface
public interface Evaluate<Entity,Value> {

	public Value apply( Entity x );

	///////////////////////////////
	
	public abstract class Directional<State,Value extends Comparable<Value>> 
	implements Evaluate< State, Value >, Prefer< State > {

		public abstract SearchDirection direction();
		
		public boolean isMinimizing() { return direction() == SearchDirection.MINIMIZING; }
		
		@Override
		public abstract Value apply( State x );
		
		@Override		
		public Preference prefer( State incumbent, State incoming ) {
			
			Diag.println( "lval " + apply( incumbent ) );
			Diag.println( "rval " + apply( incoming ) );
			
			final int cmp = apply( incumbent ).compareTo( apply( incoming ) );
			if( cmp == 0 )
				return Preference.NO_PREFERENCE;
			else if( isMinimizing() ) {
				if( cmp < 0 )
					return Preference.PREFER_LEFT;
				else
					return Preference.PREFER_RIGHT;					
			}
			else {
				assert( !isMinimizing() );
Diag.println( );				
				if( cmp < 0 )
					return Preference.PREFER_RIGHT;
				else
					return Preference.PREFER_LEFT;					
			}
		}
		
		////////////////////////////////
		
		public static <S,V extends Comparable<V>> Directional<S,V> 
		minmizing( Evaluate<S,V> eval ) {
			return new Directional<S,V>() {
				public SearchDirection direction() { return SearchDirection.MINIMIZING; }
				public V apply( S x ) { return eval.apply( x ); }
			};
		}
		
		public static <S,V extends Comparable<V>> Directional<S,V> 
		maximizing( Evaluate<S,V> eval ) {
			return new Directional<S,V>() {
				public SearchDirection direction() { return SearchDirection.MAXIMIZING; }
				public V apply( S x ) { return eval.apply( x ); }
			};
		}
	}
}

// End ///////////////////////////////////////////////////////////////

