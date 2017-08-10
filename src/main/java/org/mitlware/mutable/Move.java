package org.mitlware.mutable;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface Move<State> {

	public Optional<State> apply( State incumbent, Stream<State> locality );
	
	///////////////////////////////
	
	public static <S,V extends Comparable<V>> 
	Move<S>	best( Evaluate.Directional<S,V> eval ) {
		return ( S incumbent, Stream<S> locality ) -> { 

			Optional<S> max = locality.max( new Comparator<S>() {
				public int compare(S a, S b) {
					return eval.apply( a ).compareTo(eval.apply(b));
				}} );
			
			Optional<S> best = max.map( (S s) -> eval.prefer( incumbent, s ));
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
			
			return max.filter( (S incoming) -> eval.prefer( incumbent, incoming ) == incoming );
		};
	}
}

// End ///////////////////////////////////////////////////////////////

