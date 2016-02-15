package org.mitlware.immutable;

import java.util.Optional;
import java.util.stream.Stream;

import scala.collection.Seq;

@FunctionalInterface
public interface Choose1<Entity> {

	public Optional<Entity> apply( Stream<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

