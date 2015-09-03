package firststep;

import java.util.ArrayList;
import java.util.List;

import firststep.internal.GL3W;
import firststep.internal.NVG;

public class Framebuffer extends Canvas implements Deletable {
	
	private long id;
	private int width, height;
	private Image.Flags imageFlags;
	private Color background = Color.fromRGBA(0.0f, 0.0f, 0.0f, 0.0f);

	private boolean isDeleted;
		
	public Framebuffer(int width, int height, Image.Flags imageFlags) {
		this.width = width;
		this.height = height;
		this.imageFlags = imageFlags;

		id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
		if (id == 0) throw new RuntimeException("Can't create a framebuffer");
		image = Image.forFramebuffer(this, id);
	}
	
	Framebuffer(int width, int height, long id) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public void resize(int newWidth, int newHeight) {
		this.width = newWidth;
		this.height = newHeight;

		if (id != 0) {
			NVG.deleteFramebuffer(id);
			id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
			if (id == 0) throw new RuntimeException("Can't recreate a framebuffer");
			image = Image.forFramebuffer(this, id);
		}
	}
	
	public synchronized void delete() {
		if (!isDeleted) {
			if (id != 0) NVG.deleteFramebuffer(id);
			isDeleted = true;
		}
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	private Image image;
	public Image getImage() {
		return image;
	}
	
	private float pxRatio = 1.0f;
	
	private static List<Framebuffer> framebufferStack = new ArrayList<Framebuffer>();
	public void beginDrawing() {
		if (framebufferStack.contains(this)) {
			throw new RuntimeException("Trying to call beginDrawing() on a framebuffer that has already been opened for drawing");
		}
		
		// Closing the current framebuffer
		if (framebufferStack.size() > 0) {
			NVG.endFrame(nanoVGContext);
		}
		
		// Opening the new one
		IntXY fboSize = new IntXY(width, height);

		int winWidth = (int)(fboSize.getX() / pxRatio);
		int winHeight = (int)(fboSize.getY() / pxRatio);

		NVG.bindFramebuffer(id);
		GL3W.glViewport(0, 0, fboSize.getX(), fboSize.getY());
		if (background != null) {
			GL3W.glClearColor(background.getRed(), background.getGreen(), background.getBlue(), background.getAlpha());
			GL3W.glClear(GL3W.GL_COLOR_BUFFER_BIT | GL3W.GL_STENCIL_BUFFER_BIT | GL3W.GL_DEPTH_BUFFER_BIT);
		}
		NVG.beginFrame(nanoVGContext, winWidth, winHeight, pxRatio);
		framebufferStack.add(this);
	}
	
	public void endDrawing() {
		if (framebufferStack.get(framebufferStack.size() - 1) != this) {
			throw new RuntimeException("Trying to call endDrawing() on a wrong framebuffer");
		}
		NVG.endFrame(nanoVGContext);
		framebufferStack.remove(framebufferStack.size() - 1);
		
		// Restoring the previous framebuffer
		if (framebufferStack.size() > 0) {
			Framebuffer prev = framebufferStack.get(framebufferStack.size() - 1);
			NVG.bindFramebuffer(prev.id);
			IntXY fboSize = new IntXY(prev.width, prev.height);
			if (prev.id == 0) {
				GL3W.glViewport(0, 0, fboSize.getX(), fboSize.getY());
			}
			int winWidth = (int)(fboSize.getX() / pxRatio);
			int winHeight = (int)(fboSize.getY() / pxRatio);
			NVG.beginFrame(nanoVGContext, winWidth, winHeight, pxRatio);
		}
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
	
	@Override
	protected void finalize() throws Throwable {
		Window.issueDelete(this);
		super.finalize();
	}
}
