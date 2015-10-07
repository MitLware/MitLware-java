package org.mitlware.mutable;

@FunctionalInterface
public interface Create<Entity,Env> {

	public Entity apply( Env env );
}

// End ///////////////////////////////////////////////////////////////

