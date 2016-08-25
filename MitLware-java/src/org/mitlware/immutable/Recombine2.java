package org.mitlware.immutable;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface Recombine2<Sol,Env> {

	public Pair< Sol, Env > apply( Sol a, Sol b, Env env );
}

// End ///////////////////////////////////////////////////////////////

