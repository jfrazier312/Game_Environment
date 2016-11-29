// This entire file is part of my masterpiece.
// George Bernard
// See class documentation for masterpiece justification
//
// Note that with this masterpiece comes a change to all known 
// classes that extend AbstractObservable to change to a composition
// format.
// Changes to files: RobotController, Turtle, Environment, BackgroundInformation
package author.observe;

/**
 * This class performs the actual infrastructure of 
 * keeping track of observers and updating them when required.
 * Cannot be subclassed. Adds no new functionality so if one wants 
 * to extend Observable see AbstactObservable.
 * <P>
 * Key Principle: Favor Composition Over Inheritance
 * <P>
 * Trade Off: Created 6 lines of boiler plate code across all 
 * IObservable implementing classes
 * 
 * @see AbstractObservable
 * @see IObservable
 * @author George Bernard
 */
public final class ObserverRegistry extends AbstractObservable {
	
	/*
	 * Note that this class has no methods, 
	 * adds no new functionality to AbstractObservable, 
	 * but nevertheless can eliminate the need for Observable classes
	 * to extend AbstractObservable
	 */
	
	/**
	 * Constructs the ObserverRegistry
	 */
	public ObserverRegistry(){
		super();
		/*
		 * While not absolutely necessary (as it implicitly calls its super if not 
		 * specified) this makes clear that no new functionality is required.
		 */
	}
	
}
