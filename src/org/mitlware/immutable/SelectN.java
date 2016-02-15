package org.mitlware.immutable;

import java.util.stream.Stream;

import scala.collection.Seq;

@FunctionalInterface
public interface SelectN<Entity> {

	public Seq<Entity> apply( Stream<Entity> chooseFrom, int n );
}

// End ///////////////////////////////////////////////////////////////

