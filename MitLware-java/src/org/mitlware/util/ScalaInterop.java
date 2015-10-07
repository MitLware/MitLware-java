package org.mitlware.util;

public final class ScalaInterop {

	public static <T> java.util.List<T> 
	scalaListToJava( scala.collection.Seq<T> seq ) {
		return scala.collection.JavaConversions.seqAsJavaList(seq);
	}
}

// End ///////////////////////////////////////////////////////////////

