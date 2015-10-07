package org.mitlware.mutable;

import jeep.lang.Diag;

import org.mitlware.Preference;

@FunctionalInterface
public interface Accept<State> {

	public State apply( State incumbent, State incoming );
	
	///////////////////////////////
	
	public static < S > Accept< S > always() {
		return ( S incumbent, S incoming ) -> incoming;
//		{
//			Diag.println( "always " + incumbent + " " +  incoming );
//			return incoming; 
//		};
	}
	
	public static < S > Accept< S > 
	improving( Prefer< S > prefer ) {
		return ( S incumbent, S incoming ) ->  
			prefer.prefer( incumbent, incoming ) == Preference.PREFER_RIGHT ? incoming : incumbent; 
	}

	public static < S > Accept< S > 
	improvingOrEqual( Prefer< S > prefer ) {
		return ( S incumbent, S incoming ) ->  
			prefer.prefer( incumbent, incoming ) == Preference.PREFER_LEFT ? incumbent : incoming; 
	}
}

// End ///////////////////////////////////////////////////////////////

