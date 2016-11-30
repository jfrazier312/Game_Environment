package states;

public interface State {
	
	public State copy();
	public void updateState(int pain);

}
