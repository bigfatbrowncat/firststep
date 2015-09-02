package firststep;

import java.util.LinkedHashSet;

public class DrawingQueue {
	private LinkedHashSet<Framebuffer> framebuffers = new LinkedHashSet<>();
	
	public void append(Framebuffer framebuffer) {
		framebuffers.add(framebuffer);
	}
	
	LinkedHashSet<Framebuffer> getFramebuffers() {
		return framebuffers;
	}
	
	void clearFramebuffers() {
		framebuffers.clear();
	}
}
