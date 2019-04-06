package com.kkpa.tutorialkotlin.Unicode

fun main(args: Array<String>) {
    var emostring = "\uD83D\uDE00\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC66\u200D\uD83D\uDC66"
    val happy = "\uD83D\uDE00" // Happy

    emostring = emostring.plus(happy)
    println(emostring.codePointCount(0, emostring.length))
    println(emostring.codePoints().count())
    println(fancyCount2(emostring))

    println(arrayOf(emostring).size)

    val a = 97
    println(String.format("0x",a))


}

fun fancyCount2(str: String): Int{
    val  joiner = "\\u{200D}";
    val split = str.split(joiner);
    var count = 0;

    for(s in split){
        //removing the variation selectors
        val num = s.split("/[\\ufe00-\\ufe0f]/").joinToString { "" }.length
        count += num;
    }

    //assuming the joiners are used appropriately
    return count / split.size;
}