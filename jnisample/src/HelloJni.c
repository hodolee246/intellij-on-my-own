#include "HelloJni.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_HelloJni_printHello(JNIEnv *env, jobject obj)
{
    printf("Hello World");
}