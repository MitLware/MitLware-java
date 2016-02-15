package org.mitlware.mutable;

import java.util.Comparator;

import org.mitlware.SearchDirection;

@FunctionalInterface
public interface Evaluate<Entity,Value> {

	public Value apply( Entity x );

	///////////////////////////////
	
	public abstract class Directional<State,Value extends Comparable<Value>> 
	implements Evaluate< State, Value >, Prefer< State >, Comparator< State > {
		
		private final SearchDirection direction;
		
		///////////////////////////
		
		public Directional( SearchDirection dir ) {
			this.direction = dir;
		}
		
		public SearchDirection direction() { return direction; }

		@Override
		public abstract Value apply( State x );

		///////////////////////////
		
		public boolean isMinimizing() { return direction() == SearchDirection.MINIMIZING; }
		
		@Override
		public int compare(State left, State right) {
			final Value leftEval = apply( left );
			final Value rightEval = apply( right );
			return leftEval.compareTo(rightEval) * ( isMinimizing() ? -1 : 1 );			
		}
		
		@Override		
		public State prefer( State left, State right ) {
			final int cmp = compare(left,right);			
			return cmp < 0 ? right : cmp > 0 ? left : right;   
		}
		
		////////////////////////////////
		
		public static <S,V extends Comparable<V>> 
		Directional<S,V> 
		minimizing( Evaluate<S,V> eval ) {
			return new Directional<S,V>( SearchDirection.MINIMIZING ) {
				@Override
				public V apply( S x ) { return eval.apply( x ); }
			};
		}
		
		public static <S,V extends Comparable<V>> 
		Directional<S,V> 
		maximizing( Evaluate<S,V> eval ) {
			return new Directional<S,V>( SearchDirection.MAXIMIZING ) {
				@Override				
				public V apply( S x ) { return eval.apply( x ); }
			};
		}
	}
}

// End ///////////////////////////////////////////////////////////////

