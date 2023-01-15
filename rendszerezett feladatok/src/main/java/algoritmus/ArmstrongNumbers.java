package algoritmus;

import java.util.ArrayList;
import java.util.List;

public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int number){
        if(number<0){
            throw new IllegalArgumentException("Number can't be negative: " + number);
        }
        int original = number;
        int sum = 0;
        List<Integer> digits = new ArrayList<>();
        while(number>0){
        digits.add(number%10);
       number /=10;
        }
        int count = digits.size();
        for(Integer d:digits){
          sum+=Math.pow(d,count);
        }
        return sum==original;
    }

}
