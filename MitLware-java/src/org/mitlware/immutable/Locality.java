package org.mitlware.immutable;

import java.util.ArrayList;
import java.util.List;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Locality<State,Env> {

	public Pair<List<State>,Env> apply( State incumbent, Env env );
	
	///////////////////////////////
	
	public static <S,E> Locality<S,E> from( List< Perturb< S,E > > operators ) {
		return new Locality< S,E >() {
			public Pair<List<S>,E> apply( S incumbent, E env ) {
				List<S> neighbours = new ArrayList<>();
				for( Perturb< S, E > op : operators ) {
					Pair< S, E > p = op.apply( incumbent, env );
					neighbours.add( p._1 );
					env = p._2;
				}
				return Pair.of( neighbours, env );
			}
		};
	}
	
	///////////////////////////////
	
	public static < S, E > Locality< S, E > 
	lift( org.mitlware.mutable.Locality< S > mutable ) {
		return ( S incumbent, E env ) -> Pair.of( mutable.apply( incumbent ), env );
	}
}

// End ///////////////////////////////////////////////////////////////

