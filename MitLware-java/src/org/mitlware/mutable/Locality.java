package org.mitlware.mutable;

import scala.collection.Seq;

@FunctionalInterface
public interface Locality<S> {

	public Seq<S> apply( S incumbent );	
}

// End ///////////////////////////////////////////////////////////////

