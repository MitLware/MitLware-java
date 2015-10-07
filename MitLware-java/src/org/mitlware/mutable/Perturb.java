package org.mitlware.mutable;

@FunctionalInterface
public interface Perturb<State> {

	public State apply( State incumbent );
	
	///////////////////////////////
	
	public static <S,Env> Perturb<S> from( Create<S,Env> create, Env env) {
		return (S ignore) -> create.apply(env);
	}
	
	public static <S> Perturb<S> compose( Locality<S> locality, Move<S> move ) {
		return (S incumbent) -> move.apply(incumbent, locality.apply(incumbent)).orElse(incumbent);		
	}
}

// End ///////////////////////////////////////////////////////////////

