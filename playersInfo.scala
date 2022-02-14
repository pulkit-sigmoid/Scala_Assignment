package sigmoid

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


class playerStatistics(val year: Int, val playerName: String, val country: String, val matches: Int, val runs: Int, val wickets: Int)

object playersInfo {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in);
    println("Enter number of player details - ")
    val noOfPlayer = sc.nextInt();
    val listOfPlayer = insert(noOfPlayer, new ListBuffer[playerStatistics]).toList
//    listOfPlayer.foreach((element:playerStatistics)=>println(element.year+" "+element.playerName+" "+element.country+" "+element.matches+" "+element.runs+" "+element.wickets))

    //Question1
    println(s"\nThe player who scored highest run - ${highestRunPlayer(listOfPlayer)}")

    //Question2
    println("\nThe top 5 players who scored highest run -\n")
    printHighestRunPlayers(listOfPlayer)

    //Question3
    println("\nThe top 5 players who taken highest wickets -\n")
    printHighestWicketPlayers(listOfPlayer)

    //Question4
    println("\nThe ranks of the players -\n")
    printPlayersRank(listOfPlayer)
  }

  def highestRunPlayer(listOfPlayer: List[playerStatistics]): String = {

    val sortedList = listOfPlayer.sortBy(player => player.runs)
    sortedList(sortedList.size-1).playerName
  }

  def printHighestRunPlayers(listOfPlayer: List[playerStatistics]): Unit = {

    val sortedList = listOfPlayer.sortBy(player => player.runs)
    @tailrec
    def printNames(top: Int, listOfPlayer: List[playerStatistics]): Unit = {
      if (listOfPlayer.size - top <= 5) {
        println(listOfPlayer(top).playerName + " -> " +listOfPlayer(top).runs)
        printNames(top - 1, listOfPlayer)
      }
    }
    printNames(listOfPlayer.size-1, sortedList)
  }

  def printHighestWicketPlayers(listOfPlayer: List[playerStatistics]): Unit = {

    val sortedList = listOfPlayer.sortBy(player => player.wickets).reverse;
    @tailrec
    def printNames(index: Int, listOfPlayer: List[playerStatistics]): Unit = {
      if(index<5) {
        println(listOfPlayer(index).playerName+" -> "+listOfPlayer(index).wickets)
        printNames(index+1, listOfPlayer)
      }
    }
    printNames(0, sortedList)
  }

  def printPlayersRank(listOfPlayer: List[playerStatistics]): Unit = {

    val sortedList = listOfPlayer.sortBy(player => player.wickets*5).sortBy(player => player.runs*0.05).reverse
    @tailrec
    def printRanks(index: Int, listOfPlayer: List[playerStatistics]): Unit = {

      if(index<listOfPlayer.size){
        println(s"Ranks ${index+1}  - ${listOfPlayer(index).playerName}")
        printRanks(index+1, listOfPlayer)
      }
    }
    printRanks(0, sortedList)
  }

  def insert(noOfPlayer: Int, listOfPlayerDetails: ListBuffer[playerStatistics]): ListBuffer[playerStatistics] = {
    val sc = new java.util.Scanner(System.in);
    if(noOfPlayer<=0) listOfPlayerDetails
    else {
      println("Enter Year, Name, Country, Number of Matches, Total runs, Total Wickets -")

      val year: Int = sc.nextInt()
      val name: String = sc.next()
      val country: String = sc.next()
      val matches: Int = sc.nextInt();
      val runs: Int = sc.nextInt()
      val wicket: Int = sc.nextInt()

      val playerDetails = new playerStatistics(year, name, country, matches, runs, wicket)
      listOfPlayerDetails += playerDetails
      insert(noOfPlayer - 1, listOfPlayerDetails)
    }
  }
}

//2021 Sam India 23 2300 3
//2021 Ram India 23 300 30
//2021 Mano India 23 300 13
//2021 Varun India 35 200 45
//2021 Rishabh India 34 200 56
//2021 Dravid India 20 100 10