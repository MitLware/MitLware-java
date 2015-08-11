package org.mitlware.java;

import com.google.java.contract.Ensures;

import scala.Tuple2;

@FunctionalInterface
public interface Accept< Sol, Env > {

	@Ensures( "result._1().equals( incumbent ) || result._1().equals( incoming )" )
	public Tuple2< Sol, Env > apply( Sol incumbent, Sol incoming, Env env );
}

// End ///////////////////////////////////////////////////////////////

