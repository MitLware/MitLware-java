package org.mitlware.mutable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.mitlware.Preference;
import org.mitlware.util.ScalaInterop;

import scala.collection.Seq;

@FunctionalInterface
public interface Move<State> {

	public Optional<State> apply( State incumbent, Seq<State> locality );
	
	///////////////////////////////
	
	public static <S,V extends Comparable<V>> 
	Move<S>	best( Evaluate.Directional<S,V> eval ) {
		return ( S incumbent, Seq<S> locality ) -> { 

			List<S> asJava = ScalaInterop.scalaListToJava( locality );
			Optional<S> max = asJava.stream().max( new Comparator<S>() {
				public int compare(S a, S b) {
					return eval.apply( a ).compareTo(eval.apply(b));
				}} );
			
			Optional<S> best = max.map( (S s) -> Prefer.preferred( incumbent, s, eval ));
			return Optional.of( best.orElse( incumbent ) );
		};
	}

	public static <S,V extends Comparable<V>> 
	Move<S>	bestImproving( Evaluate.Directional<S,V> eval ) {
		return ( S incumbent, Seq<S> locality ) -> { 

			List<S> asJava = ScalaInterop.scalaListToJava( locality );
			Optional<S> max = asJava.stream().max( new Comparator<S>() {
				public int compare(S a, S b) {
					return eval.apply( a ).compareTo(eval.apply(b));
				}} );
			
			return max.filter( (S incoming) -> eval.prefer( incumbent, incoming ) == Preference.PREFER_RIGHT );
		};
	}
}

// End ///////////////////////////////////////////////////////////////

