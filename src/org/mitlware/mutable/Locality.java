package org.mitlware.mutable;

import java.util.stream.Stream;

@FunctionalInterface
public interface Locality<State> {

	public Stream<State> apply( State incumbent );
	
	///////////////////////////////
	
	public static <S> Locality<S> from( Stream< Perturb< S > > operators ) {
		return new Locality< S >() {
			public Stream<S> apply( S incumbent ) {
				return operators.map( o -> o.apply( incumbent ) );
			}
		};
	}
}

// End ///////////////////////////////////////////////////////////////

