package firststep.internal;

public class NativeLoader {
	private static boolean isLoaded = false;
	
	static void loadDefault() {
		if (!isLoaded) {
			System.loadLibrary("firststep");
			isLoaded = true;
		}
	}
	
/*	static void loadCustom(String path) {
		if (!isLoaded) {
			System.load(path);
			isLoaded = true;
		}
	}*/
}
