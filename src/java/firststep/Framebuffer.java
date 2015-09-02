package firststep;

import firststep.internal.GL3W;
import firststep.internal.NVG;

public class Framebuffer extends Canvas {
	
	private long id;
	private int width, height;
	private Image.Flags imageFlags;
	private Color background = Color.fromRGBA(0.0f, 0.0f, 0.0f, 0.0f);

	private boolean isDeleted;
		
	Framebuffer(int width, int height, Image.Flags imageFlags) {
		this.width = width;
		this.height = height;
		this.imageFlags = imageFlags;

		id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
		image = Image.forFramebuffer(this, id);
	}
	
	Framebuffer(int width, int height, long id) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public void resize(int newWidth, int newHeight) {
		if (id == 0) {
			this.width = newWidth;
			this.height = newHeight;
		} else {
			NVG.deleteFramebuffer(id);
			id = NVG.createFramebuffer(nanoVGContext, width, height, imageFlags.toFlags());
			image = Image.forFramebuffer(this, id);

		}
	}
	
	public synchronized void delete() {
		if (!isDeleted) {
			NVG.deleteFramebuffer(id);
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
	public void beginDrawing() {
		IntXY fboSize;
//		if (id == 0) {
			// TODO Implement getFramebufferSize
			fboSize = new IntXY(width, height);
//		} else {
//			fboSize = getImage().getSize();
//		}

		int winWidth = (int)(fboSize.getX() / pxRatio);
		int winHeight = (int)(fboSize.getY() / pxRatio);

		NVG.bindFramebuffer(id);
		GL3W.glViewport(0, 0, fboSize.getX(), fboSize.getY());
		if (background != null) {
			GL3W.glClearColor(background.getRed(), background.getGreen(), background.getBlue(), background.getAlpha());
			GL3W.glClear(GL3W.GL_COLOR_BUFFER_BIT | GL3W.GL_STENCIL_BUFFER_BIT | GL3W.GL_DEPTH_BUFFER_BIT);
		}
		NVG.beginFrame(nanoVGContext, winWidth, winHeight, pxRatio);
	}
	
	public void endDrawing() {
		NVG.endFrame(nanoVGContext);
		NVG.bindFramebuffer(0);
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
	
	@Override
	protected void finalize() throws Throwable {
		delete();
		super.finalize();
	}
}
