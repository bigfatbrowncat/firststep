package firststep.internal.portaudio;

public class PortAudioException extends Exception {

	public enum Code {
	    paNotInitialized(-10000),
	    paUnanticipatedHostError(-9999),
	    paInvalidChannelCount(-9998),
	    paInvalidSampleRate(-9997),
	    paInvalidDevice(-9996),
	    paInvalidFlag(-9995),
	    paSampleFormatNotSupported(-9994),
	    paBadIODeviceCombination(-9993),
	    paInsufficientMemory(-9992),
	    paBufferTooBig(-9991),
	    paBufferTooSmall(-9990),
	    paNullCallback(-9989),
	    paBadStreamPtr(-9988),
	    paTimedOut(-9987),
	    paInternalError(-9986),
	    paDeviceUnavailable(-9985),
	    paIncompatibleHostApiSpecificStreamInfo(-9984),
	    paStreamIsStopped(-9983),
	    paStreamIsNotStopped(-9982),
	    paInputOverflowed(-9981),
	    paOutputUnderflowed(-9980),
	    paHostApiNotFound(-9979),
	    paInvalidHostApi(-9978),
	    paCanNotReadFromACallbackStream(-9977),
	    paCanNotWriteToACallbackStream(-9976),
	    paCanNotReadFromAnOutputOnlyStream(-9975),
	    paCanNotWriteToAnInputOnlyStream(-9974),
	    paIncompatibleStreamHostApi(-9973),
	    paBadBufferPtr(-9972);
	    
    	public final int value;
    	Code(int value) {
    		this.value = value;
    	}
    	
    	public static Code fromValue(int value) {
    		for (Code c : values()) {
    			if (c.value == value) return c;
    		}
    		return null;
    	}
	}
	
	private Code code;
	
	public Code getCode() {
		return code;
	}
	
	public PortAudioException(Code code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public PortAudioException(Code code, Throwable cause) {
		super(cause);
		this.code = code;
	}
	
	/** To call from JNI */
	PortAudioException(int codeValue, String message) {
		super(message);
		this.code = Code.fromValue(codeValue);
	}
	
	public PortAudioException(String message) {
		super(message);
		this.code = null;
	}

}
