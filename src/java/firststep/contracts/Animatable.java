package firststep.contracts;

public interface Animatable extends Renderable {
	/**
	 * Sets the current time
	 * @param time in seconds
	 */
	void setCurrentTime(float time);
	
	/**
	 * @return the animatable duration in seconds.
	 * If the animation is infinite, returns null
	 */
	Float getDuration();
}
