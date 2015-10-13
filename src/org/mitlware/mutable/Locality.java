package org.mitlware.mutable;

import java.util.stream.Stream;

@FunctionalInterface
public interface Locality<S> {

	public Stream<S> apply( S incumbent );	
}

// End ///////////////////////////////////////////////////////////////

