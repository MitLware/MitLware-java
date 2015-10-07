package org.mitlware.mutable;

import scala.collection.Seq;

@FunctionalInterface
public interface ChooseN<Entity> {

	public Seq<Entity> apply( Seq<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

