package org.mitlware.immutable;

@FunctionalInterface
public interface Recombine2<S> {

	public S apply( S a, S b );
}

// End ///////////////////////////////////////////////////////////////

