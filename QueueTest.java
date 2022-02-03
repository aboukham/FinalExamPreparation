package FinalExamPreparation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueTest {
    public static String reverseWord(String word){//hello//5
        if (word.length() == 0){
            return "";
        }
        return  reverseWord(word.substring(1)) + word.charAt(0);
    }

    public static int function(int n){//4
        if (n == 0)
            return 2;
        return 4 + function(n - 1);
    }

    public static void main(String[] args){
        Queue<String> q = new LinkedList<>();
        q.offer("abdelaziz");//abdelaziz
        q.offer("rahel");//abdelaziz rahel
        q.offer("robeil");
        q.offer("taye");//abdelaziz rahel robeil taye

        q.offer(q.peek());//abdelaziz rahel robeil taye abdelaziz
        q.offer(q.poll());//rahel robeil taye abdelaziz abdelaziz
        q.poll();
        q.offer(q.peek());//robeil taye abdelaziz abdelaziz robeil
        System.out.println(function(4));
    }
}
