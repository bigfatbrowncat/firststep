/* DO NOT EDIT THIS FILE - it is machine generated */
#if defined(__APPLE__)
#include <JavaVM/jni.h>
#else
#include <jni.h>
#endif

/* Header for class firststep_internal_portaudio_BlockingStream */

#ifndef _Included_firststep_internal_portaudio_BlockingStream
#define _Included_firststep_internal_portaudio_BlockingStream
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    getReadAvailable
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_BlockingStream_getReadAvailable
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    getWriteAvailable
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_firststep_internal_portaudio_BlockingStream_getWriteAvailable
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    readFloats
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_readFloats
  (JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    writeFloats
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_writeFloats
  (JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    readShorts
 * Signature: ([SI)Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_readShorts
  (JNIEnv *, jobject, jshortArray, jint);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    writeShorts
 * Signature: ([SI)Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_writeShorts
  (JNIEnv *, jobject, jshortArray, jint);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_BlockingStream_start
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_BlockingStream_stop
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    abort
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_BlockingStream_abort
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_BlockingStream_close
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    isStopped
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_isStopped
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    isActive
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_firststep_internal_portaudio_BlockingStream_isActive
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    getTime
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_firststep_internal_portaudio_BlockingStream_getTime
  (JNIEnv *, jobject);

/*
 * Class:     firststep_internal_portaudio_BlockingStream
 * Method:    getInfo
 * Signature: (Lfirststep/internal/portaudio/StreamInfo;)V
 */
JNIEXPORT void JNICALL Java_firststep_internal_portaudio_BlockingStream_getInfo
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
