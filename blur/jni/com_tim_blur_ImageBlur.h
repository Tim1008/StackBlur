/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_tim_blur_ImageBlur */

#ifndef _Included_com_tim_blur_ImageBlur
#define _Included_com_tim_blur_ImageBlur
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_tim_blur_ImageBlur
 * Method:    blurIntArray
 * Signature: ([IIII)V
 */
JNIEXPORT void JNICALL Java_com_tim_blur_ImageBlur_blurIntArray
  (JNIEnv *, jclass, jintArray, jint, jint, jint);

/*
 * Class:     com_tim_blur_ImageBlur
 * Method:    blurBitMap
 * Signature: (Landroid/graphics/Bitmap;I)V
 */
JNIEXPORT void JNICALL Java_com_tim_blur_ImageBlur_blurBitMap
  (JNIEnv *, jclass, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif