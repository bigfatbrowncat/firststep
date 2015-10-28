#include <jni.h>
#include <string.h>

#include "gl-header.h"

void getGLVersion(int *major, int *minor)
{
    const char *verstr = (const char *) glGetString(GL_VERSION);
    if ((verstr == NULL) || (sscanf(verstr,"%d.%d", major, minor) != 2))
    {
        *major = *minor = 0;
        fprintf(stderr, "Invalid GL_VERSION format!!!\n");
    }
}

void getGLSLVersion(int *major, int *minor)
{
    int gl_major, gl_minor;
    getGLVersion(&gl_major, &gl_minor);

    *major = *minor = 0;
    if (gl_major == 1)
    {
        /* GL v1.x can only provide GLSL v1.00 as an extension */
        const char *extstr = (const char *) glGetString(GL_EXTENSIONS);
        if ((extstr != NULL) &&
            (strstr(extstr, "GL_ARB_shading_language_100") != NULL))
        {
            *major = 1;
            *minor = 0;
        }
    }
    else if (gl_major >= 2)
    {
        /* GL v2.0 and greater must parse the version string */
        const char *verstr =
            (const char *) glGetString(GL_SHADING_LANGUAGE_VERSION);

        if((verstr == NULL) ||
            (sscanf(verstr, "%d.%d", major, minor) != 2))
        {
            *major = *minor = 0;
            fprintf(stderr,
                "Invalid GL_SHADING_LANGUAGE_VERSION format!!!\n");
        }
    }
}

extern "C"
{
	JNIEXPORT jboolean JNICALL Java_firststep_internal_GL3W_init(JNIEnv * env, jclass clz)
	{
		printf("no glew here\n");
		/*glewExperimental = true;		// Fixes crash on OSX
		if (glewInit() == GLEW_OK) {
			// We need to clear a GL_INVALID_ENUM (0x500) error
			int err;
			do {
				err = glGetError();
				if (err != 0) printf("glGetError after glewInit() returns 0x%x. Ignoring this.\n", err); fflush(stdout);
			} while (err != 0);
			return true;
		} else {
			return false;
		}*/
		return true;
	}

	JNIEXPORT jint JNICALL Java_firststep_internal_GL3W_getGLVersionMajor(JNIEnv * env, jclass clz)
	{
		int major, minor;
		getGLVersion(&major, &minor);
		return major;
	}

	JNIEXPORT jint JNICALL Java_firststep_internal_GL3W_getGLVersionMinor(JNIEnv * env, jclass clz)
	{
		int major, minor;
		getGLVersion(&major, &minor);
		return minor;
	}

	JNIEXPORT jint JNICALL Java_firststep_internal_GL3W_getGLSLVersionMajor(JNIEnv * env, jclass clz)
	{
		int major, minor;
		getGLSLVersion(&major, &minor);
		return major;
	}

	JNIEXPORT jint JNICALL Java_firststep_internal_GL3W_getGLSLVersionMinor(JNIEnv * env, jclass clz)
	{
		int major, minor;
		getGLSLVersion(&major, &minor);
		return minor;
	}

	// Some OpenGL 3 bindings

	JNIEXPORT void JNICALL Java_firststep_internal_GL3W_glViewport(JNIEnv * env, jclass clz, jint x, jint y, jint width, jint height)
	{
		glViewport(x, y, width, height);
	}
	JNIEXPORT void JNICALL Java_firststep_internal_GL3W_glClearColor(JNIEnv * env, jclass clz, jfloat red, jfloat green, jfloat blue, jfloat alpha)
	{
		glClearColor(red, green, blue, alpha);
	}
	JNIEXPORT void JNICALL Java_firststep_internal_GL3W_glClear(JNIEnv * env, jclass clz, jlong mask)
	{
		glClear(mask);
	}

}
