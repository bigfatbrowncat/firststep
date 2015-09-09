package firststep;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Iterator;

import firststep.contracts.Deletable;
import firststep.internal.JavaTools;
import firststep.internal.NVG;

public class Image implements Deletable {
	
	/**
	 * Image construction flags
	 */
	public enum Flag {
	    GENERATE_MIPMAPS(1<<0),	// Generate mipmaps during creation of the image.
	    REPEATX(1<<1),			// Repeat image in X direction.
	    REPEATY(1<<2),			// Repeat image in Y direction.
	    FLIPY(1<<3),				// Flips (inverses) image in Y direction when rendered.
	    PREMULTIPLIED(1<<4);		// Image data has premultiplied alpha.
		
		final int value;
		Flag(int value) {
			this.value = value;
		}
		static Flag fromId(int id) {
			for (Flag v : Flag.values()) {
				if (v.value == id) return v;
			}
			throw new RuntimeException("Incorrect id");
		}
	}
	
	public static class Flags {
		public static final Flags NONE = new Flags(EnumSet.noneOf(Flag.class));
		
		private final EnumSet<Flag> values;
		public Flags(EnumSet<Flag> values) {
			this.values = values.clone();
		}
		static Flags fromFlags(int flags) {
			EnumSet<Flag> values = EnumSet.noneOf(Flag.class);
			for (Flag f : Flag.values()) {
				if ((flags & f.value) != 0) values.add(f);
			}
			return new Flags(values);
		}
		public boolean contains(Flag mod) {
			return values.contains(mod);
		}
		public boolean isEmpty() {
			return values.isEmpty();
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Flags) {
				if (((Flags) obj).values.size() == values.size()) {
					for (Flag v : values) {
						if (!((Flags) obj).values.contains(v)) return false;
					}
					return true;
				}
			}
			return false;
		}
		
		public static Flags of(Flag mod) {
			return new Flags(EnumSet.of(mod));
		}
		public static Flags of(Flag mod1, Flag... rest) {
			return new Flags(EnumSet.of(mod1, rest));
		}
		
		int toFlags() {
			int res = 0;
			for (Flag mod : values) {
				res |= mod.value;
			}
			return res;
		}
		
		@Override
		public String toString() {
			if (values.size() == 0) return "()";
			Iterator<Flag> iter = values.iterator(); 
			String s = "(" + iter.next();
			while (iter.hasNext()) {
				s += ", " + iter.next();
			}
			return s += ")";
		}
	}

	
	int id;

	private boolean isDeleted;
	private boolean shouldBeDeleted;
	
	public Image(String filename, Flags imageFlags) throws IOException {
		id = NVG.createImage(Canvas.nanoVGContext, filename, imageFlags.toFlags());
		if (id == 0) {
			throw new IOException("Can't load image from file " + filename);
		}
	}
	
	public Image(byte[] data, Flags imageFlags) {
		id = NVG.createImageMem(Canvas.nanoVGContext, data, imageFlags.toFlags());
	}
	
	public Image(InputStream is, Flags imageFlags) throws IOException {
		id = NVG.createImageMem(Canvas.nanoVGContext, JavaTools.convertSteamToByteArray(is, 65536), imageFlags.toFlags());
	}
	
	static Image forFramebuffer(long fbId) {
		int imgId = NVG.getImageFromFramebuffer(fbId);
		return new Image(imgId, false);
	}
	
	private Image(int id, boolean shouldBeDeleted) {
		this.id = id;
		this.shouldBeDeleted = shouldBeDeleted;
	}
	
	public IntXY getSize() {
		int[] dims = new int[2];
		NVG.imageSize(Canvas.nanoVGContext, id, dims);
		return new IntXY(dims[0], dims[1]);
	}
	
	public void delete() {
		if (!isDeleted) {
			if (shouldBeDeleted) {
				NVG.deleteImage(Canvas.nanoVGContext, id);
			}
			isDeleted = true;
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		Window.issueDelete(this);
		super.finalize();
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
}
