import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;


public class WriteToFileTest {
    private static final Logger LOG = LoggerFactory.getLogger(WriteToFileTest.class);
    String logfilePath = "./src/test/logFile.log";

    @Test
    public void WriteToFileTest() throws Exception{
        try {
            BufferedWriter bw =
                    new BufferedWriter(new FileWriter("./src/test/logFile.log"));
            bw.write("Changes are made: User doesn't have permissions to access service \n"
                    + System.currentTimeMillis());
            bw.close();
        } catch (Exception e){
            e.getMessage();
        }

        Assert.assertFalse(permissionsToAccessSkybase(logfilePath));
    }

    static boolean permissionsToAccessSkybase(String logfilePath) throws IOException {
        boolean permissionsToAccessSkybase = true;
        BufferedReader br = new BufferedReader(new FileReader(logfilePath));
        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            if (sCurrentLine.contains("doesn't have permissions to access")) {
                permissionsToAccessSkybase = false;
                System.out.println(""+sCurrentLine);
            }
        }
        return permissionsToAccessSkybase;
    }

}
