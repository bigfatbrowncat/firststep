package firststep.internal;

public class NativeLoader {
	
	static void loadDefault() {
		System.loadLibrary("firststep");
	}
	
/*	static void loadCustom(String path) {
		if (!isLoaded) {
			System.load(path);
			isLoaded = true;
		}
	}*/
}
