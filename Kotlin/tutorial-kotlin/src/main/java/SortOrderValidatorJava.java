import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public interface SortOrderValidatorJava {
    Set<String> directions = Arrays.stream(SortDirectionJava.values()).map(Enum::name).collect(Collectors.toSet());

    /**
     * Check if sorting order is valid.
     * @return True if valid, otherwise false.
     */
    boolean isValid();
}
