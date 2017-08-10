package org.mitlware.mutable;

@FunctionalInterface
public interface Accept<State> {

	public State apply( State incumbent, State incoming );
	
	///////////////////////////////
	
	public static < S > Accept< S > always() {
		return ( S incumbent, S incoming ) -> incoming;
	}
	
	public static < S, V extends Comparable< V > > 
	Accept< S > 
	improving( Evaluate.Directional< S, V > eval ) {
		return ( S incumbent, S incoming ) -> eval.compare( incumbent, incoming ) < 0 ? incoming : incumbent;    
	}

	public static < S, V extends Comparable< V > > 
	Accept< S > 
	improvingOrEqual( Evaluate.Directional< S, V > eval ) {
		return ( S incumbent, S incoming ) -> eval.compare( incumbent, incoming ) <= 0 ? incoming : incumbent; 
	}
}

// End ///////////////////////////////////////////////////////////////

