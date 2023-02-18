/*
    NAME: ERTUGRUL SAHIN , JOHN OLAYENI , LEVI MAXWELL
    STUDENT NUMBER : A00270022, A00260853 , A00263436
    DESCRIPTION :
    The program creates a class of dice. This dice class has variables name, number of side and currentside. There are 3 constructors in the class.
    At the same time, a random number is generated to get the currentside value in the class. The program finds the value with the highest current side value.
    Finally, 5 dice with 6 corners are formed and thrown, and the dice are rolled until these dice have the same surfaces. There is the total number of dice rolled.

 */


fun main(args: Array<String>) {


    val dice:Dice =Dice()
    val dice1:Dice =Dice(20)
    val dice2:Dice =Dice("d10",10)
    val highestValueList= ArrayList<Int>()


    println("Creating a default ${dice.name}...")
    println("Creating a default ${dice1.name}...")
    println("Creating percentile die (a special ${dice2.name})...")
    println("The current side up for ${dice.name} is ${dice.currentSideUp}")
    println("The current side up for ${dice1.name} is ${dice1.currentSideUp}")
    println("The current side up for Percentile is ${dice2.currentSideUp}\n")
    highestValueList.add(dice1.currentSideUp)




    println("Testing the roll method\n")

    println("Rolling the ${dice.name}...")
    dice.roll()
    println("The new value is ${dice.currentSideUp}")

    println("Rolling the ${dice1.name}...")
    dice1.roll()
    println("The new value is ${dice1.currentSideUp}")
    highestValueList.add(dice1.currentSideUp)

    println("Rolling the Percentile...")
    dice2.roll()
    println("The new value is ${dice2.currentSideUp}\n")


    dice1.roll()
    println("The New value ${dice1.name} is ${dice1.currentSideUp}")
    highestValueList.add(dice1.currentSideUp)
    val highestValue:Int=highestValueList.maxOrNull() ?:0
    println("Setting the ${dice1.name} to show ${highestValue}")
    println("The side up is now ${highestValue}.\n")


    val newDice: Dice = Dice(6)
    println("Creating ${newDice.name}...")
    println("YAHTZEE! It took ${fiveDiceTotalRoll(newDice)} rolls")
}

// 5 tane zar oluşturulcak 5 tane zarın sonucu aynı olana kadar zarlar atılcak aynı olunca kaç kere zar atıldığı bulunucak
fun fiveDiceTotalRoll(dice:Dice): Int {

    var firstRollDices = ArrayList<Int>()
    var secoundRollDices = ArrayList<Int>()
    var firstListRollSize: Int = 0
    var secoundRollSize: Int = 0


    val firstRollDicesMap = rollDice(dice, 5)
    // The list returned from the function and the number of times dice are rolled to reach the list are equal to the global value.
    firstRollDices = firstRollDicesMap.keys.first()
    firstListRollSize = firstRollDicesMap.values.first()


    println(findDuplicatesValues(firstRollDices))

    val duplicateList = findDuplicatesValues(firstRollDices)

    if (duplicateList.values.first() == 5) {
        // If it is repeated 5 times, each number is the same, which is the result we want.
        return duplicateList.size
    }
    // Finding the number of times the most repeated number is repeated
    val maxValue = duplicateList.values.maxOrNull() ?: 0
    // If the number of repeating digits is not one
    if (maxValue != 1) {
        // Most repeated number
        val keys = duplicateList.filterValues { it == maxValue }.keys
        println(maxValue)
        println(keys.first())
        secoundRollDices = rollDiceWithRemainingAndDuplucate(dice, 5 - maxValue, keys.first())
        secoundRollSize = secoundRollDices.size
        println("İlk tekrar sayısı ${firstListRollSize}")
        println("İkinci tekrar sayısı ${secoundRollSize}")


    }

    // we add the value of the first number of dice and the number of the second dice,
    // this returns us the information of how many dice were rolled when the same 5 dice are rolled, until the same numbers come
    return firstListRollSize + secoundRollSize

}

fun rollDiceWithRemainingAndDuplucate(dice: Dice, remaining: Int, duplicateValue: Int): ArrayList<Int> {
    // Here, the dice are rolled again as much as the remaining number.
    // Our goal is to reach the most repetitive duplicate value.
    // This method returns how many dice were rolled back

    val highestValueList = ArrayList<Int>()
    val highestValueList1 = ArrayList<Int>()
    var rollAgain: Boolean = true

    while (rollAgain) {
        loop@ for (item in 1..Int.MAX_VALUE) {
            dice.roll()
            if (dice.currentSideUp == duplicateValue) {
                println("$item repeating number ${dice.currentSideUp}")
                highestValueList1.add(item)
            }
            highestValueList.add(dice.currentSideUp)
            if (highestValueList1.size == remaining) {
                rollAgain = false
                break@loop
            }
        }
    }

    return highestValueList
}

fun rollDice(dice: Dice, i: Int): MutableMap<ArrayList<Int>, Int> {
    // In the first 5 dice rolls, if all numbers are different from each other, they are rolled again. Our goal is to toss until it's the same.
    // If a number repeats more than 1, it means that we have reached the desired list.
    // Function returns list and how many dice were rolled to reach this list
    val rollCurrentSiteUpList = ArrayList<Int>()
    var rollAgain: Boolean = true
    var timesWokingWhile: Int = 0
    var roolSiteUpMap = mutableMapOf<ArrayList<Int>, Int>()

    while (rollAgain) {
        timesWokingWhile++
        for (item in 1..i) {
            dice.roll()
            rollCurrentSiteUpList.add(dice.currentSideUp)
        }
        // Checking if each element in the list is different
        if (!(allElementsDifferent(rollCurrentSiteUpList))) {
            rollAgain = false
        } else {
            rollCurrentSiteUpList.clear()
        }

    }
    roolSiteUpMap.putIfAbsent(rollCurrentSiteUpList, (timesWokingWhile * i))
    return roolSiteUpMap
}

fun allElementsDifferent(list: List<Int>): Boolean {
    return list.distinct().size == list.size
}


fun findDuplicatesValues(highestValueList: ArrayList<Int>): Map<Int, Int> {
    // Finding how many times each element in the list is repeated and assigned to the map list
    return highestValueList.groupingBy { it }.eachCount().filter { it.value >= 1 }
}


