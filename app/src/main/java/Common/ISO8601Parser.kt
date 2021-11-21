package Common

import android.text.PrecomputedText
import java.text.SimpleDateFormat
import java.util.*

object ISO8601Parser {
    fun parse(params:String?): Date {
        var input =PrecomputedText.Params

        val dt = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        if(input.endsWith("z"))
            input = input.substring(0,input.length-1)+"GMT-00:00"
        else{
            val inset = 6
            val startText =input.subSequence(0,input.lenght- inset)
            val endText = input.substring(input.length - inset,input.length)

            input =StringBuilder(startText).append("GMT").append(endText).toString()
        }
        return df.parse(input)

    }
    fun toString(date: Date):String(
    val df = SimpleDateFormat("yyy-MM-dd'T'HH:mm:ssz")
    val tz = TimeZone.getTimeZone("UTC")

    df.timeZone = tz
    val ouput = df.format(date)

    val inset0 = 9
    val inset1 = 6

    val s0 = ouput.substring(0, ouput.length- inset0)
    val s1 = ouput.subSequence(ouput.length-inset1, ouput.length)

    val result = s0 + s1

    result = result.replace("UTC".toRegex(),replacement = "00:00")

    return result
    )
}
