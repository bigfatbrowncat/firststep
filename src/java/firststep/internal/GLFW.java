
package firststep.internal;

import java.util.ArrayList;
import java.util.List;

public class GLFW {

	/**
	 * Registered with Glfw3#glfwSetCallback(), reacts to all
	 * callback events of Glfw
	 * @author mzechner
	 *
	 */
	public interface Callback {
		/**
		 * Called in case of an error, on the thread
		 * the error was generated on.
		 * @param error the error code
		 * @param description the description of the error
		 */
		public void error(int error, String description);
		
		/**
		 * Called when a monitor was connected/disconnected
		 * @param monitor the monitor handle
		 * @param connected whether the monitor was connected or disconnected
		 */
		public void monitor(long monitor, boolean connected);
		
		/**
		 * Called when the position of a window changed
		 * @param window the window handle
		 * @param x the new x coordinate, in pixels, of the top left corner of the client area of the window
		 * @param y the new y coordinate, in pixels, of the top left corner of the client area of the window
		 */
		public void windowPos(long window, int x, int y);
		
		/**
		 * Called when the size of the window changed
		 * @param window the window handle
		 * @param width the new width in pixels
		 * @param height the new height in pixels
		 */
		public void windowSize(long window, int width, int height);
		
		/**
		 * Called when the window should be closed.
		 * @param window the window handle
		 * @return whether to allow closing the window
		 */
		public void windowClose(long window);
		
		/**
		 * Called when the window content needs to be refreshed
		 * @param window the window handle
		 */
		public void windowRefresh(long window);
		
		/**
		 * Called when the focus of the window changed
		 * @param window the window handle
		 * @param focused whether the window is focused
		 */
		public void windowFocus(long window, boolean focused);
		
		/**
		 * Called when the window is (de-)iconified
		 * @param window the window handle
		 * @param iconified whether the window is iconified
		 */
		public void windowIconify(long window, boolean iconified);
		
		/**
		 * Called when a key was pressed or released
		 * @param window the window handle
		 * @param key the key code
		 * @param action whether the key was pressed (PRESSED), released (RELEASE) or repeated (REPEAT)
		 */
		public void key(long window, int key, int scancode, int action, int mods);
		
		/**
		 * Called when a Unicode character was generated by the keyboard
		 * @param window the window handle
		 * @param character the Unicode character
		 */
		public void character(long window, char character);
		
		/**
		 * Called when a mouse button was pressed or released
		 * @param window the window handle
		 * @param button the button id
		 * @param pressed whether the button was pressed (true) or released (false)
		 */
		public void mouseButton(long window, int button, int action, int mods);
		
		/**
		 * Called when the mouse cursor moved
		 * @param window the window
		 * @param x the x-coordinate in client area coordinates
		 * @param y the y-coordinate in client area coordinates
		 */
		public void cursorPos(long window, double x, double y);
		
		/**
		 * Called when the mouse entered the client area
		 * @param window the window handle
		 * @param entered whether the mouse cursor entered or left the window
		 */
		public void cursorEnter(long window, boolean entered);
	
		/**
		 * Called when the mouse wheel was scrolled
		 * @param window the window handle
		 * @params scrollX The scroll offset along the x-axis.
		 * @params scrollY The scroll offset along the y-axis.
		 */
		public void scroll(long window, double scrollX, double scrollY);
	}
	
	public static class CallbackAdapter implements Callback {

		@Override
		public void error(int error, String description) {
		}

		@Override
		public void monitor(long monitor, boolean connected) {
		}

		@Override
		public void windowPos(long window, int x, int y) {
		}

		@Override
		public void windowSize(long window, int width, int height) {
		}

		@Override
		public void windowClose(long window) {
		}

		@Override
		public void windowRefresh(long window) {
		}

		@Override
		public void windowFocus(long window, boolean focused) {
		}

		@Override
		public void windowIconify(long window, boolean iconified) {
		}

		@Override
		public void key(long window, int key, int scancode, int action, int mods) {
		}

		@Override
		public void character(long window, char character) {
		}

		@Override
		public void mouseButton(long window, int button, int action, int mods) {
		}

		@Override
		public void cursorPos(long window, double x, double y) {
		}

		@Override
		public void cursorEnter(long window, boolean entered) {
		}

		@Override
		public void scroll(long window, double scrollX, double scrollY) {
		}

	}

	public static class Exception extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Exception() {
			super();
		}

		public Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public Exception(String message, Throwable cause) {
			super(message, cause);
		}

