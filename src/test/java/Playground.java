import java.time.LocalDate;

public class Playground
{
    public static void main(String[] args)
    {
        String date = "10/10/1958";

        String[] splitDate = date.split("/");

        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));

        System.out.println("day: " + dateOfBirth.getDayOfMonth());
        System.out.println("month: " + dateOfBirth.getMonth());
        System.out.println("year: " + dateOfBirth.getYear());

    }
}
