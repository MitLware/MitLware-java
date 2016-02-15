package org.mitlware.immutable;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Prefer< State, Env > {

	public Pair< State, Env > prefer(State left,State right, Env env );
	
	public static <S,V extends Comparable<V>,E> Prefer<S,E> 
	from( Evaluate.Directional<S,V,E> eval ) {
		return (S left, S right,E env) -> eval.prefer(left,right,env);
	}
	
	public static < S, E > Prefer< S, E > 
	lift( org.mitlware.mutable.Prefer< S > mutable ) {
		return ( S left, S right, E env ) -> Pair.of( mutable.prefer( left, right ), env );
	}
	
	///////////////////////////////
	
	static final class Reduce<S,E> implements BinaryOperator< S > {
		private Prefer<S,E> prefer;
		private E env;
		public Reduce(E env,Prefer<S,E> prefer) { this.env = env; this.prefer = prefer; }
		
		@Override
		public S apply(S a, S b) {
			Pair< S,E> p = prefer.prefer(a,b,env); 
			env = p._2;
			return p._1;
		}
	}
	
	public static <S,E> 
	Pair<Optional<S>,E> 
	preferred( Stream<S> s, Prefer<S,E> prefer, E e ) {
		return Pair.of( s.reduce( new Reduce<S,E>(e,prefer) ), e );
	}
	
	///////////////////////////////
	
	/*******
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
	*******/
	
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

