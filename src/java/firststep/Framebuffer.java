package firststep;

import java.util.ArrayList;
import java.util.List;

import firststep.contracts.Deletable;
import firststep.internal.GL3W;
import firststep.internal.NVG;

public class Framebuffer extends Canvas {
	private static class IdHolder implements Deletable {
		private boolean isDeleted;
		
		public final long id;

		public IdHolder(long id) {
			this.id = id;
		}
		
		@Override
		public synchronized void delete() {
			if (!isDeleted) {
				if (id != 0) NVG.deleteFramebuffer(id);
				isDeleted = true;
			}
		}
		public boolean isDeleted() {
			return isDeleted;
		}
		
	}
	private IdHolder idHolder;
	private int width, height;
	private Image.Flags imageFlags;
	private Color background = Color.fromRGBA(0.0f, 0.0f, 0.0f, 0.0f);

	public Framebuffer(int width, int height, Image.Flags imageFlags) {
		super(false);
		this.width = width;
		this.height = height;
		this.imageFlags = imageFlags;

		long id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
		if (id == 0) throw new RuntimeException("Can't create a framebuffer");
		image = Image.forFramebuffer(id);
		idHolder = new IdHolder(id);
	}
	
	Framebuffer(int width, int height) {
		super(true);
		this.idHolder = new IdHolder(0);
		this.width = width;
		this.height = height;
	}
	
	public void resize(int newWidth, int newHeight) {
		this.width = newWidth;
		this.height = newHeight;

		if (idHolder.id != 0) {
			idHolder.delete();
			
			long id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
			if (id == 0) throw new RuntimeException("Can't recreate a framebuffer");
			idHolder = new IdHolder(id);
			
			image = Image.forFramebuffer(idHolder.id);
		}
	}
	
	public synchronized void delete() {
		idHolder.delete();
	}
	
	public boolean isDeleted() {
		return idHolder.isDeleted();
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

		int winWidth = (int)(fboSize.x / pxRatio);
		int winHeight = (int)(fboSize.y / pxRatio);

		NVG.bindFramebuffer(idHolder.id);
		GL3W.glViewport(0, 0, fboSize.x, fboSize.y);
		if (background != null) {
			GL3W.glClearColor(background.getRed(), background.getGreen(), background.getBlue(), background.getAlpha());
			GL3W.glClear(GL3W.GL_COLOR_BUFFER_BIT | GL3W.GL_STENCIL_BUFFER_BIT | GL3W.GL_DEPTH_BUFFER_BIT);
		}
		NVG.beginFrame(nanoVGContext, winWidth, winHeight, pxRatio);
		framebufferStack.add(this);
	}
	
	protected void clearDrawingStack() {
		if (framebufferStack.size() > 0) {
			NVG.endFrame(nanoVGContext);
			framebufferStack.clear();
		}
	}
	
	protected void checkStackClear() {
		if (framebufferStack.size() > 0) {
			NVG.endFrame(nanoVGContext);
			int fbs = framebufferStack.size();
			if (fbs > 1) {
				framebufferStack.clear();
				throw new RuntimeException("Drawing stack isn't clear. " + fbs + " endDrawing() calls missing");
			} else {
				framebufferStack.clear();
				throw new RuntimeException("Drawing stack isn't clear. An endDrawing() call missing");
			}
		}
	}
	
	public void endDrawing() {
		if (framebufferStack.size() == 0) {
			throw new RuntimeException("Trying to call endDrawing() out of the drawing stack");
		}
		
		if (framebufferStack.get(framebufferStack.size() - 1) != this) {
			throw new RuntimeException("Trying to call endDrawing() on a wrong framebuffer");
		}
		NVG.endFrame(nanoVGContext);
		framebufferStack.remove(framebufferStack.size() - 1);
		
		// Restoring the previous framebuffer
		if (framebufferStack.size() > 0) {
			Framebuffer prev = framebufferStack.get(framebufferStack.size() - 1);
			NVG.bindFramebuffer(prev.idHolder.id);
			IntXY fboSize = new IntXY(prev.width, prev.height);
			if (prev.idHolder.id == 0) {
				GL3W.glViewport(0, 0, fboSize.x, fboSize.y);
			}
			int winWidth = (int)(fboSize.x / pxRatio);
			int winHeight = (int)(fboSize.y / pxRatio);
			NVG.beginFrame(nanoVGContext, winWidth, winHeight, pxRatio);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
	
	@Override
	protected void finalize() throws Throwable {
		Window.issueDelete(idHolder);
		super.finalize();
	}
}
