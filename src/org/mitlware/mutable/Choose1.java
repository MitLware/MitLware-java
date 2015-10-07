package org.mitlware.mutable;

import java.util.Optional;

import scala.collection.Seq;

@FunctionalInterface
public interface Choose1<Entity> {

	public Optional<Entity> apply( Seq<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

