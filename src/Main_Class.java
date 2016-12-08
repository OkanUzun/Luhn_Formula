
import static java.lang.Character.getNumericValue;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ouzun
 */
public class Main_Class {
    
    static ArrayList<String> card_number_list = new ArrayList<>();
    static StringBuilder sb;
    static int sum = 0;
    static int last_digit;
    static int[] sb_int = new int[15];
    
    public static void main(String[] args) {
        while (card_number_list.size() < 100){
            generate_card_number();
        }
                      
        card_number_list.forEach(System.out::println);
        
    }
    public static void generate_card_number(){
        long card_number = getRandomNumberInRange(1000000000000000L,9999999999999999L);
        
        if (!card_number_list.contains(Long.toString(card_number))){
            long temp = card_number;
            last_digit = (int) (temp % 10);

            //Delete last digit
            temp = temp / 10;

            //Reverse     
            sb = new StringBuilder(Long.toString(temp)).reverse(); 


            for (int i = 0; i < sb.length(); i++) {
                sb_int[i] = getNumericValue(sb.charAt(i));
            }

            //Multiple odd digits by 2
            for (int i = 0; i < sb_int.length; i++) {
                int c = sb_int[i];
                if (i % 2 == 0){
                    sb_int[i] = c*2;
                }
            }

            //Subtract 9 to numbers over 9

            for (int i = 0; i < sb_int.length; i++) {
                int a = sb_int[i];

                while ( a > 9){
                    a -= 9;
                }
                sb_int[i] = a;
            }

            //Add all numbers
            sum = 0;
            for (int i = 0; i < sb_int.length; i++) {
                sum = sum + sb_int[i];
            }

            if ((10 - (sum % 10)) == last_digit){
                 card_number_list.add(Long.toString(card_number));
            }  
        }      
    }
    
    private static long getRandomNumberInRange(long min, long max) {

	if (min >= max) {
		throw new IllegalArgumentException("max must be greater than min");
	}

	Random r = new Random();
        
	return min+((long)(r.nextDouble()*(max-min)));
    }
}
