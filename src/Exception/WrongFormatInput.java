package Exception;

import java.io.IOException;

public class WrongFormatInput extends IOException {
    public WrongFormatInput(String message) {
        super(message);
    }
}
