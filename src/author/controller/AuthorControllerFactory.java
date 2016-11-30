/**
 * 
 */
package author.controller;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class AuthorControllerFactory {

	/**
	 * 
	 */
	public AuthorControllerFactory() {
		// Empty On Purpose
	}
	
	public IAuthorControllerExternal create(){
		return new ConcreteAuthorController();
	}

}
