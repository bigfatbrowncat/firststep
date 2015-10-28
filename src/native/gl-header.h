#ifndef GL_HEADER_H
#define GL_HEADER_H

#if defined USE_GLEW_GL3		// Defined in CMakeLists.txt

	#ifndef GLEW_STATIC
	#define GLEW_STATIC
	#endif
	#include <GL/glew.h>
	
	#define NANOVG_GL3 1

	// Solves problem with some old Intel chipsets
	#define NANOVG_GL_USE_UNIFORMBUFFER 0	

#else if defined USE_EGL_GLES2		// Defined in CMakeLists.txt

	#define GLFW_INCLUDE_ES2
	#define NANOVG_GLES2 1

#endif

#include <GLFW/glfw3.h>
#include <nanovg.h>

#define NANOVG_GL_IMPLEMENTATION 1

#endif
