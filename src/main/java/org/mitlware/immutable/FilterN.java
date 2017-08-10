package org.mitlware.immutable;

import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface FilterN<Entity, Env> {

	public Pair< Stream<Entity>, Env > apply( Stream<Entity> chooseFrom, int n, Env env );
}

// End ///////////////////////////////////////////////////////////////

