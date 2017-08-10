package org.mitlware.immutable;

import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Select1<Entity,Env> {

	public Pair< Entity, Env > apply( Stream<Entity> chooseFrom, Env env );
}

// End ///////////////////////////////////////////////////////////////

