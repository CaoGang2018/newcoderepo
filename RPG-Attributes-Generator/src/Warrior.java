import java.util.Arrays;
import java.util.Random;

/**
 * @author admin_cg
 * @data 2020/9/18 15:53
 */
public class Warrior {
    public int[] attr;
    public static String[] name = {"力量","敏捷","体格","智力","学识","魅力"};

    // 在构造函数中进行判断生成
    public Warrior() {
        boolean flag = false;
        while (!flag){
            this.attr = new int[6];
            for (int i = 0; i < 6; i++) {
                attr[i] = generator();
            }
            if(checkAttr(attr)){
                flag = true;
            }
        }

    }

    // 生成规定随机数
    private int generator(){
        Random random = new Random();
        int[] temp = new int[4];
        for (int i = 0; i < 4; i++) {
            temp[i] = random.nextInt(6) + 1;
        }
        Arrays.sort(temp);
        return temp[1] + temp[2] + temp[3];
    }

    // 检查是否满足要求
    private boolean checkAttr(int[] attrs){
        int overFifteen = 0;
        for (int attr : attrs) {
            if(attr >= 15)
                overFifteen++;
        }
        return (overFifteen >= 2 && Arrays.stream(attrs).sum() >= 75);
    }
}
