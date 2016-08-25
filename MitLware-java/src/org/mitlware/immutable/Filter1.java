package org.mitlware.immutable;

import java.util.Optional;
import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Filter1<Entity,Env> {

	public Pair< Optional<Entity>, Env > apply( Stream<Entity> chooseFrom, Env env );
}

// End ///////////////////////////////////////////////////////////////

