package year2022;

public class Constants
{
    public static final String BASE_DIR = getProperty() + "/java/year2022/input/";

    private static String getProperty()
    {
        String prop = System.getProperty("user.dir");
        int index = prop.indexOf("\\test");
        if (index > -1)
        {
            prop = prop.replaceFirst("test", "main");
        }
        else if (!prop.contains("\\src\\main"))
        {
            prop += "\\src\\main";
        }
        return prop;
    }
}
