package org.mitlware.mutable;

import java.util.stream.Stream;

@FunctionalInterface
public interface FilterN<Entity> {

	public Stream<Entity> apply( Stream<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

