package com.tntcpu.algs4.chap1_basis.example.chap1_basic;

import com.tntcpu.algs4.chap1_basis.utils.StdIn;
import com.tntcpu.algs4.chap1_basis.utils.StdOut;

/**
 * @program: firefly
 * @desc:
 * @author: tntcpu
 * @create: 2019-11-25
 */
public class FixedCapacityStack<T> {
    private T[] a;
    private int N;

    public FixedCapacityStack(int cap) {
        a = (T[]) new Object[cap];
    }
    public boolean isEmpty(){return N==0;}
    public int size() {return N;}
    public void push(T t){
        a[N++] = t;
    }
    public T pop(){
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);
        while (!StdIn.isEmpty()){
            String t = StdIn.readString();
            if (!t.equals("-")&&!t.equals(";")){
                s.push(t);
            }else if (!s.isEmpty()&&!t.equals(";")){
                StdOut.print(s.pop() + " ");
            }else if (t.equals(";")){
                StdOut.println("(" + s.size() + " left on statck)");
            }
        }
    }
}





















