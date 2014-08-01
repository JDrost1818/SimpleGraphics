package dgraphics.utilities;

public class DMath {

    public static int bind(int number, int max) {
        while(number >= max) {
            number -= max;
        }
        return number;
    }
}
