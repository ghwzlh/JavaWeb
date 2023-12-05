package Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class properties {
    public static void main(String[] args) throws Exception {
        Properties ps = new Properties();

        ps.load(new FileReader("jinjie\\src\\Properties\\user1.properties"));
        System.out.println(ps);
        ps.setProperty("啊啊啊","202205010115");
        ps.store(new FileWriter("jinjie\\src\\Properties\\user1.properties"),"阿加房间");
    }
}
