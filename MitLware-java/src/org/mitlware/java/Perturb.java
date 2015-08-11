package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface Perturb< Sol, Env > {

	public Tuple2< Sol, Env > apply( Sol incumbent, Env env );
}

// End ///////////////////////////////////////////////////////////////

