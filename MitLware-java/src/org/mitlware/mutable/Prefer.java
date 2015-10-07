package org.mitlware.mutable;

import org.mitlware.Preference;

@FunctionalInterface
public interface Prefer< State > {

	public Preference prefer(State left,State right);
	
	///////////////////////////////
	
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
	
	/*********
	///////////////////////////////
	
	public static <E,V extends Comparable<V>> Prefer<E> 
	from( Evaluate.Directional<E,V> eval ) {
		return (E left, E right) -> {
			final V leftEval = eval.apply( left );
			final V rightEval = eval.apply( right );
			
			final int cmp = leftEval.compareTo(rightEval);
			if( cmp < 0 ) {
				return eval.isMinimizing() ? 
					Preference.PREFER_LEFT : Preference.PREFER_RIGHT;  
			}
			else if( cmp > 0 ) {
				return eval.isMinimizing() ? 
					Preference.PREFER_RIGHT : Preference.PREFER_LEFT;				
			}
			else {
				return Preference.NO_PREFERENCE;
			}
		};
	}
	*********/
}

// End ///////////////////////////////////////////////////////////////

