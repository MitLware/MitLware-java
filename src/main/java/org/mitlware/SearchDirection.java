package org.mitlware;

public enum SearchDirection {
	
	MINIMIZING {
		public int multiplier() { return -1; }		
	}, 
	MAXIMIZING {
		public int multiplier() { return 1; }		
	};
	
	public abstract int multiplier();
}

// End ///////////////////////////////////////////////////////////////

