package org.mitlware.mutable;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Locality<State> {

	public List<State> apply( State incumbent );
	
	///////////////////////////////
	
	public static <S> Locality<S> from( List< Perturb< S > > operators ) {
		return new Locality< S >() {
			public List<S> apply( S incumbent ) {
				return operators.stream().map( o -> o.apply( incumbent ) ).collect(Collectors.toList());
			}
		};
	}
}

// End ///////////////////////////////////////////////////////////////

