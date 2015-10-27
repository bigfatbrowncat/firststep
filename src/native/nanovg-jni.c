#include <stdio.h> // for lameass debugging (printf)
#include <assert.h> // slightly less lame debugging
#include <stdlib.h>

#include <jni.h>

#ifndef GLEW_STATIC
#define GLEW_STATIC
#endif
#include <GL/glew.h>

#include <nanovg.h>

//#define NANOVG_GL3 1
#define NANOVG_GL2 1

#define NANOVG_GL_IMPLEMENTATION 1
#define NANOVG_GL_USE_UNIFORMBUFFER 0

#include <nanovg_gl.h>
#include <nanovg_gl_utils.h>

#define checkJO(JO, MSG) if (JO==NULL) { printf(MSG); fflush(stdout); }


// used for debuging : from the gnu libc manual
/*
#include <execinfo.h>
void print_trace (void)
{
  void *array[10];
  size_t size;
  char **strings;
  size_t i;

  size = backtrace (array, 10);

  strings = backtrace_symbols (array, size);

  printf ("Obtained %zd stack frames.\n", size);

  for (i = 0; i < size; i++)
     printf ("%s\n", strings[i]);

  fflush(stdout);
  free (strings);
}
*/

// both nvg.Paint and nvg.RGBA have a field "buff" this gets the
// physical address of this buffer
void* getBuffPtr(JNIEnv *e, jobject jo)
{
	jobject cls = (*e)->GetObjectClass(e, jo);
	jfieldID fid = (*e)->GetFieldID(e, cls,"buff","Ljava/nio/FloatBuffer;");
	jobject jbuff = (*e)->GetObjectField(e, jo, fid);
	return (*e)->GetDirectBufferAddress(e, jbuff);
}

JNIEXPORT jlong JNICALL Java_firststep_internal_NVG_test
  (JNIEnv *e, jclass c, jint i)
{
	printf("i=%i\n",i);
	//printf("sizeof NVGpaint=%i\n",sizeof(NVGpaint));
	//printf("sizeof NVGpaint=%i\n",sizeof(NVGcolor));

	// this is only here in case of a change with nanovg struct sizes
	// failure of these asserts will need nvg subclass buffer changes
	assert(sizeof(NVGpaint)==76);
	assert(sizeof(NVGcolor)==16);

	return glGetError();
}


