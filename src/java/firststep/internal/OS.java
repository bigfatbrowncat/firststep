package firststep.internal;

public class OS {

	public enum Platform {
		WINDOWS, OSX, OTHER;
	}
	
	static {
		NativeLoader.loadDefault();
	}

	public static native Platform getPlatform();
}