		public Exception(String message) {
			super(message);
		}

		public Exception(Throwable cause) {
			super(cause);
		}
		
	}

	public static class VideoMode {
		public int width;
		public int height;
		public int redBits;
		public int greenBits;
		public int blueBits;
		@Override
		public String toString() {
			return "GlfwVideoMode [width=" + width + ", height=" + height
					+ ", redBits=" + redBits + ", greenBits=" + greenBits
					+ ", blueBits=" + blueBits + "]";
		}
	}
	
	static {
		NativeLoader.loadDefault();
	}
	
	public static final int VERSION_MAJOR = 3;
	public static final int VERSION_MINOR = 0;
	public static final int VERSION_REVISION = 0;
	public static final int RELEASE = 0;
	public static final int PRESS = 1;
	public static final int REPEAT = 2;

	public static final int KEY_SPACE = 32;
	public static final int KEY_APOSTROPHE = 39 /* ' */;
	public static final int KEY_COMMA = 44 /* , */;
	public static final int KEY_MINUS = 45 /* - */;
	public static final int KEY_PERIOD = 46 /* . */;
	public static final int KEY_SLASH = 47 /* / */;
	public static final int KEY_0 = 48;
	public static final int KEY_1 = 49;
	public static final int KEY_2 = 50;
	public static final int KEY_3 = 51;
	public static final int KEY_4 = 52;
	public static final int KEY_5 = 53;
	public static final int KEY_6 = 54;
	public static final int KEY_7 = 55;
	public static final int KEY_8 = 56;
	public static final int KEY_9 = 57;
	public static final int KEY_SEMICOLON = 59 /* ; */;
	public static final int KEY_EQUAL = 61 /* = */;
	public static final int KEY_A = 65;
	public static final int KEY_B = 66;
	public static final int KEY_C = 67;
	public static final int KEY_D = 68;
	public static final int KEY_E = 69;
	public static final int KEY_F = 70;
	public static final int KEY_G = 71;
	public static final int KEY_H = 72;
	public static final int KEY_I = 73;
	public static final int KEY_J = 74;
	public static final int KEY_K = 75;
	public static final int KEY_L = 76;
	public static final int KEY_M = 77;
	public static final int KEY_N = 78;
	public static final int KEY_O = 79;
	public static final int KEY_P = 80;
	public static final int KEY_Q = 81;
	public static final int KEY_R = 82;
	public static final int KEY_S = 83;
	public static final int KEY_T = 84;
	public static final int KEY_U = 85;
	public static final int KEY_V = 86;
	public static final int KEY_W = 87;
	public static final int KEY_X = 88;
	public static final int KEY_Y = 89;
	public static final int KEY_Z = 90;
	public static final int KEY_LEFT_BRACKET = 91 /* [ */;
	public static final int KEY_BACKSLASH = 92 /* \ */;
	public static final int KEY_RIGHT_BRACKET = 93 /* ] */;
	public static final int KEY_GRAVE_ACCENT = 96 /* ` */;
	public static final int KEY_WORLD_1 = 161 /* non-US #1 */;
	public static final int KEY_WORLD_2 = 162 /* non-US #2 */;

	/* Function keys */
	public static final int KEY_ESCAPE = 256;
	public static final int KEY_ENTER = 257;
	public static final int KEY_TAB = 258;
	public static final int KEY_BACKSPACE = 259;
	public static final int KEY_INSERT = 260;
	public static final int KEY_DELETE = 261;
	public static final int KEY_RIGHT = 262;
	public static final int KEY_LEFT = 263;
	public static final int KEY_DOWN = 264;
	public static final int KEY_UP = 265;
	public static final int KEY_PAGE_UP = 266;
	public static final int KEY_PAGE_DOWN = 267;
	public static final int KEY_HOME = 268;
	public static final int KEY_END = 269;
	public static final int KEY_CAPS_LOCK = 280;
	public static final int KEY_SCROLL_LOCK = 281;
	public static final int KEY_NUM_LOCK = 282;
	public static final int KEY_PRINT_SCREEN = 283;
	public static final int KEY_PAUSE = 284;
	public static final int KEY_F1 = 290;
	public static final int KEY_F2 = 291;
	public static final int KEY_F3 = 292;
	public static final int KEY_F4 = 293;
	public static final int KEY_F5 = 294;
	public static final int KEY_F6 = 295;
	public static final int KEY_F7 = 296;
	public static final int KEY_F8 = 297;
	public static final int KEY_F9 = 298;
	public static final int KEY_F10 = 299;
	public static final int KEY_F11 = 300;
	public static final int KEY_F12 = 301;
	public static final int KEY_F13 = 302;
	public static final int KEY_F14 = 303;
	public static final int KEY_F15 = 304;
	public static final int KEY_F16 = 305;
	public static final int KEY_F17 = 306;
	public static final int KEY_F18 = 307;
	public static final int KEY_F19 = 308;
	public static final int KEY_F20 = 309;
	public static final int KEY_F21 = 310;
	public static final int KEY_F22 = 311;
	public static final int KEY_F23 = 312;
	public static final int KEY_F24 = 313;
	public static final int KEY_F25 = 314;
	public static final int KEY_KP_0 = 320;
	public static final int KEY_KP_1 = 321;
	public static final int KEY_KP_2 = 322;
	public static final int KEY_KP_3 = 323;
	public static final int KEY_KP_4 = 324;
	public static final int KEY_KP_5 = 325;
	public static final int KEY_KP_6 = 326;
	public static final int KEY_KP_7 = 327;
	public static final int KEY_KP_8 = 328;
	public static final int KEY_KP_9 = 329;
	public static final int KEY_KP_DECIMAL = 330;
	public static final int KEY_KP_DIVIDE = 331;
	public static final int KEY_KP_MULTIPLY = 332;
	public static final int KEY_KP_SUBTRACT = 333;
	public static final int KEY_KP_ADD = 334;
	public static final int KEY_KP_ENTER = 335;
	public static final int KEY_KP_EQUAL = 336;
	public static final int KEY_LEFT_SHIFT = 340;
	public static final int KEY_LEFT_CONTROL = 341;
	public static final int KEY_LEFT_ALT = 342;
	public static final int KEY_LEFT_SUPER = 343;
	public static final int KEY_RIGHT_SHIFT = 344;
	public static final int KEY_RIGHT_CONTROL = 345;
	public static final int KEY_RIGHT_ALT = 346;
	public static final int KEY_RIGHT_SUPER = 347;
	public static final int KEY_MENU = 348;
	public static final int KEY_LAST = KEY_MENU;

	public static final int KEY_ESC = KEY_ESCAPE;
	public static final int KEY_DEL = KEY_DELETE;
	public static final int KEY_PAGEUP = KEY_PAGE_UP;
	public static final int KEY_PAGEDOWN = KEY_PAGE_DOWN;
	public static final int KEY_KP_NUM_LOCK = KEY_NUM_LOCK;
	public static final int KEY_LCTRL = KEY_LEFT_CONTROL;
	public static final int KEY_LSHIFT = KEY_LEFT_SHIFT;
	public static final int KEY_LALT = KEY_LEFT_ALT;
	public static final int KEY_LSUPER = KEY_LEFT_SUPER;
	public static final int KEY_RCTRL = KEY_RIGHT_CONTROL;
	public static final int KEY_RSHIFT = KEY_RIGHT_SHIFT;
	public static final int KEY_RALT = KEY_RIGHT_ALT;
	public static final int KEY_RSUPER = KEY_RIGHT_SUPER;

	public static final int MOUSE_BUTTON_1 = 0;
	public static final int MOUSE_BUTTON_2 = 1;
	public static final int MOUSE_BUTTON_3 = 2;
	public static final int MOUSE_BUTTON_4 = 3;
	public static final int MOUSE_BUTTON_5 = 4;
	public static final int MOUSE_BUTTON_6 = 5;
	public static final int MOUSE_BUTTON_7 = 6;
	public static final int MOUSE_BUTTON_8 = 7;
	public static final int MOUSE_BUTTON_LAST = MOUSE_BUTTON_8;
	public static final int MOUSE_BUTTON_LEFT = MOUSE_BUTTON_1;
	public static final int MOUSE_BUTTON_RIGHT = MOUSE_BUTTON_2;
	public static final int MOUSE_BUTTON_MIDDLE = MOUSE_BUTTON_3;

	public static final int JOYSTICK_1 = 0;
	public static final int JOYSTICK_2 = 1;
	public static final int JOYSTICK_3 = 2;
	public static final int JOYSTICK_4 = 3;
	public static final int JOYSTICK_5 = 4;
	public static final int JOYSTICK_6 = 5;
	public static final int JOYSTICK_7 = 6;
	public static final int JOYSTICK_8 = 7;
	public static final int JOYSTICK_9 = 8;
	public static final int JOYSTICK_10 = 9;
	public static final int JOYSTICK_11 = 10;
	public static final int JOYSTICK_12 = 11;
	public static final int JOYSTICK_13 = 12;
	public static final int JOYSTICK_14 = 13;
	public static final int JOYSTICK_15 = 14;
	public static final int JOYSTICK_16 = 15;
	public static final int JOYSTICK_LAST = JOYSTICK_16;

	public static final int NO_ERROR = 0;

	public static final int NOT_INITIALIZED = 0x00070001;

	public static final int NO_CURRENT_CONTEXT = 0x00070002;

	public static final int INVALID_ENUM = 0x00070003;

	public static final int INVALID_VALUE = 0x00070004;

	public static final int OUT_OF_MEMORY = 0x00070005;

	public static final int API_UNAVAILABLE = 0x00070006;

	public static final int VERSION_UNAVAILABLE = 0x00070007;

	public static final int PLATFORM_ERROR = 0x00070008;

	public static final int FORMAT_UNAVAILABLE = 0x00070009;

	public static final int FOCUSED = 0x00020001;
	public static final int ICONIFIED = 0x00020002;
	public static final int RESIZABLE = 0x00022007;
	public static final int VISIBLE = 0x00022008;
	public static final int UNDECORATED = 0x00022009;

	public static final int RED_BITS = 0x00021000;
	public static final int GREEN_BITS = 0x00021001;
	public static final int BLUE_BITS = 0x00021002;
	public static final int ALPHA_BITS = 0x00021003;
	public static final int DEPTH_BITS = 0x00021004;
	public static final int STENCIL_BITS = 0x00021005;
	public static final int ACCUM_RED_BITS = 0x00021006;
	public static final int ACCUM_GREEN_BITS = 0x00021007;
	public static final int ACCUM_BLUE_BITS = 0x00021008;
	public static final int ACCUM_ALPHA_BITS = 0x00021009;
	public static final int AUX_BUFFERS = 0x0002100A;
	public static final int STEREO = 0x0002100B;
	public static final int SAMPLES = 0x0002100C;
	public static final int SRGB_CAPABLE = 0x0002100D;

	public static final int CLIENT_API = 0x00022001;
	public static final int CONTEXT_VERSION_MAJOR = 0x00022002;
	public static final int CONTEXT_VERSION_MINOR = 0x00022003;
	public static final int CONTEXT_REVISION = 0x00022004;
	public static final int CONTEXT_ROBUSTNESS = 0x00022005;
	public static final int OPENGL_FORWARD_COMPAT = 0x00022006;
	public static final int OPENGL_DEBUG_CONTEXT = 0x00022007;
	public static final int OPENGL_PROFILE = 0x00022008;
	public static final int CONTEXT_RELEASE_BEHAVIOR = 0x00022009;

	public static final int OPENGL_API = 0x00030001;
	public static final int OPENGL_ES_API = 0x00030002;

	public static final int NO_ROBUSTNESS = 0x00000000;
	public static final int NO_RESET_NOTIFICATION = 0x00031001;
	public static final int LOSE_CONTEXT_ON_RESET = 0x00031002;

	public static final int OPENGL_ANY_PROFILE = 0x00000000;
	public static final int OPENGL_CORE_PROFILE = 0x00032001;
	public static final int OPENGL_COMPAT_PROFILE = 0x00032002;

	public static final int CURSOR = 0x00033001;
	public static final int STICKY_KEYS = 0x00033002;
	public static final int STICKY_MOUSE_BUTTONS = 0x00033003;

	public static final int CURSOR_NORMAL = 0x00034001;
	public static final int CURSOR_HIDDEN = 0x00034002;
	public static final int CURSOR_DISABLED = 0x00034003;

	public static final int PRESENT = 0x00050001;
	public static final int AXES = 0x00050002;
	public static final int BUTTONS = 0x00050003;

	public static final int GAMMA_RAMP_SIZE = 256;

	public static final int CONNECTED = 0x00061000;
	public static final int DISCONNECTED = 0x00061001;

	private static Callback callback = null;

	public static boolean init() {
		return initJni();
	}
	
	private static native boolean initJni();
	public static native void terminate();
	public static native String getVersionString();

	public static long[] getMonitors() {
		long[] monitors = new long[256]; // 256 monitors are enough for everyone...
		int count = getMonitorsJni(monitors);
		if (count > 256) {
			// Really? Not enough??
			count = 256;
		}
		long[] n = new long[count];
		System.arraycopy(monitors, 0, n, 0, count);
		return n;
	}
	
	private static native int getMonitorsJni(long[] monitors);
	public static native long getPrimaryMonitor();
	public static native int getMonitorX(long monitor);
	public static native int getMonitorY(long monitor);
	public static native int getMonitorPhysicalWidth(long monitor);
	public static native int getMonitorPhysicalHeight(long monitor);
	public static native String getMonitorName(long monitor);
	
	public static List<VideoMode> getVideoModes(long monitor) {
		int[] buffer = new int[5 * 256]; // 256 video modes are enough for everyone...
		int numModes = getVideoModesJni(monitor, buffer);
		if (numModes > 256) {
			numModes = 256;
		}
		List<VideoMode> modes = new ArrayList<VideoMode>();
		for(int i = 0, j = 0; i < numModes; i++) {
			VideoMode mode = new VideoMode();
			mode.width = buffer[j++];
			mode.height = buffer[j++];
			mode.redBits = buffer[j++];
			mode.greenBits = buffer[j++];
			mode.blueBits = buffer[j++];
			modes.add(mode);
		}
		return modes;
	}
	
	private static native int getVideoModesJni(long monitor, int[] modes);
	
	public static VideoMode getVideoMode(long monitor) {
		int[] buffer = new int[5];
		getVideoModeJni(monitor, buffer);
		VideoMode mode = new VideoMode();
		mode.width = buffer[0];
		mode.height = buffer[1];
		mode.redBits = buffer[2];
		mode.greenBits = buffer[3];
		mode.blueBits = buffer[4];
		return mode;
	}
	
	private static native void getVideoModeJni(long monitor, int[] buffer);
	public static native void setGamma(long monitor, float gamma);

	private static native void getGammaRamp();
	private static native void setGammaRamp();
	
	public static native void defaultWindowHints();
	public static native void windowHint(int target, int hint);
	
	public static long createWindow(int width, int height, String title, long monitor, long share) {
		return createWindowJni(width, height, title == null ? "GLFW" : title, monitor, share);
	}

	private static native long createWindowJni(int width, int height, String title, long monitor, long share);
	public static native void destroyWindow(long window);
	public static native boolean windowShouldClose(long window);
	public static native void setWindowShouldClose(long window, int value);
	public static native void setWindowTitle(long window, String title);
	public static native void setWindowPos(long window,  int x, int y);
	public static native int getWindowX(long window);
	public static native int getWindowY(long window);
	public static native int getWindowWidth(long window);
	public static native int getWindowHeight(long window);
	public static native void setWindowSize(long window, int width, int height);
	public static native void iconifyWindow(long window);
	public static native void restoreWindow(long window);
	public static native void hideWindow(long window);
	public static native void showWindow(long window);
	public static native long getWindowMonitor(long window);
	
	/**
	 * Sets the {@link Callback} that will get invoked by
	 * various events. Replaces the single callback functions of GLFW
	 * @param callback the callback or null
	 */
	public static void setCallback(Callback callback) {
		setCallbackJni(callback);
	}

	public static native void setCallbackJni(Callback javaCallback);
	public static void pollEvents() {
		pollEventsJni(callback);
	}
	
	private static native void pollEventsJni(Callback javaCallback);
	public static void waitEvents() {
		waitEventsJni(callback);
	}
	
	private static native void waitEventsJni(Callback javaCallback);
	public static native int getInputMode(long window, int mode);
	public static native void setInputMode(long window, int mode, int value);
	public static native boolean getKey(long window, int key);
	public static native boolean getMouseButton(long window, int button);
	public static native double getCursorPosX(long window);
	public static native double getCursorPosY(long window);
	public static native void setCursorPos(long window, int x, int y);	
	public static native int getJoystickAxes(int joy, float[] axes);
	public static native int getJoystickButtons(int joy, byte[] buttons);
	public static native String getJoystickName(int joy);
	public static native void setClipboardString(long window, String string);
	public static native String getClipboardString(long window);
	public static native double getTime();
	public static native void setTime(double time);
	public static void makeContextCurrent(long window) {
		makeContextCurrentJni(window);
		//GL.init();
	}
	
	private static native void makeContextCurrentJni(long window);
	public static native long getCurrentContext();
	public static native void swapBuffers(long window);
	public static native void swapInterval(int interval);
	public static native boolean extensionSupported(String extension);

}