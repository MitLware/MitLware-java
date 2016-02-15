package org.mitlware.mutable;

import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface Choose1<Entity> {

	public Optional<Entity> apply( Stream<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

