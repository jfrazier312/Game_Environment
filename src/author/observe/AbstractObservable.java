// This entire file is part of my masterpiece.
// George Bernard
// Please note that Hannah actually made this class, but I refactored it to the 
// shorter, clearer method implementations

package author.observe;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class that others can extend to reduce code reuse in Observer pattern
 * <p>
 * REFACTORED:
 * ghb5 - converted list to collection, no reason to use index in remove method
 * <p>
 * In the future, this functionality may better be served by an Observable object
 * that can handle the same trivial observer infrastructure requests. Using an abstract
 * class to handle this toes a hard line that anything the designer may want to be an 
 * observable must inherit from this class and can no longer inherit from another abstract class.
 * The key principle being ~favor composition over inheritance~.
 *  
 * @see IObservable
 * @see author.observe
 * @author Hannah Fuchshuber, George Bernard
 */
public abstract class AbstractObservable implements IObservable{ 
	private Collection<IObserver> myObservers;
	
	public AbstractObservable() {
		myObservers = new ArrayList<IObserver>();
	}

	public void registerObserver(IObserver o) {
		myObservers.add(o);
	}


	public void removeObserver(IObserver o) {
		myObservers.remove(o);
	}

	public void notifyObservers() {
		myObservers.forEach(IObserver::update);
	}

}
