package org.mitlware.java;

import java.util.Optional;
import java.util.Random;

import scala.Tuple2;
import scala.collection.immutable.Seq;

//////////////////////////////////////////////////////////////////////

@FunctionalInterface
public interface Perturb< Sol, Env > {

	public Tuple2< Sol, Env > apply( Sol incumbent, Env env );
	
	public static < S, E > Perturb< S, E > 
	from( Locality< S, E > locality, Choose< S, E > choose ) {
		return new ChooseFromLocality< S, E >( locality, choose );
	}
	
	///////////////////////////////
	
	public static < S, E > Perturb< S, E > 
	from( final Create< S, E > create, final Random random ) {
		return new CreateAsPerturb< S, E >( create );
	}
	
	///////////////////////////////
	
	public static final class ChooseFromLocality< S, E > 
	implements Perturb< S, E > {

		private Locality< S, E > locality;
		private Choose< S, E > choose;
		
		///////////////////////////
		
		public ChooseFromLocality( Locality< S, E > locality, Choose< S, E > choose ) {
			this.locality = locality;
			this.choose = choose;
		}
		
		///////////////////////////
		
//		@Override
//		public Perturb< S, E > deepCopy() {
//			return new ChooseFromLocality< S, E >( locality.deepCopy(), choose.deepCopy() );
//		}

		@Override
		public Tuple2< S, E > apply(S x, E env ) {
			
			// TODO: write this succinctly
			Tuple2< Seq< S >, E > p1 = locality.apply( x, env );
			Tuple2< Optional< S >, E > p2 = choose.apply( p1._1(), p1._2() );
			if( p2._1().isPresent() )
				return new Tuple2< S, E >( p2._1().get(), p2._2() );
			else
				return new Tuple2< S, E >( x, env );
		}
	}
	
	///////////////////////////////

	public static final class CreateAsPerturb< S, E > 
	implements Perturb< S, E > {

		private Create< S, E > create;
		
		///////////////////////////
		
		public CreateAsPerturb( Create< S, E > create ) {
			this.create = create;
		}
		
		///////////////////////////
		
//		@Override
//		public Perturb< S, E > deepCopy() {
//			return new CreateAsPerturb< S, E >( create.deepCopy() );
//		}

		@Override
		public Tuple2< S, E > apply(S x, E env ) {
			return create.apply( env );
		}
	}
}

// End ///////////////////////////////////////////////////////////////

