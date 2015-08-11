package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface Recombine2< Sol, Env > {
	
	public Tuple2< Sol, Env > apply( Sol parent1, Sol parent2, Env env );
}

// End ///////////////////////////////////////////////////////////////
