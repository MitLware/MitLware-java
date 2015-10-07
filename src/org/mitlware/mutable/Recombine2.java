package org.mitlware.mutable;

@FunctionalInterface
public interface Recombine2<S> {

	public S apply( S a, S b );
}

// End ///////////////////////////////////////////////////////////////

