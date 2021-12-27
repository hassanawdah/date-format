import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String fileName = "D:\\applications-code\\date-format\\src\\date.txt";
        List<String> dates = readDateFromFileToList(fileName);
        StringBuilder value = new StringBuilder();
        for (String date : dates) {
            String[] split = date.split("/");
            maintainDateToDDMMYYYY(value, split);
            System.out.println(value);
            value=new StringBuilder();
        }

    }

    private static void maintainDateToDDMMYYYY(StringBuilder value, String[] split) {
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() == 1) {
                split[i] = "0".concat(split[i]);
            }
            if (i == 0)
                value.append(split[i]);
            else
                value.append(split[i].concat("-"));
        }
    }

    public  static List<String> readDateFromFileToList(String fileName) {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
