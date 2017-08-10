package org.mitlware.immutable;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface IsFinished<State,Env> {

	public Pair< Boolean, Env > apply( State incumbent, Env env );
}

// End ///////////////////////////////////////////////////////////////

