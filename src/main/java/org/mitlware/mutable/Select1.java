package org.mitlware.mutable;

import java.util.List;

@FunctionalInterface
public interface Select1<Entity> {

	public Entity apply( List<Entity> chooseFrom );
}

// End ///////////////////////////////////////////////////////////////

