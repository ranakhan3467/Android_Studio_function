// This is comment

//user define function in kotlin
fun myFunction(x: Int, y: Int): Int {
    return (x + y)
    //go to line 213  happy coding
}

//class in kotlin
class Car {
    var brand = ""
    var model = ""
    var year = 0
    // go to line 218
}

// class using constructor
class Car1(var brand: String, var model: String, var year: Int) // go to line 232

// method inside class + constructor
class Car2(var brand: String, var model: String, var year: Int) {
    // Class function
    fun drive() {
        println("Method inside car2 class: Wrooom!")
    }

    // Class function with parameters
    fun speed(maxSpeed: Int) {
        println("Max speed is: " + maxSpeed)
    }

    //go to line : 239
}

// inheritance in kotlin
// Superclass
open class MyParentClass {
    val x = 5
}

// Subclass
class MyChildClass: MyParentClass() {
    fun myFunction() {
        println(x) // x is now inherited from the superclass
    }
    // goto page 249
}

fun main() {  // main funtion


    println("Hello World!")

    /*The difference between var and val is that variables declared with the var keyword can be changed/modified,
    while val variables cannot.
     */
    val name = "MD Fardin Jaman Aranyak"
    var id = 93

    println("Name: $name")
    println("Id: $id")

    /*You can also declare a variable without assigning the value, and assign
    the value later. However, this is only possible when you specify the type:
     */
    var num1:Int //dont need to assign
    var num2:Int

    num1=10;
    num2=30;
    var sum = num1+num2 // must need to assign bc we dont specify variable type

    println("SUM OF 10 And 30 is: $sum")
    println("")

    //data type in Kotlin
    val myNum: Int = 5                // Int
    val myDoubleNum: Double = 5.99    // Double
    val myLetter: Char = 'D'          // Char
    val myBoolean: Boolean = true     // Boolean
    val myText: String = "Hello"      // String

    /* ***Unlike Java, you cannot use ASCII values to display certain characters.
    The value 66 would output a "B" in Java, but will generate an error in Kotlin:*/

    val myNum1: Long = 15000000000L
    println("Long Data Type $myNum1")

    val myNum2:Double =5E3 // exponential
    println("Double Data Type $myNum2")

    /*To convert a numeric data type to another type, you must use one of the following
    functions: toByte(), toShort(), toInt(), toLong(), toFloat(), toDouble() or toChar():
     */

    /*In Kotlin, numeric type conversion is different from Java. For example,
    it is not possible to convert an Int type to a Long type with the following code:
     */
    val x: Int = 5
    val y: Long = x.toLong()
    println("Int ->toLong: $y")

    /*kotlin operation same as java
     */

    var x1 = 10
    x1 += 5
    println("operation in Kotlin: $x1")

    println("")
    println("")

    // condition  same as c/c++;

    val time = 22
    if (time < 10) {
        println("Good morning.")
    } else if (time < 20) {
        println("Good day.")
    } else {
        println("Good evening.")
    }

    println("")
    println("")

    // When in Kotlin
    /*Instead of writing many if..else expressions, you can use the when expression, which is much easier to read.
    It is used to select one of many code blocks to be executed:*/
    val day = 4

    val result = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day."
    }
    println("Result $result")
    println("")
    println("")

    // while loop are same like java c/c++ happy coding
    var i = 0
    while (i < 5) {
        println("Inside while: $i")
        i++
    }

    var j = 0
    do {
        println("Inside do While: $j")
        j++
    }
    while (j < 5)

    //break and continue

    var k = 0
    while (k < 10) {
        println("Using break: $k")
        k++
        if (k == 4) {
            break
        }
    }

    var l = 0
    while (l < 10) {
        if (l == 4) {
            l++
            continue
        }
        println("using continue: $l");
        l++
    }

    // array-> https://www.w3schools.com/kotlin/kotlin_arrays.php
    println("")
    println("")
    val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
    println(cars[0])
    println("array size: $cars.size")
    //change an array element

    cars[0] = "Opel"
    println("Afeter change new array element: $cars[0]")

   // Check if an Element Exists
    val cars1 = arrayOf("Volvo", "BMW", "Ford", "Mazda")
    if ("Volvo" in cars1) {
        println("It exists!")
    } else {
        println("It does not exist.")
    }

    //Loop Through an Array
    println("Cars Name: ")
    for (x in cars) {
        println(x)
    }
    //loop in ranges
    println("Print for loop in range a to x")
    for (chars in 'a'..'x') {
        println(chars)
    }

    // function call in kotlin

    var results = myFunction(3, 5)
    println("result from user-define funtion: $results")

    // object of a class called car

    val c1 = Car() //object of car
    c1.brand = "Ford"
    c1.model = "Mustang"
    c1.year = 1969

    val c2 = Car() //object of car
    c2.brand = "BMW"
    c2.model = "X5"
    c2.year = 1999

    println("Inside c1 object(without constructor) "+c1.brand)  // Ford
    println("Inside c2 object(without constructor) "+c2.brand)  // BMW

    //object with constructor. constructor use to initialize
    val d1 = Car1("Ford", "Mustang", 1969)
    val d2 = Car1("BMW", "X5", 1999)
    val d3 = Car1("Tesla", "Model S", 2020)

    println("Inside d1 object(with constructor) "+d1.brand)  // Ford
    println("Inside d2 object(with constructor) "+d2.brand)  // BMW
    println("Inside d3 object (with constructor) "+d3.brand)  // Tesla

    val f1 = Car2("Ford", "Mustang", 1969)

    // Call the function
    f1.drive()
    f1.speed(200)

    println("")
    println("")

    val myObj = MyChildClass()
    myObj.myFunction()

    // Example and exercise https://www.w3schools.com/kotlin/kotlin_examples.php
}