/* DO NOT EDIT THIS FILE - it is machine generated */
#if defined(__APPLE__)
#include <JavaVM/jni.h>
#else
#include <jni.h>
#endif
/* Header for class firststep_internal_portaudio_PortAudio */

#ifndef _Included_firststep_internal_portaudio_PortAudio
#define _Included_firststep_internal_portaudio_PortAudio
#ifdef __cplusplus
extern "C" {
#endif
#undef firststep_internal_portaudio_PortAudio_FLAG_CLIP_OFF
#define firststep_internal_portaudio_PortAudio_FLAG_CLIP_OFF 1L
#undef firststep_internal_portaudio_PortAudio_FLAG_DITHER_OFF
#define firststep_internal_portaudio_PortAudio_FLAG_DITHER_OFF 2L
#undef firststep_internal_portaudio_PortAudio_FORMAT_FLOAT_32
#define firststep_internal_portaudio_PortAudio_FORMAT_FLOAT_32 1L
#undef firststep_internal_portaudio_PortAudio_FORMAT_INT_32
#define firststep_internal_portaudio_PortAudio_FORMAT_INT_32 2L
#undef firststep_internal_portaudio_PortAudio_FORMAT_INT_24
#define firststep_internal_portaudio_PortAudio_FORMAT_INT_24 4L
#undef firststep_internal_portaudio_PortAudio_FORMAT_INT_16
#define firststep_internal_portaudio_PortAudio_FORMAT_INT_16 8L
#undef firststep_internal_portaudio_PortAudio_FORMAT_INT_8
#define firststep_internal_portaudio_PortAudio_FORMAT_INT_8 16L
#undef firststep_internal_portaudio_PortAudio_FORMAT_UINT_8
#define firststep_internal_portaudio_PortAudio_FORMAT_UINT_8 32L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_DEV
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_DEV 0L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_DIRECTSOUND
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_DIRECTSOUND 1L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_MME
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_MME 2L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_ASIO
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_ASIO 3L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_SOUNDMANAGER
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_SOUNDMANAGER 4L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_COREAUDIO
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_COREAUDIO 5L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_OSS
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_OSS 7L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_ALSA
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_ALSA 8L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_AL
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_AL 9L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_BEOS
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_BEOS 10L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_WDMKS
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_WDMKS 11L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_JACK
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_JACK 12L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_WASAPI
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_WASAPI 13L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_AUDIOSCIENCE
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_AUDIOSCIENCE 14L
#undef firststep_internal_portaudio_PortAudio_HOST_API_TYPE_COUNT
#define firststep_internal_portaudio_PortAudio_HOST_API_TYPE_COUNT 15L
/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getVersion
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getVersion
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getVersionText
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_firststep_internal_portaudio_PortAudio_getVersionText
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    initialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_PortAudio_initialize
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    terminate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_PortAudio_terminate
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getDeviceCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getDeviceCount
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getDeviceInfo
 * Signature: (ILfirststep/internal/portaudio/DeviceInfo;)V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_PortAudio_getDeviceInfo
  (JNIEnv *, jclass, jint, jobject);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getHostApiCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getHostApiCount
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getHostApiInfo
 * Signature: (ILfirststep/internal/portaudio/HostApiInfo;)V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_PortAudio_getHostApiInfo
  (JNIEnv *, jclass, jint, jobject);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    hostApiTypeIdToHostApiIndex
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_hostApiTypeIdToHostApiIndex
  (JNIEnv *, jclass, jint);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    hostApiDeviceIndexToDeviceIndex
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_hostApiDeviceIndexToDeviceIndex
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getDefaultInputDevice
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getDefaultInputDevice
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getDefaultOutputDevice
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getDefaultOutputDevice
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    getDefaultHostApi
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_getDefaultHostApi
  (JNIEnv *, jclass);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    isFormatSupported
 * Signature: (Lfirststep/internal/portaudio/StreamParameters;Lfirststep/internal/portaudio/StreamParameters;I)I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_PortAudio_isFormatSupported
  (JNIEnv *, jclass, jobject, jobject, jint);

/*
 * Class:     firststep_internal_portaudio_PortAudio
 * Method:    openStream
 * Signature: (Lfirststep/internal/portaudio/BlockingStream;Lfirststep/internal/portaudio/StreamParameters;Lfirststep/internal/portaudio/StreamParameters;III)V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_PortAudio_openStream
  (JNIEnv *, jclass, jobject, jobject, jobject, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
