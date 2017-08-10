package org.mitlware.immutable;

import java.util.List;
import java.util.stream.Stream;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface SelectN<Entity,Env> {

	public Pair<List<Entity>,Env> apply( Stream<Entity> chooseFrom, int n, Env env );
}

// End ///////////////////////////////////////////////////////////////

