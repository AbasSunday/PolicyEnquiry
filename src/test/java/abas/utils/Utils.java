package abas.utils;

public class Utils
{
    public static void log(String text)
    {
        System.out.println(text);
    }

    public static void logSeparator()
    {
        log("-------------------------------------------------------------");
    }

    public static String getJavaVersion()
    {
        return System.getProperty("java.version");
    }

    public static String getOperatingSystemName()
    {
        return System.getProperty("os.name");
    }
}
