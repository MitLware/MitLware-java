# MitLware-java
This repository contains Java interfaces for 'Metaheuristics in the Large', 
corresponding to the most common metaheuristic components (```Pertub,Accept,Evaluate``` etc).

There are two parallel sets of interfaces: mutable and immutable. The distinction is that implementations of ```immutable``` interfaces are required to be pure functions (i.e. without side-effects). In contrast, implementations of ```mutable``` interfaces may be stateful, e.g. successive applications of a mutable ```Accept``` to the same arguments are allowed to yield different results.

Stateful components are traditional practice, but have the following drawbacks:
* They pose difficulties for algorithm configuration tools that assemble components automatically, since hidden state means that 
the configurator:
 * Cannot automatically manage dependencies between components. 
 * Cannot readily determine which state changes lead to a different quality of outcome.
* Mutable components do not provide the appropriate interface for accessing (typically stateless) Service-Oriented Architectures. 

There is a direct correspondence between the function signatures of mutable and immutable components 
(i.e. one can be procedurally derived from the other). 

For example, the mutable component for ```Perturb``` looks like this:
```
package org.mitlware.mutable;

public interface Perturb<Sol> {
	public Sol apply( Sol incumbent );
}
```

As is common practice when implementing pure functions, any external state required by a component is instead passed/returned as an additional ```Env``` ('environment') parameter. The corresponding immutable version of ```Perturb``` looks like this:

```
package org.mitlware.mutable;

public interface Perturb<Sol> {
	public Pair<Sol,Env> apply( Sol incumbent, Env env );
}
```

An immutable version is provided for each mutable component.

