package org.aftx.holers.android.xreader.service.binder;

import java.lang.annotation.*;

import com.google.inject.BindingAnnotation;

@BindingAnnotation
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GetDbBinder {

}