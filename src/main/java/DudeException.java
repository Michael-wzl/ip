/**
 * Represents exceptions specific to the Dude chatbot.
 */
public class DudeException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new DudeException with the given error message.
     *
     * @param message The error message describing the exception.
     */
    public DudeException(String message) {
        super(message);
    }
}
