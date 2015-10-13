package org.mitlware.mutable;

import java.util.stream.Stream;

@FunctionalInterface
public interface Select1<Entity> {

	public Entity apply( Stream<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

