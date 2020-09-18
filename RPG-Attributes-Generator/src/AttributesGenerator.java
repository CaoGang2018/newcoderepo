import java.util.Arrays;

/**
 * @author admin_cg
 * @data 2020/9/18 15:46
 */

public class AttributesGenerator {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();
        System.out.println("综合 " + Arrays.stream(warrior.attr).sum()
                + "\t" + Arrays.stream(warrior1.attr).sum()
                + "\t" + Arrays.stream(warrior1.attr).sum());
        for (int i= 0; i < 6; i++) {
            System.out.println(Warrior.name[i] + " " + warrior.attr[i]
                    + "\t" + warrior1.attr[i]
                    + "\t" + warrior2.attr[i]);
        }
    }
}
