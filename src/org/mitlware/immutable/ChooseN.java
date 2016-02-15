package org.mitlware.immutable;

import java.util.stream.Stream;

@FunctionalInterface
public interface ChooseN<Entity> {

	public Stream<Entity> apply( Stream<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

