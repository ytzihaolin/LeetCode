public class DogSearch
{
 public static void main(String[] args)
  {
    //This code will not compile correctly until you fix it.
    //
    //We are trying to count the number of times the word
    //dog appears in some input text, but we can't seem to
    //remember the appropriate method call in the String class,
    //and are not sure our approach will work.
    //task: Use the JDK documentation to help us finish the code,
    //then discuss the ambiguity in the problem description, and
    //reword it to remove the ambiguity in the space provided below.
    //
    //
    //1. The description fails to notice if we're looking for "dog" in lower case or in higher case
    //method indexOf() is case sensitive. So we can rewrite the requirement to find exact word "dog" in input string
    //or to find every dog in input regardless of cases, this can be done by toLowerCase() method.
    //
    //2. It said we were to find word dog, we don't know if this means "Dogman" counts for one. we could rewrite the requirement as 
    //find all the standalone word "dog" in the input.
    //

    String input = new String("The Dogman was no ordinary dog, nor man, but rather a peculiar dog-like man who barked like a dog, and panted like a dog, he even ate like a dog.  He owned a dog named Doglips, and interestingly enough, his favorite food was hotdogs.");
    System.out.println(input);
    input=" "+input+" ";//to add space in case the first word or the last word is standalone "dog";
    int index = 0;
    int count = 0;
    System.out.println("Counting dogs:");

    do{
      //index = input.indexOf("dog",index); comment out if we want to find "dog", not "Dog" or anything else;
      index = input.toLowerCase().indexOf("dog",index);//this is for find every dog including "Dog";
      if(index != -1){
        count++;
        index+=3;
        System.out.print(count+" ");
      }
    }while(index != -1);
    System.out.println("The word \"dog\" in all case appears "+count+" times.");

    index=0;
    count=0;

     do{
      index = input.indexOf("dog",index); //if we want to find only lowercase of "dog"
      if(index != -1){
        count++;
        index+=3;
        System.out.print(count+" ");
      }
    }while(index != -1);
    System.out.println("The word \"dog\" in lowercase appears "+count+" times.");



    index=0;
    count=0;
    do{
      index = input.toLowerCase().replace(","," ").replace("."," ").indexOf(" dog ",index);//find " dog " instead of "dog" to check if dog is a standalone string not a substring
      if(index != -1){
        count++;
        index+=5;
        System.out.print(count+" ");
      }
    }while(index != -1);
    System.out.println("The standalone word \"dog\" appears "+count+" times.");

  } 
}



