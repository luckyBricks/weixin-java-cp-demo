import org.mybatis.generator.api.ShellRunner;

/**
 * @author ：Bricks
 * @date ：Created in 2020/11/18 0:13
 * @description：自动生成实体类
 * @modified By：
 */
public class GetAutoEntityAndDao {

        // 该配置文件放在src\\main\\resources\\该路径下即可
        public static void main(String[] args) {
            args = new String[] { "-configfile", "src\\main\\resources\\mybatis-generator.xml", "-overwrite" };
            ShellRunner.main(args);
        }


}
