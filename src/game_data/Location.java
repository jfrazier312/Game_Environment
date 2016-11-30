package game_data;

/**
 * Holds X and Y location of a Sprite
 * 
 * @author Addison
 */
public class Location {

	private double myHeading;
	private double myXLocation;
	private double myYLocation;
	
	public Location(double aXLocation, double aYLocation, double aHeading){
		setLocation(aXLocation, aYLocation);
		setMyHeading(aHeading);
	}
	
	public double getMyHeading() {
		return myHeading;
	}

	public void setMyHeading(double myHeading) {
		this.myHeading = myHeading % 360;
	}
	
	public double getXLocation(){
		return myXLocation;
	}
	
	public double getYLocation(){
		return myYLocation;
	}
	public void setLocation(double myXLocation, double myYLocation){
		this.myXLocation = myXLocation;
		this.myYLocation = myYLocation;
	}

}
