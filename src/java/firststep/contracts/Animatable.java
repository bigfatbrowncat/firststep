package firststep.contracts;

public interface Animatable extends Renderable {
	void setCurrentFrame(int index);
	int getFrameCount();
}
