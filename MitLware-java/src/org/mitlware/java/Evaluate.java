package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface Evaluate< Sol, Value, Env > {
	
	public Tuple2< Value, Env > apply( Sol s, Env env );
}

// End ///////////////////////////////////////////////////////////////
