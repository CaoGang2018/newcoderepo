import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * @author admin_cg
 * @date 2020/9/9 14:25
 */
public class first {
    public static void main(String[] args) throws IOException {
        //long startTime = System.currentTimeMillis();
        URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //long finishUrlTime = System.currentTimeMillis();
        //System.out.println("Download Time: " + (finishUrlTime - startTime) + " ms");

        Map<String, Collection<String>> ans = new HashMap<>();
        String word;
        int count = 0;
        while((word = reader.readLine()) != null){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if(!ans.containsKey(key)){
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(word);
            count = Math.max(count, ans.get(key).size());
        }
        //long finishTime = System.currentTimeMillis();
        //System.out.println("Find Time: " + (finishTime - finishUrlTime) + " ms");
        //System.out.println("Total Time: " + (finishTime - startTime) + " ms");
        reader.close();
        for(Collection<String> an : ans.values()){
            if(an.size() >= count)
                System.out.println(an);
        }
    }

}
