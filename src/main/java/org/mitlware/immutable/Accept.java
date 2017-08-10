package org.mitlware.immutable;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Accept<State, Env> {

	public Pair< State, Env > apply( State incumbent, State incoming, Env env );
	
	///////////////////////////////

	public static < S, E > Accept< S, E > always() {
		return ( S incumbent, S incoming, E env ) -> Pair.of( incoming, env );
	}
	
	public static < S, E > Accept< S, E > 
	fromMutable( org.mitlware.mutable.Accept< S > mutable ) {
		return ( S incumbent, S incoming, E env ) -> Pair.of( mutable.apply( incumbent, incoming ), env );
	}
}

// End ///////////////////////////////////////////////////////////////

