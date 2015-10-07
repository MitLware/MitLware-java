package org.mitlware.java;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiFunction;

import org.mitlware.java.util.NoEnv;
import org.mitlware.java.value.DirectedValue;

/**
 * Partial order on states.
 * 
 * @author Jerry Swan
 */

public abstract class Order< State > 
implements BiFunction< State, State, Ordering >, Comparator< State > {

	@Override
	public abstract Ordering apply( State incoming, State incumbent );

	///////////////////////////////

	@Override
	public int compare( State incoming, State incumbent ) {
		final Ordering c = apply( incoming, incumbent );
		return c == Ordering.LT ? -1 : ( c == Ordering.GT ? 1 : 0 );
	}

	///////////////////////////////	
	
	public static < S > Order< S > from( Comparator< S > cmp ) {
		return new Order< S >() {
			@Override
			public Ordering apply(S incoming, S incumbent) {
				final int c = cmp.compare( incoming, incumbent ); 
				return c < 0 ? Ordering.LT : ( c > 0 ? Ordering.GT : Ordering.EQ );
			}};
	}
	
	public static < S, V extends DirectedValue< ?, V > > 
	Order< S > 
	from( Evaluate< S, V, NoEnv > eval ) {
		return new Order< S >() {
			@Override
			public Ordering apply( S incoming, S incumbent ) {
				final V incomingValue = eval.apply( incoming, NoEnv.VALUE )._1();				
				final V incumbentValue = eval.apply( incumbent, NoEnv.VALUE )._1();
				int cmp = incomingValue.compareTo( incumbentValue );
				assert( incomingValue.isMinimizing() == incumbentValue.isMinimizing() );
				if( incomingValue.isMinimizing() )
					cmp = -cmp;
				
				return cmp < 0 ? Ordering.LT : ( cmp > 0 ? Ordering.GT : Ordering.EQ );
			}};
	}
	
	///////////////////////////////

	public static < S > S 
	apply( Order< S > prefer, final S preferred, final S incoming ) {
		Ordering ord = prefer.apply( preferred, incoming ); 
		return ord == Ordering.LT ? incoming : preferred;
	}
	
	public static < S > S 
	apply( Optional< Order< S > > prefer, final S preferred, final S incoming ) {
		Ordering ord = prefer.map( 
				( Order< S > x ) -> x.apply( preferred, incoming ) 
				).orElse( Ordering.LT );
			return ord == Ordering.LT ? incoming : preferred;
	}
}

// End ///////////////////////////////////////////////////////////////
