package nehe.chatappapi.Exception;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String email){

        super( String.format("Email: %S already registered", email));

    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
