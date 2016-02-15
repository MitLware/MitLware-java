package org.mitlware.mutable;

@FunctionalInterface
public interface Perturb<State> {

	public State apply( State incumbent );
	
	///////////////////////////////
	
	public static <S> Perturb<S> from( Create<S> create) {
		return (S ignore) -> create.apply();
	}

	public static <S> Perturb<S> 
	preferredFromLocality( Locality<S> locality, Prefer<S> prefer) {
		return (S s) -> Prefer.preferred( locality.apply(s).stream(), prefer ).orElse( s );
	}
	
	public static <S> Perturb<S> compose( Locality<S> locality, Move<S> move ) {
		return (S incumbent) -> move.apply(incumbent, locality.apply(incumbent).stream()).orElse(incumbent);		
	}
}

// End ///////////////////////////////////////////////////////////////

