package passwordgen;

import java.util.*;

public class Letter {

    private static final String lowercase = "abcdefghijklmnopqrstuvwxyz";

    private static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String specialChar = "!@#$%^&*()-_=+[]{}\\\\|;:'\\\",.<>?/";

    private static final String nums = "0123456789";

    private static final String failSafe = "";

    public String generate(int[] options, int length) {
        Random rand = new Random();
        StringBuilder result = new StringBuilder();
        List<Character> chars = new ArrayList<Character>();

        for(int option: options){
            switch(option){
                case 1:
                    chars.add(lowercase.charAt(rand.nextInt(lowercase.length())));
                    break;
                case 2:
                    chars.add(uppercase.charAt(rand.nextInt(uppercase.length())));
                    break;
                case 3:
                    chars.add(specialChar.charAt(rand.nextInt(specialChar.length())));
                    break;
                case 4:
                    chars.add(nums.charAt(rand.nextInt(nums.length())));
                    break;
                default:
                    chars.add(failSafe.charAt(0));
            }
        }

        while(chars.size() < length){
            chars.add(lowercase.charAt(rand.nextInt(lowercase.length())));
        }

        Collections.shuffle(chars);

        for(char c: chars){
            result.append(c);
        }
        return result.toString();
    }
}
