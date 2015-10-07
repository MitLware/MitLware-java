package org.mitlware.mutable;

import scala.collection.Seq;

@FunctionalInterface
public interface Merge<S> {

	public Seq<S> apply( Seq<S> incumbent, Seq<S> incoming );
}

// End ///////////////////////////////////////////////////////////////

