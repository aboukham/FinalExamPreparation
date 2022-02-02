import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyQueueImplementaion<E> {
    private E[] data;
    private int capacity;
    private int size;
    private int front;
    private int rear;

    public MyQueueImplementaion(int capacity){
        this.capacity = capacity;
        data = (E[]) new Object[capacity];
        size = 0;
        rear = -1;
        front = 0;
    }

    public int size(){
        return size;
    }

    public boolean offer(E item){//add
        if (item == null)
            return false;
        if (size() == capacity)
            reallocate();
        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
        return true;
    }

    public boolean empty(){
        return (size() == 0);
    }

    public E poll(){//remove
        if (empty())
            return null;
        E item = data[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public E peek(){//element
        if (empty())
            return null;
        return data[front];
    }

    public void reallocate(){
        capacity = capacity * 2;
        E[] temp = data;
        data = (E[])new Object[capacity];
        int j = front;
        for (int i = 0; i < size(); i++){
            data[i] = temp[j];
            j = (j + 1) % capacity;
        }
    }

    public String[] flashArray(){
        //remove all queue elements then put them in the array;
        String[] arr = new String[size()];
        int len = size();
        for (int i = 0; i < len; i++){
            arr[i] = (String) poll();
        }
        return arr;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        int len = size() - 1;
        for (int i = 0; i < len; i++){
            sb.append(poll() + ", ");
        }
        sb.append(poll() + "]");
        return sb.toString();
    }

    public static boolean hasDuplicate(int[] arr){
        return hasDuplicate(arr, new HashSet<Integer>(), 0);
    }

    public static boolean hasDuplicate(int[] arr, Set<Integer> set, int index){
        if (set.size() == arr.length)
            return false;
        set.add(arr[index]);
        if (set.size() < arr.length && index == arr.length - 1)
            return true;
        return hasDuplicate(arr, set, index + 1);
    }

    public static boolean hasDuplicate(int[] arr, int index){
        if (index >= arr.length - 1)
            return false;
        if (index < arr.length - 1 && arr[index] == arr[index + 1])
            return true;
        return hasDuplicate(arr, index + 1);
    }


    public static void main(String[] args){
        MyQueueImplementaion<String> q = new MyQueueImplementaion<>(10);
        q.offer("abdelaziz");
        q.offer("rahel");
        q.offer("robeil");
        q.offer("taye");

        int[] arr = {12, 34, 35, 40, 42};
        System.out.println(hasDuplicate(arr, 0));



        //System.out.println(Arrays.toString(q.flashArray()));

    }
}
