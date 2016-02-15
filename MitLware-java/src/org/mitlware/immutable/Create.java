package org.mitlware.immutable;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Create<Entity,Env> {

	public Pair<Entity,Env> apply( Env env );
}

// End ///////////////////////////////////////////////////////////////

