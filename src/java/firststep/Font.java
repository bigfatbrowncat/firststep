package firststep;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import firststep.internal.JavaTools;
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

	Font(String name, String path) throws FontExistsException, IOException {
		if (allFonts.containsKey(name)) throw new FontExistsException(id);
		id = NVG.createFont(Canvas.nanoVGContext, name, path);
		if (id == -1) throw new RuntimeException("Failed to load font " + name);
		allFonts.put(name, new WeakReference<>(this));
	}

	Font(String name, byte[] data) throws FontExistsException {
		if (allFonts.containsKey(name)) throw new FontExistsException(id);
		id = NVG.createFontMem(Canvas.nanoVGContext, name, data);
		if (id == -1) throw new RuntimeException("Failed to load font " + name);
		allFonts.put(name, new WeakReference<>(this));
	}
	
	public static Font createOrFindFont(String name, String path) throws IOException {
		try {
			return new Font(name, path);
		} catch (Font.FontExistsException e) {
			return Font.find(name);
		}
	}

	public static Font createOrFindFont(String name, byte[] data) {
		try {
			return new Font(name, data);
		} catch (Font.FontExistsException e) {
			return Font.find(name);
		}
	}

	public static Font createOrFindFont(String name, InputStream is) throws IOException {
		try {
			return new Font(name, JavaTools.convertSteamToByteArray(is, 65536));
		} catch (Font.FontExistsException e) {
			return Font.find(name);
		}
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
