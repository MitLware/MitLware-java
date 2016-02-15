package org.mitlware.util;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

/**
 * FList is an immutable list. 
 */

public final class FList<T> {

	private java.util.List<T> list = new ArrayList<>();
	
	///////////////////////////////
	
	private static FList<?> nil_ = new FList<>();
	
	@SuppressWarnings("unchecked")
	public static <T> FList<T> nil() { return (FList<T>)nil_; }

	@SafeVarargs
	public static <T> FList<T> apply(T... ta) {
		FList<T> result = new FList<>();
		for( T t: ta )
			result.list.add(t);
		return result;
	}

	public FList<T> cons(T t) {
		FList<T> result = new FList<>();
	    result.list.add(t);
	    result.list.addAll(list);
	    return result;
	}

	///////////////////////////////
	
	public <U> U foldRight(U seed, Function<T, Function<U, U>> f) {
		U result = seed;
		for (int i = list.size() - 1; i >= 0; i--)
	      result = f.apply(list.get(i)).apply(result);
	    
	    return result;
	}

	public <U> FList<U> map(Function<T, U> f) {
		FList<U> result = new FList<>();
	    for (T t : list) {
	      result.list.add(f.apply(t));
	    }
	    return result;
	}

	public FList<T> filter(Function<T, Boolean> f) {
		FList<T> result = new FList<>();
	    for (T t : list) {
	      if (f.apply(t)) {
	        result.list.add(t);
	      }
	    }
	    return result;
	}

	public Optional<T> findFirst() {
		return list.size() == 0 ? Optional.empty() : Optional.of(list.get(0));
	}

	///////////////////////////////
	
	@Override
	public int hashCode() { return list.hashCode(); }

	@Override
	public boolean equals(Object o) { 
		 if( !( o instanceof FList<?>) )
			 return false;
		 FList<?> rhs = (FList<?>)o;
		 return list.equals(rhs.list);
	}
	
	@Override
	public String toString() {
		if( list.isEmpty() )
			return "Nil";
		else {
			StringBuilder s = new StringBuilder("[");
			for( int i=0; i<list.size(); ++i ) {
				s.append(list.get(i));
				if( i < list.size() - 1 )
					s.append(", ");
			}
	    
			return s.toString();
		}
	}
}

// End ///////////////////////////////////////////////////////////////

