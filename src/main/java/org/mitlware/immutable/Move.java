package org.mitlware.immutable;

import java.util.Optional;
import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Move<State, Env> {

	public Optional<Pair<State,Env>> apply( State incumbent, Stream<Pair<State,Env>> locality, Env env );
	
	///////////////////////////////
	
	/******
	public static <S,V extends Comparable<V>,E> 
	Move<S,E>	best( Evaluate.Directional<S,V,E> eval ) {
		return ( S incumbent, Stream<Pair<S,E>> locality, E env ) -> { 

			Optional<S> max = locality.max( new Comparator<S>() {
				public int compare(S a, S b) {
					return eval.apply( a, env ).compareTo(eval.apply(b, env));
				}} );
			
			Optional<S> best = max.map( (S s, E e) -> Prefer.preferred( incumbent, s, eval ));
			return Optional.of( best.orElse( incumbent ) );
		};
	}

	public static <S,V extends Comparable<V>> 
	Move<S>	bestImproving( Evaluate.Directional<S,V> eval ) {
		return ( S incumbent, Stream<S> locality ) -> { 

			Optional<S> max = locality.max( new Comparator<S>() {
				public int compare(S a, S b) {
					return eval.apply( a ).compareTo(eval.apply(b));
				}} );
			
			return max.filter( (S incoming) -> eval.prefer( incumbent, incoming ) == Preference.PREFER_RIGHT );
		};
	}
	******/
}

// End ///////////////////////////////////////////////////////////////

