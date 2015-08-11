package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface IsFinished< Sol, Env > {
	
	public Tuple2< Boolean, Env > apply( Sol s, Env env );
}

// End ///////////////////////////////////////////////////////////////
