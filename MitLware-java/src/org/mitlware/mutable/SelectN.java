package org.mitlware.mutable;

import scala.collection.Seq;

@FunctionalInterface
public interface SelectN<Entity> {

	public Seq<Entity> apply( Seq<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

