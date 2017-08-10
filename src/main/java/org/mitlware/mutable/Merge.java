package org.mitlware.mutable;

import java.util.List;

@FunctionalInterface
public interface Merge<S> {

	public List<S> apply( List<S> incumbent, List<S> incoming );
}

// End ///////////////////////////////////////////////////////////////

