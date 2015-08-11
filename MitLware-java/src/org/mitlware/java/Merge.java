package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface Merge< Sol, Env > {
	
	public Tuple2< Sol, Env > 
	apply( Sol incumbent, Sol incoming, Env env );
}

// End ///////////////////////////////////////////////////////////////
