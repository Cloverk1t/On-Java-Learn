package ml.cloverkit.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class ReversibleArrayList<E> extends ArrayList<E> {

    ReversibleArrayList(Collection<E> c) {
        super(c);
    }

    // public Iterable<E> reversed() {
    //     return new Iterable<E>() {
    //         @Override
    //         public Iterator<E> iterator() {
    //             return new Iterator<E>() {
    //                 int current = size() - 1;
    //                 @Override
    //                 public boolean hasNext() {
    //                     return current > -1;
    //                 }

    //                 @Override
    //                 public E next() {
    //                     return get(current--);
    //                 }
    //             };
    //         }
    //     };
    // }
}
public class AdapterMethodIdiom {
    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        for (String s : ral) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (String s : ral.reversed()) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
