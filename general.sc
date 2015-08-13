import java.util.Date

val list = List(1,2,3,4,5)
list.foreach(num => print(num +1)) //won't return anything


val list2 = List(1,2,3,4,6,7,8,12,14,13,14,15,15,17,18)

val oddOne:PartialFunction[Int, Int] = {
  case x: Int if x % 2 == 0 => x * 3
}

val evenOne:PartialFunction[Int, Int] = {
  case x: Int if x % 2 != 0 => x + 5
}



val result0 = list2.collect(oddOne orElse evenOne)

val set1 = list2.toSet
val stream1 = list2.toStream
stream1 take 5 print

stream1 take 2 print


val set2 = Set("Phoenix" -> "Arizona", "Austin" -> "Texas")
set2.toMap

list2.headOption
list2.head

list2.find(_ % 2 != 0)

list2.slice(1,50)


//stream
//can generate value as a stream till infinity
def streamer(a:Int):Stream[Int] = Stream.cons(a, streamer(a+2))
val stream2 = streamer(10)

stream2 take 50 toList

stream2 take 3 toList

(stream2 drop 5) take 5 toList


val isOdd:PartialFunction[Int,String] = {
  case x : Int if x % 2 != 0 && x < 100 => "Its is Odd"
}

val isEven:PartialFunction[Int,String] = {
  case x : Int if x % 2 == 0 => "Its is Odd"
}

val isNeg:PartialFunction[Int,String]={
  case x:Int if x < 0 => "I am Negative"
}

val isGreat:PartialFunction[Int,Int]={
  case x:Int if x > 100 => 100
}

val array = Array(87, 44, 5, 4, 200, 10, 39, 100)

val result3 = array groupBy {
  isOdd orElse
  isEven orElse
  isNeg orElse
  isGreat
}


val result1 = array forall(_ <= 200)

val list3 =  List(5,4,3,2,1)

//fold left
val result4 = (3 /: list3){
  (`pappu1`,`pappu2`) => `pappu1` - `pappu2`
}

val result5 = (((((3 - 5)-4)-3)-2)-1)


val result6 = (list3 :\ 0){
  (`pappu1`,`pappu2`) => `pappu1` - `pappu2`
}

list3.sum


//////////////////////////////////foldLeft is Tail recursion//////////
val MAX_SIZE = 1000000
val startDate = new Date
(1 to MAX_SIZE) reduceLeft (_ + _)
val endDate = new Date
(1 to MAX_SIZE) reduceRight (_ + _)
val superEndDate = new Date


endDate.getTime - startDate.getTime
superEndDate.getTime - endDate.getTime

val list4 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
list4.transpose

list4.mkString(" + ")

list.mkString("<"," = ",">")

val strBuilder = new StringBuilder()
strBuilder.append("Let's c how numbers can be assembled: ")
list.filter(it => it > 2 && it < 8).addString(strBuilder," $ ")
strBuilder.mkString

var history = List[String]()

def addHistory(s: String): Unit ={
  history = history :+ s
}

val lst = list.map{
  x => addHistory("History %s".format(x))
  x * 2
}

print(history)

val l2 = lst.map{ x => addHistory("Adding 1 to %s".format(x)); x + 1}

val l3 = list.view.map{ x => addHistory("Adding 1 to %s".format(x)); x + 1}.map{
                   x => addHistory(" multiply by 2 to %s".format(x)); x * 2}.force

list.view.map(_ + 4).map(_ * 10).force

print(history)