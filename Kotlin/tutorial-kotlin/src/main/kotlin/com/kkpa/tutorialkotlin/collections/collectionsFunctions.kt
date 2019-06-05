package com.kkpa.tutorialkotlin.collections

enum class FraudFlagType {
    APPLICATION,
    ACCOUNT_TAKEOVER,
    ACH,
    APEX_DO_NO_BUSINESS,
    APEX_REJECTED,
    CHECK,
    DEBIT_CARD,
    ID_THEFT,
    SYNTHETIC_ID,
    WIRE
}


fun main(args: Array<String>) {

    val confirmedFlags = arrayListOf<String>("ACH")
    val confirmedString = FraudFlagType.values().map { flag -> flag.name }.toList()



    println(confirmedFlags.intersect(FraudFlagType.values().toList()).any())
    println(confirmedFlags.intersect(confirmedString).any())


    val colors = arrayListOf("Yellow" , "Red" , "Blue", "Yellow")
    val seasons = arrayListOf("Fall" , "Summer" , "Spring" , "Spring")

    println(colors.asReversed())
    println(colors.getOrNull(5))

    // If lists have same size, convert into Pair List
    val zipped = colors.zip(seasons)
    println("${zipped.get(0).first} - ${zipped.get(0).second}")

    // Merging List
    val merged = colors + seasons
    println(merged)

    // Removing Duplication Merging in a list
    val noDupsList = colors.union(seasons)
    println(noDupsList)

    // Removing duplicates into a list.
    val noDupColors = colors.distinct()
    println(noDupColors)


    // To MutableList
    val mutableSeasons = seasons.toMutableList()
    mutableSeasons.add("Fall")
    println(mutableSeasons)

    // Hashmap
    val mutableHashMap =  hashMapOf(1 to Car("green" , " Toyota" , 2015) ,
            2 to Car("red" , "Ford" , 2016) ,
            3 to Car("silver" , "Honda" , 2013),
            4 to Car("silver" , "Renault" , 2013),
            5 to Car("silver" , "Mazda" , 2013)
    )

    println("Any From Mutable " + mutableHashMap.any{
        it.value.year > 2013
    })

    println("Count >  2013  " + mutableHashMap.count{
        it.value.year > 2013
    })


    // Group By
    println("GroupBy Color " + mutableHashMap.values.groupBy {
        it.color
    })

    // Sort By Year
    println("Sorted By Year " + mutableHashMap.values.sortedBy { it.year })



}
