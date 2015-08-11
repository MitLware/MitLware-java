package org.mitlware.java;

import scala.Tuple2;
import scala.collection.immutable.Seq;

@FunctionalInterface
public interface Locality< Sol, Env > {

	public Tuple2< Seq< Sol >, Env > apply( Sol incumbent, Env env );
}

// End ///////////////////////////////////////////////////////////////