JNIEXPORT jlong JNICALL Java_firststep_internal_NVG_create
  (JNIEnv *e, jclass c, jint flags)
{
#if defined NANOVG_GL2
	return (jlong)(NVGcontext*) nvgCreateGL2(flags);
#elif defined NANOVG_GL3
	return (jlong)(NVGcontext*) nvgCreateGL3(flags);
#elif defined NANOVG_GLES2
	return (jlong)(NVGcontext*) nvgCreateGLES2(flags);
#elif defined NANOVG_GLES3
	return (jlong)(NVGcontext*) nvgCreateGLES3(flags);
#endif

}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_delete
  (JNIEnv *e, jclass c, jlong ctx)
{
#if defined NANOVG_GL2
	nvgDeleteGL2((NVGcontext*)ctx);
#elif defined NANOVG_GL3
	nvgDeleteGL3((NVGcontext*)ctx);
#elif defined NANOVG_GLES2
	nvgDeleteGLES2((NVGcontext*)ctx);
#elif defined NANOVG_GLES3
	nvgDeleteGLES3((NVGcontext*)ctx);
#endif
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_beginFrame
  (JNIEnv *e, jclass c, jlong ctx, jint wid, jint hgt, jfloat r)
{
	nvgBeginFrame((NVGcontext*)ctx, wid, hgt, r);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_cancelFrame
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgCancelFrame((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_endFrame
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgEndFrame((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_beginPath
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgBeginPath((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_stroke
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgStroke((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_moveTo
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y)
{
	nvgMoveTo((NVGcontext*)ctx,x,y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_lineTo
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y)
{
	nvgLineTo((NVGcontext*)ctx,x,y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fillColor
  (JNIEnv *e, jclass c, jlong ctx, jobject jo)
{
	checkJO(jo,"\nNull colour was passed to fillColor\n");
	nvgFillColor((NVGcontext*)ctx, (NVGcolor)*((NVGcolor*)getBuffPtr(e,jo)));
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fillColorf
  (JNIEnv *e, jclass c, jlong ctx, jfloat r, jfloat g, jfloat b, jfloat a)
{
	nvgFillColor((NVGcontext*)ctx, nvgRGBAf(r,g,b,a) );
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_strokeColor
  (JNIEnv *e, jclass c, jlong ctx, jobject jo)
{
	checkJO(jo,"\nNull colour was passed to strokeColor\n");
	nvgStrokeColor((NVGcontext*)ctx, (NVGcolor)*((NVGcolor*)getBuffPtr(e,jo)));
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_strokeColorf
  (JNIEnv *e, jclass c, jlong ctx, jfloat r, jfloat g, jfloat b, jfloat a)
{
	nvgStrokeColor((NVGcontext*)ctx, nvgRGBAf(r,g,b,a));
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_strokeWidth
  (JNIEnv *e, jclass c, jlong ctx, jfloat w)
{
	nvgStrokeWidth((NVGcontext*)ctx, w);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_rect
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y, jfloat w, jfloat h)
{
	nvgRect((NVGcontext*)ctx, x,y, w,h);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fill
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgFill((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_save
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgSave((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_restore
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgRestore((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_reset
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgReset((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_miterLimit
  (JNIEnv *e, jclass c, jlong ctx, jfloat l)
{
	nvgMiterLimit((NVGcontext*)ctx, l);
}


JNIEXPORT void JNICALL Java_firststep_internal_NVG_quadTo
  (JNIEnv *e, jclass c, jlong ctx, jfloat cx, jfloat cy, jfloat x, jfloat y)
{
	nvgQuadTo((NVGcontext*)ctx, cx,cy, x,y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_bezierTo
  (JNIEnv *e, jclass c, jlong ctx, jfloat c1x, jfloat c1y, jfloat c2x, jfloat c2y, jfloat x, jfloat y)
{
	nvgBezierTo((NVGcontext*)ctx, c1x,c1y, c2x,c2y, x,y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_lineCap
  (JNIEnv *e, jclass c, jlong ctx, jint cap)
{
	nvgLineCap((NVGcontext*)ctx, cap);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_lineJoin
  (JNIEnv *e, jclass c, jlong ctx, jint join)
{
	nvgLineJoin((NVGcontext*)ctx, join);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_circle
  (JNIEnv *e, jclass c, jlong ctx, jfloat cx, jfloat cy, jfloat r)
{
	nvgCircle((NVGcontext*)ctx, cx, cy, r);
}

JNIEXPORT int JNICALL Java_firststep_internal_NVG_createImage
  (JNIEnv *e, jclass c, jlong ctx, jstring fname, jint flags)
{
	int r;
    const char *filename = (*e)->GetStringUTFChars(e, fname, 0);

	r = nvgCreateImage((NVGcontext*)ctx, filename, flags);

    (*e)->ReleaseStringUTFChars(e, fname, filename);
	return r;
}

JNIEXPORT int JNICALL Java_firststep_internal_NVG_createImageMem
  (JNIEnv *e, jclass c, jlong ctx, jbyteArray jdata, jint flags)
{
	int ndata = (*e)->GetArrayLength(e, jdata);
	unsigned char* data = (unsigned char*)(*e)->GetByteArrayElements(e, jdata, NULL);

	int r;
	r = nvgCreateImageMem((NVGcontext*)ctx, flags, data, ndata);

    (*e)->ReleaseByteArrayElements(e, jdata, (jbyte*)data, 0);
	return r;
}


//void nvgImageSize(NVGcontext* ctx, int image, int* w, int* h);
JNIEXPORT void JNICALL Java_firststep_internal_NVG_imageSize
  (JNIEnv *e, jclass c, jlong ctx, jint image, jintArray dims)
{
	int w, h;
	nvgImageSize((NVGcontext*)ctx, image, &w, &h);

	jint *dimsArray = (*e)->GetIntArrayElements(e, dims, 0);
	dimsArray[0] = w;
	dimsArray[1] = h;
	(*e)->ReleaseIntArrayElements(e, dims, dimsArray, 0);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_deleteImage
  (JNIEnv *e, jclass c, jlong ctx, jint im)
{
	nvgDeleteImage((NVGcontext*)ctx, im);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_roundedRect
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y, jfloat w, jfloat h, jfloat r)
{
	nvgRoundedRect((NVGcontext*)ctx, x,y, w,h, r);
}


JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1imagePattern
  (JNIEnv *e, jclass c, jlong ctx, jobject paint, jfloat ox, jfloat oy,
		jfloat ex, jfloat ey, jfloat angle, jint image, jfloat alpha)
{
	// paint can't be null as it's passed internally as the objects instance as a convenience
	NVGpaint p = nvgImagePattern((NVGcontext*)ctx, ox, oy, ex, ey, angle, image, alpha);
	NVGpaint* dp = getBuffPtr(e,paint);
	*dp = p;
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1linearGradient
  (JNIEnv *e, jclass cls, jlong ctx, jobject paint, jfloat sx, jfloat sy, jfloat ex, jfloat ey, jobject scol, jobject ecol)
{
	checkJO(scol,"\nLinearGradient was passed NULL start colour\n");
	checkJO(ecol,"\nLinearGradient was passed NULL end colour\n");
	NVGpaint p = nvgLinearGradient((NVGcontext*)ctx,sx,sy,ex,ey,
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,scol)),
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,ecol)));
	NVGpaint* dp = (NVGpaint*)getBuffPtr(e,paint);
	*dp = p;
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1boxGradient
  (JNIEnv *e, jclass cls, jlong ctx, jobject paint, jfloat x, jfloat y,
				jfloat w, jfloat h, jfloat r, jfloat f, jobject scol, jobject ecol)
{
	checkJO(scol,"\nBoxGradient was passed NULL start colour\n");
	checkJO(ecol,"\nBoxGradient was passed NULL end colour\n");
	NVGpaint p = nvgBoxGradient((NVGcontext*)ctx, x, y, w, h, r, f,
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,scol)),
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,ecol)));
	NVGpaint* dp = (NVGpaint*)getBuffPtr(e,paint);
	*dp = p;
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1radialGradient
  (JNIEnv *e, jclass c, jlong ctx, jobject paint, jfloat cx, jfloat cy, jfloat inr, jfloat outr, jobject scol, jobject ecol)
{
	checkJO(scol,"\nRadialGradient was passed NULL start colour\n");
	checkJO(ecol,"\nRadialGradient was passed NULL end colour\n");
	NVGpaint p = nvgRadialGradient((NVGcontext*)ctx, cx, cy, inr, outr,
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,scol)),
					(NVGcolor)*((NVGcolor*)getBuffPtr(e,ecol)));
	NVGpaint* dp = (NVGpaint*)getBuffPtr(e,paint);
	*dp = p;
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fillPaint
  (JNIEnv *e, jclass c, jlong ctx, jobject paint)
{
	checkJO(paint,"\nFillPaint was passed NULL paint\n");
	nvgFillPaint((NVGcontext*)ctx, (NVGpaint)*((NVGpaint*)getBuffPtr(e,paint)));
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_scissor
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y, jfloat w, jfloat h)
{
	nvgScissor((NVGcontext*)ctx, x,y, w,h);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_intersectScissor
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y, jfloat w, jfloat h)
{
	nvgIntersectScissor((NVGcontext*)ctx, x, y, w, h);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_resetScissor
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgResetScissor((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fontSize
  (JNIEnv *e, jclass c, jlong ctx, jfloat sz)
{
	nvgFontSize((NVGcontext*)ctx, sz);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fontFace
  (JNIEnv *e, jclass c, jlong ctx, jstring jfont)
{
	checkJO(jfont, "\nfontFace was passed a NULL string\n");
    const char *font = (*e)->GetStringUTFChars(e, jfont, 0);

	nvgFontFace((NVGcontext*)ctx, font);

    (*e)->ReleaseStringUTFChars(e, jfont, font);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_textAlign
  (JNIEnv *e, jclass c, jlong ctx, jint align)
{
	nvgTextAlign((NVGcontext*)ctx, align);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fontBlur
  (JNIEnv *e, jclass c, jlong ctx, jfloat blr)
{
	nvgFontBlur((NVGcontext*)ctx, blr);
}

JNIEXPORT jfloat JNICALL Java_firststep_internal_NVG_text
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y, jstring jmesg)
{
	checkJO(jmesg,"\nvg.Text was passed a NULL string\n");
    const char *mesg = (*e)->GetStringUTFChars(e, jmesg, 0);

	float r=nvgText((NVGcontext*)ctx, x,y, mesg, NULL);

    (*e)->ReleaseStringUTFChars(e, jmesg, mesg);
    return r;
}

JNIEXPORT jint JNICALL Java_firststep_internal_NVG_createFont
  (JNIEnv *e, jclass c, jlong ctx, jstring jname, jstring jpath)
{
	checkJO(jname,"\ncreateFont was passed a NULL name string\n");
	checkJO(jpath,"\ncreateFont was passed a NULL path string\n");

	const char *name = (*e)->GetStringUTFChars(e, jname, 0);
	const char *path = (*e)->GetStringUTFChars(e, jpath, 0);

	int r = nvgCreateFont((NVGcontext*)ctx, name, path);

    (*e)->ReleaseStringUTFChars(e, jname, name);
    (*e)->ReleaseStringUTFChars(e, jpath, path);

	return r;
}

JNIEXPORT int JNICALL Java_firststep_internal_NVG_createFontMem
  (JNIEnv *e, jclass c, jlong ctx, jstring jname, jbyteArray jdata)
{
	checkJO(jname,"\ncreateFontMem was passed a NULL name string\n");
	checkJO(jdata,"\ncreateFontMem was passed a NULL data\n");
	const char *name = (*e)->GetStringUTFChars(e, jname, 0);
	int ndata = (*e)->GetArrayLength(e, jdata);
	unsigned char* data = (unsigned char*)(*e)->GetByteArrayElements(e, jdata, NULL);

	int r;
	unsigned char* fData = (unsigned char*)malloc(ndata);
	memcpy(fData, data, ndata);
	r = nvgCreateFontMem((NVGcontext*)ctx, name, fData, ndata, 1);

    (*e)->ReleaseByteArrayElements(e, jdata, (jbyte*)data, 0);
    (*e)->ReleaseStringUTFChars(e, jname, name);
	return r;
}


JNIEXPORT void JNICALL Java_firststep_internal_NVG_rotate
  (JNIEnv *e, jclass c, jlong ctx, jfloat angle)
{
	nvgRotate((NVGcontext*)ctx, angle);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_resetTransform
  (JNIEnv *e, jclass c, jlong ctx)
{
	nvgResetTransform((NVGcontext*)ctx);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_translate
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y)
{
	nvgTranslate((NVGcontext*)ctx, x, y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_scale
  (JNIEnv *e, jclass c, jlong ctx, jfloat x, jfloat y)
{
	nvgScale((NVGcontext*)ctx, x, y);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_strokePaint
  (JNIEnv *e, jclass c, jlong ctx, jobject paint)
{
	checkJO(paint,"\nstrokePaint was passed a NULL paint\n");
	nvgStrokePaint((NVGcontext*)ctx, (NVGpaint)*((NVGpaint*)getBuffPtr(e,paint)));
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_globalAlpha
  (JNIEnv *e, jclass c, jlong ctx, jfloat alpha)
{
	nvgGlobalAlpha((NVGcontext*)ctx, alpha);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_transform
  (JNIEnv *env, jclass cls, jlong ctx, jfloat a, jfloat b, jfloat c, jfloat d, jfloat e, jfloat f)
{
	nvgTransform((NVGcontext*)ctx, a, b, c, d, e, f);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_skewX
  (JNIEnv *e, jclass c, jlong ctx, jfloat angle)
{
	nvgSkewX((NVGcontext*)ctx, angle);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_skewY
  (JNIEnv *e, jclass c, jlong ctx, jfloat angle)
{
	nvgSkewY((NVGcontext*)ctx, angle);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_arcTo
  (JNIEnv *e, jclass c, jlong ctx, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jfloat radius)
{
	nvgArcTo((NVGcontext*)ctx, x1, y1, x2, y2, radius);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_pathWinding
  (JNIEnv *e, jclass c, jlong ctx, jint dir)
{
	nvgPathWinding((NVGcontext*)ctx, dir);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_arc
  (JNIEnv *e, jclass c, jlong ctx, jfloat cx, jfloat cy, jfloat r, jfloat a0, jfloat a1, jint dir)
{
	nvgArc((NVGcontext*)ctx, cx, cy, r, a0, a1, dir);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_ellipse
  (JNIEnv *e, jclass c, jlong ctx, jfloat cx, jfloat cy, jfloat rx, jfloat ry)
{
	nvgEllipse((NVGcontext*)ctx, cx, cy, rx, ry);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_textLetterSpacing
  (JNIEnv *e, jclass c, jlong ctx, jfloat spacing)
{
	nvgTextLetterSpacing((NVGcontext*)ctx, spacing);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_textLineHeight
  (JNIEnv *e, jclass c, jlong ctx, jfloat lineHeight)
{
	nvgTextLineHeight((NVGcontext*)ctx, lineHeight);
}

JNIEXPORT jint JNICALL Java_firststep_internal_NVG_findFont
  (JNIEnv *e, jclass c, jlong ctx, jstring jname)
{
	checkJO(jname,"\nfindFont was passed a NULL name string\n");
	const char *name = (*e)->GetStringUTFChars(e, jname, 0);

	int r = nvgFindFont((NVGcontext*)ctx, name);

    (*e)->ReleaseStringUTFChars(e, jname, name);

    return r;
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG_fontFaceId
  (JNIEnv *e, jclass c, jlong ctx, jint fid)
{
	nvgFontFaceId((NVGcontext*)ctx, fid);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1getTransform
  (JNIEnv *e, jclass c, jlong ctx, jobject jdest)
{
	float* dest = (float*)getBuffPtr(e,jdest);
	nvgCurrentTransform((NVGcontext*)ctx, dest);
}

JNIEXPORT void JNICALL Java_firststep_internal_NVG__1_1setTransform
  (JNIEnv *e, jclass c, jlong ctx, jobject jsrc)
{
	float* src = (float*)getBuffPtr(e,jsrc);
	nvgResetTransform((NVGcontext*)ctx);
	nvgTransform((NVGcontext*)ctx, src[0], src[1], src[2], src[3], src[4], src[5] );
}

// Framebuffers

JNIEXPORT jlong Java_firststep_internal_NVG_createFramebuffer(JNIEnv *e, jclass c, jlong ctx, jint w, jint h, jint imageFlags) {
	return (jlong)nvgluCreateFramebuffer((NVGcontext*)ctx, w, h, imageFlags);
}

JNIEXPORT void Java_firststep_internal_NVG_bindFramebuffer(JNIEnv *e, jclass c, jlong fb) {
	nvgluBindFramebuffer((NVGLUframebuffer*)fb);
}

JNIEXPORT void Java_firststep_internal_NVG_deleteFramebuffer(JNIEnv *e, jclass c, jlong fb) {
	nvgluDeleteFramebuffer((NVGLUframebuffer*)fb);
}

JNIEXPORT jint Java_firststep_internal_NVG_getImageFromFramebuffer(JNIEnv *e, jclass c, jlong fb) {
	return ((NVGLUframebuffer*)fb)->image;
}

// Color

JNIEXPORT jint Java_firststep_internal_NVG_00024Color_sizeOf(JNIEnv *e, jclass c) {
	return sizeof(NVGcolor);
}

JNIEXPORT void Java_firststep_internal_NVG_00024Color_putRGBA(JNIEnv *e, jobject o, jfloat r, jfloat g, jfloat b, jfloat a) {
	NVGcolor c = nvgRGBAf(r, g, b, a);
	void *ptr = getBuffPtr(e, o);
	memcpy(ptr, &c, sizeof(NVGcolor));
}

JNIEXPORT void Java_firststep_internal_NVG_00024Color_putHSLA(JNIEnv *e, jobject o, jfloat h, jfloat s, jfloat l, jfloat a) {
	NVGcolor c = nvgHSLA(h, s, l, (char)(a * 255));
	void *ptr = getBuffPtr(e, o);
	memcpy(ptr, &c, sizeof(NVGcolor));
}
