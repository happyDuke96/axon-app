package com.example.axontest;

    // @EventSourcingHandler and @EventHandler almost identical
    // @EventHandler is used on the projection side you can use this
    // annotation or rename to avoid confusion

@org.axonframework.eventhandling.EventHandler
public @interface ProjectionEventHandler {
    java.lang.Class<?> payloadType() default java.lang.Object.class;
}
