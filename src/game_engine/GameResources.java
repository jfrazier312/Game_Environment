package game_engine;


public enum GameResources {
    GAME_TITLE("GAME TITLE HERE"),
    
    //Default accelerations
    DEFAULT_DOWN_ACCELERATION(9.8),
	DEFAULT_UP_ACCELERATION(0.0),
	DEFAULT_RIGHT_ACCELERATION(0.0),
	DEFAULT_LEFT_ACCELERATION(0.0),
	
	//Default velocities 
	DEFAULT_DOWN_VELOCITY(0.0),
	DEFAULT_RIGHT_VELOCITY(5.0),
	DEFAULT_LEFT_VELOCITY(5.0),
	DEFAULT_UP_VELOCITY(10.0);

    private double resourceDouble;
    private String resourceString;

    GameResources(String resource) {
        resourceString = resource;
        resourceDouble = -1;
    }

    GameResources(double resource) {
        resourceString = null;
        resourceDouble = resource;
    }

    public String getResource() {
        return resourceString;
    }

    public double getDoubleResource() {
        return resourceDouble;
    }
}
