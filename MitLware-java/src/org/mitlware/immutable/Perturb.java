package org.mitlware.immutable;

import java.util.List;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Perturb<State, Env>  {

	public Pair< State, Env > apply( State incumbent, Env env );
	
	///////////////////////////////
	
	public static <S,Env> Perturb<S,Env> from( Create<S,Env> create) {
		return (S ignore,Env env) -> create.apply(env);
	}
	
//	public static <S,Env> Perturb<S,Env> compose( Locality<S> locality, Move<S> move ) {
//		return (S incumbent) -> move.apply(incumbent, locality.apply(incumbent)).orElse(incumbent);		
//	}
	
	public static < S, E > Perturb< S, E > 
	fromMutable( org.mitlware.mutable.Perturb< S > mutable ) {
		return ( S incumbent, E env ) -> Pair.of( mutable.apply( incumbent ), env );
	}
	
	public static <S,E> Perturb<S,E> 
	preferredFromLocality( Locality<S,E> locality, Prefer<S,E> prefer) {
		return (S s, E e) -> {
			Pair<List<S>,E> p = locality.apply(s,e);
			return Pair.of( Prefer.preferred( p._1.stream(), prefer, p._2 )._1.orElse( s ), p._2 );
		};
	}
}

// End ///////////////////////////////////////////////////////////////

