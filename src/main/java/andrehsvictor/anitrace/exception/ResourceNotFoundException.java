package andrehsvictor.anitrace.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4709451883862033299L;

    public ResourceNotFoundException(Class<?> clazz, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", clazz.getSimpleName(), fieldName, fieldValue));
    }

}
