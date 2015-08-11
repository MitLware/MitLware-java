package org.mitlware.java;

import java.util.Optional;

import com.google.java.contract.Ensures;

import scala.Tuple2;
import scala.collection.immutable.Seq;

@FunctionalInterface
public interface Choose< Entity, Env > {

	public Tuple2< Optional< Entity >, Env > 
	apply( Seq< Entity > chooseFrom, Env env );
}

// End ///////////////////////////////////////////////////////////////

