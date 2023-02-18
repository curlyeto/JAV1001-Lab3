import kotlin.random.Random
class Dice {
    val name:String
    val numberOfSides:Int
    var currentSideUp:Int=1


    constructor() {
        name="d6"
        numberOfSides=6
        roll()

    }
    constructor(numberOfSides:Int) {
        name="d${numberOfSides}"
        this.numberOfSides=numberOfSides
        roll()
    }

    constructor(name: String, numberOfSides: Int) {
        this.name = name
        this.numberOfSides = numberOfSides
        roll()
    }
    // we gave public because we want to access DiceGame.kt file
    public fun roll() {
        // Generates random characters from 1 to the number of sides of the dice
        this.currentSideUp= Random.nextInt(1, numberOfSides)
    }

}

