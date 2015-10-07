package org.mitlware.mutable;

import scala.collection.Seq;

@FunctionalInterface
public interface Select1<Entity> {

	public Entity apply( Seq<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

