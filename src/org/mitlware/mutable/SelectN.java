package org.mitlware.mutable;

import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface SelectN<Entity> {

	public List<Entity> apply( Stream<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

