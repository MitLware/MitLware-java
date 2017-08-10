package org.mitlware.immutable;

import org.mitlware.util.Pair;

@FunctionalInterface
public interface IteratedPerturb< State, Env > extends Perturb< State, Env > {

	@Override
	public Pair< State, Env > apply( State incumbent, Env env );
}

// End ///////////////////////////////////////////////////////////////
