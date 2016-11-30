package author.observe;

/**
 * This interface specifies the Observable property
 * <p>
 * IMPLEMENTATION NOTE:
 * It must keep a registry of observers to add and remove Observers. 
 * Every time this object changes state relevant to observers
 * it must notify its observers.
 * 
 * @see integration.observe;
 * @author George Bernard, Hannah Fuchshuber
 */
public interface IObservable {

    /**
     * adds the specified observer to the registry
     * 
     * @see IObserver
     * @param aObserver
     */
    public void registerObserver (IObserver aObserver);

    /**
     * removes the specified observer from the registry
     * 
     * @see IObserver
     * @param aObserver
     */
    public void removeObserver (IObserver aObserver);

    /**
     * This called whenever the observers need to be updated.
     * Notify Observers must call .update() method on all current registered observers.
     */
    public void notifyObservers ();
    
}
