

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class SessionIdGenerator {

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String UPPER_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_SYMBOLS = UPPER_SYMBOLS.toLowerCase(Locale.ROOT);
    public static final String NUMBER_SYMBOLS = "0123456789";
    public static final String CHARSET = UPPER_SYMBOLS + LOWER_SYMBOLS + NUMBER_SYMBOLS;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public SessionIdGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random, "rondom must not be null");
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public SessionIdGenerator(int length, Random random) {
        this(length, random, CHARSET);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public SessionIdGenerator(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public SessionIdGenerator() {
        this(64);
    }

}
