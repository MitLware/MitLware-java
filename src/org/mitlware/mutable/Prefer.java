package org.mitlware.mutable;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

// import org.mitlware.Preference;

@FunctionalInterface
public interface Prefer< State > {

	public State prefer(State left,State right);
	
	public static <S> Optional<S> preferred( Stream<S> s, Prefer<S> prefer ) {
		return s.reduce( (S a, S b) -> prefer.prefer(a,b) );
	}
	
	///////////////////////////////
	
	/********
	public static <S> S preferred(S incumbent, S incoming, Prefer<S> prefer) {
		if( prefer.prefer( incumbent, incoming ) == Preference.PREFER_LEFT )
			return incumbent;
		else
			return incoming;			
	}
	
	public static < E > Prefer< E > 
	preferStable( Prefer< E > base ) {
		return ( E incumbent, E incoming ) -> {
			Preference p = base.prefer( incumbent, incoming );
			if( p == Preference.NO_PREFERENCE )
				return Preference.PREFER_LEFT;
			else
				return p;
		};
	}
	********/
	
	///////////////////////////////
	
	public static <E,V extends Comparable<V>> Prefer<E> 
	from( Evaluate.Directional<E,V> eval ) {
		return (E left, E right) -> eval.prefer(left,right);
	}
}

// End ///////////////////////////////////////////////////////////////

