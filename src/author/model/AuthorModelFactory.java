/**
 * 
 */
package author.model;

import author.controller.IAuthorController;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class AuthorModelFactory {

	/**
	 * 
	 */
	public AuthorModelFactory() {
		// TODO Nothing On Purpose
	}
	
	public IAuthorModel create(IAuthorController aIAuthorController){
		return new ConcreteAuthorModel(aIAuthorController);
	}

}
