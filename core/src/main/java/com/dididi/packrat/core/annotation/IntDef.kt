package com.dididi.packrat.core.annotation

/**
 * author: yechao
 * desc:
 * createTime:2020-12-18
 */
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class IntDef(
    /** Defines the allowed constants for this element  */
    vararg val value: Int = [],
    /** Defines whether the constants can be used as a flag, or just as an enum (the default)  */
    val flag: Boolean = false,
    /**
     * Whether any other values are allowed. Normally this is
     * not the case, but this allows you to specify a set of
     * expected constants, which helps code completion in the IDE
     * and documentation generation and so on, but without
     * flagging compilation warnings if other values are specified.
     */
    val open: Boolean = false
)