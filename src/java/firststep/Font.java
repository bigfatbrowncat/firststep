package firststep;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import firststep.internal.NVG;

public class Font {
	@SuppressWarnings("serial")
	class FontExistsException extends Exception {
		public final int fontId;
		public FontExistsException(int fontId) {
			this.fontId = fontId;
		}
	}

	private static HashMap<String, WeakReference<Font>> allFonts = new HashMap<>();
	int id;
	private Canvas canvas;

	Font(Canvas cnv, String name, String path) throws FontExistsException, IOException {
		canvas = cnv;
		if (allFonts.containsKey(name)) throw new FontExistsException(id);
		id = NVG.createFont(canvas.nanoVGContext, name, path);
		if (id == -1) throw new RuntimeException("Failed to load font " + name);
		allFonts.put(name, new WeakReference<>(this));
	}

	Font(Canvas cnv, String name, byte[] data) throws FontExistsException {
		canvas = cnv;
		if (allFonts.containsKey(name)) throw new FontExistsException(id);
		id = NVG.createFontMem(canvas.nanoVGContext, name, data);
		if (id == -1) throw new RuntimeException("Failed to load font " + name);
		allFonts.put(name, new WeakReference<>(this));
	}

	static Font find(Canvas cnv, String name) {
		return find(name);
	}
	
	static Font find(String name) {
		WeakReference<Font> f = allFonts.get(name);
		if (f != null) {
			return f.get();
		} else {
			return null;
		}
	}
}
