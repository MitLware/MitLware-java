package org.mitlware.java;

import java.util.Comparator;

import com.google.java.contract.Ensures;

import scala.Tuple2;

//////////////////////////////////////////////////////////////////////

@FunctionalInterface
public interface Accept< Sol, Env > {

	@Ensures( "result._1().equals( incumbent ) || result._1().equals( incoming )" )
	public Tuple2< Sol, Env > apply( Sol incumbent, Env incumbentEnv, Sol incoming, Env incomingEnv );
	
	public static < S, E > Accept< S, E > 
	always() {
		return new Accept< S, E >() {
			public Tuple2< S, E > 
			apply(S incumbent, E incumbentEnv, S incoming, E incomingEnv ) { 
				return new Tuple2<S,E>( incoming, incomingEnv ); 
			}
			// public Accept<S,E> deepCopy() { return this; }
		};
	}
	
	public static < S, E > Accept< S, E > 
	improving( Order< S > prefer ) {
		final boolean stable = true;
		return from( prefer, stable );
	}

	public static < S, E > Accept< S, E > 
	improvingOrEqual( Order< S > prefer ) {
		final boolean stable = false;
		return from( prefer, stable );
	}
	
	///////////////////////////////	

	static < S, E > Accept< S, E > 
	from( Order< S > prefer, boolean stable ) {
		return new Accept< S, E >() {
			@Override
			public Tuple2< S, E > 
			apply(S incumbent, E incumbentEnv, S incoming, E incomingEnv ) { 
				Ordering ord = prefer.apply(incoming, incumbent);
				if( ord == Ordering.LT )
					return new Tuple2<S,E>( incumbent, incumbentEnv );
				else if( ord == Ordering.GT )
					return new Tuple2<S,E>( incoming, incomingEnv );
				else {
					assert( ord == Ordering.EQ );
					return stable 
						? new Tuple2<S,E>( incumbent, incumbentEnv ) 
						: new Tuple2<S,E>( incoming, incomingEnv );
				}
			}

			// @Override
			// public Accept<S,E> deepCopy() { return this; }
		};
	}
	
	///////////////////////////////
	
	public static < S, E > Accept<S,E> 
	from( Comparator< S > cmp, boolean stable ) {
		return new Accept< S, E >() {
			@Override
			public Tuple2< S, E > 
			apply(S incumbent, E incumbentEnv, S incoming, E incomingEnv ) { 
				final int c = cmp.compare(incoming, incumbent);
				if( c < 0 ) return new Tuple2<S,E>( incumbent, incumbentEnv );
				else if( c > 0 ) return new Tuple2<S,E>( incoming, incomingEnv );
				else {
					assert( c == 0 );
					return stable ? new Tuple2<S,E>( incumbent, incumbentEnv ) : new Tuple2<S,E>( incoming, incomingEnv );
				}
			}

			// @Override
			// public Accept<S,E> deepCopy() { return this; }
		};
	}
}

// End ///////////////////////////////////////////////////////////////

