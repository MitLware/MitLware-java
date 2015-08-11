package org.mitlware.java;

import com.google.java.contract.Ensures;

import scala.Tuple2;
import scala.collection.immutable.Seq;

@FunctionalInterface
public interface Select< Entity, Env > {

	@Ensures( "result._1().size() == numSelections" )	
	public Tuple2< Seq< Entity >, Env > 
	apply( Seq< Entity > chooseFrom, int numSelections, Env env );
}

// End ///////////////////////////////////////////////////////////////

