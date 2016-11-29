package author.observe;

/**
 *  This interface specifies the Observer property
 *  <p>
 *  It must implement update(), which ~updates~ the object with this property
 *  In the current state of the code base. Observers are passed an observable object
 *  which they then register themselves to.
 *  
 * @see IObservable  
 * @see author.observe
 * @author George Bernard, Hannah Fuchsuber
 */
public interface IObserver {

    /**
     * this method is called whenever the observer must be updated
     */
    void update ();

}
