package org.mitlware.mutable;

@FunctionalInterface
public interface IsFinished<State> {

	public Boolean apply( State incumbent );
}

// End ///////////////////////////////////////////////////////////////

