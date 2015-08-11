package org.mitlware.java;

import scala.Tuple2;

@FunctionalInterface
public interface Create< Entity, Env > {
	
	public Tuple2< Entity, Env > apply( Env env );
}

// End ///////////////////////////////////////////////////////////////
