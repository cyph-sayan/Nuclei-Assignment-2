package enums;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Course {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;
    public static List<String> names = Stream.of(Course.values()).map(Enum::name).collect(Collectors.toList());
}

