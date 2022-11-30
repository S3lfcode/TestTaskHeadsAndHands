package somegame;

/**
 * Просто класс исключения, наследовано от Exception. Вызывается, когда при создании существа указаны неверные параметры.
 */
public class CreationException extends Exception{
    public static final String tRed = "\u001B[31m";
    public CreationException(String message) {
        super(message);
    }

    public String getNameException() {
        return tRed + "Ошибка создания существа!";
    }
}
