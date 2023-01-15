package algoritmus;

import java.util.Comparator;
import java.util.List;

public class Algorithms_zaropot {

    public int countMaximums(List<Integer> numbers){
        if(numbers.isEmpty()){
            throw new IllegalArgumentException();
        }
       int max = numbers.stream().
               sorted(Comparator.reverseOrder())
               .findFirst().get();

        List<Integer> maxNumbers= numbers.stream()
                .filter(n->n==max).toList();
        return maxNumbers.size();

    }
}
