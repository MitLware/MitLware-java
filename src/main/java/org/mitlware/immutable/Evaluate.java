package org.mitlware.immutable;

import org.mitlware.SearchDirection;
import org.mitlware.immutable.Prefer;
import org.mitlware.util.Pair;

@FunctionalInterface
public interface Evaluate<Entity,Value,Env> {

	public Pair< Value, Env > apply( Entity x, Env e );

	public static < S, V, E > Evaluate< S, V, E > 
	fromMutable( org.mitlware.mutable.Evaluate< S, V > mutable ) {
		return ( S x, E env ) -> Pair.of( mutable.apply( x ), env );
	}
	
	///////////////////////////////
	
	public abstract class Directional<State,Value extends Comparable<Value>,Env> 
	implements Evaluate< State, Value, Env >, Prefer< State, Env > {

		private final SearchDirection direction;
		
		///////////////////////////

		public static < S, V extends Comparable<V>, E > 
		Evaluate.Directional< S, V, E > 
		lift( org.mitlware.mutable.Evaluate.Directional< S, V > mutable ) {
			return new Directional<S,V,E>(mutable.direction()) {
				@Override
				public Pair< V, E > apply( S x, E env ) {
					return Pair.of( mutable.apply( x ), env );
				}
			};
		}
		
		///////////////////////////

		public Directional( SearchDirection dir ) {
			this.direction = dir;
		}
		
		public SearchDirection direction() { return direction; }

		@Override
		public abstract Pair< Value, Env > apply( State x, Env env );

		///////////////////////////
		
		public boolean isMinimizing() { return direction() == SearchDirection.MINIMIZING; }
		
		@Override		
		public Pair< State, Env > 
		prefer( State left, State right, Env env ) {
			final Pair< Value, Env > leftEval = apply( left, env );
			env = leftEval._2;
			final Pair< Value, Env > rightEval = apply( right, env );
			env = rightEval._2;
			
			final int cmp = leftEval._1.compareTo(rightEval._1);
			
			if( cmp < 0 ) {
				return isMinimizing() ? Pair.of( left, env ) : Pair.of( right, env );  
			}
			else if( cmp > 0 ) {
				return isMinimizing() ? Pair.of( right, env ) : Pair.of( left, env );				
			}
			else {
				return Pair.of( left, env );
			}
		}
		
		////////////////////////////////
		
		public static <S,V extends Comparable<V>,E> 
		Directional<S,V,E> 
		minimizing( Evaluate<S,V,E> eval ) {
			return new Directional<S,V,E>( SearchDirection.MINIMIZING ) {
				@Override
				public Pair< V, E > apply( S x, E env ) { return eval.apply( x, env ); }
			};
		}
		
		public static <S,V extends Comparable<V>,E> 
		Directional<S,V,E> 
		maximizing( Evaluate<S,V,E> eval ) {
			return new Directional<S,V,E>( SearchDirection.MAXIMIZING ) {
				@Override				
				public Pair< V, E > apply( S x, E env ) { return eval.apply( x, env ); }
			};
		}
	}
}

// End ///////////////////////////////////////////////////////////////

