package org.mitlware.mutable;

@FunctionalInterface
public interface IteratedPerturb< State > extends Perturb< State > {

	@Override
	public State apply( State incumbent );
}

// End ///////////////////////////////////////////////////////////////
