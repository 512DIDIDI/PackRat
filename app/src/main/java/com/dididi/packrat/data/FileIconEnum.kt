package com.dididi.packrat.data


/**
 * @author dididi(yechao)
 * @since 09/10/2019
 * @describe
 */
@Suppress("UNUSED_PARAMETER")
object Format{
    fun getFormat(extensions:String){
        FileIconEnum.values().forEach {

        }
    }
}
@Suppress("unused", "UNUSED_PARAMETER")
enum class FileIconEnum(var type: String, var icon: Int, vararg formats: String) {


}