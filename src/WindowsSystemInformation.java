import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsSystemInformation
{
    static String get() throws IOException
    {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("systeminfo");
        BufferedReader systemInformationReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = systemInformationReader.readLine()) != null)
        {
        	if(line.toUpperCase().contains( "SPEICHER") || line.toUpperCase().contains(" MEMORY")) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
        	}
        }

        return stringBuilder.toString().trim();
    }
    
    public static void main(String args[]) {
    	try {
			System.out.println(get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}