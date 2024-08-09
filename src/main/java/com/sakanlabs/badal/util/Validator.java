package com.sakanlabs.badal.util;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import java.net.URL;

/**
 *
 * Provides utility methods related to data validation and format checking.
 *
 * @author Ahmad Fajar
 * @since June 2020
 */
public class Validator {

    /* Returns true if url is valid */
    public static boolean isURLValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if the object is null, using the
     * rules from { {@link #isNull(String)} if the object
     * is one of these types.
     *
     * @param  object the object to check
     * @return true if the object is null;
     *         false otherwise
     */
    public static boolean isNull(Object object) {
        if (object instanceof Long) {
            return isNull((Long)object);
        }
        else if (object instanceof String) {
            return isNull((String)object);
        }
        else if (object == null) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if the object is not null, using
     * the rules from {@link #isNotNull(Long)} or {@link #isNotNull(String)} if
     * the object is one of these types.
     *
     * @param  object the object to check
     * @return true if the object is not null;
     *         false otherwise
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * Returns true if the string is null, meaning it
     * is a null reference, an empty string, whitespace, or the
     * string "null", with or without leading or trailing
     * whitespace.
     *
     * @param  s the string to check
     * @return true if the string is null;
     *         false otherwise
     */
    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }

        int counter = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == CharPool.SPACE) {
                continue;
            }
            else if (counter > 3) {
                return false;
            }

            if (counter == 0) {
                if (c != CharPool.LOWER_CASE_N) {
                    return false;
                }
            }
            else if (counter == 1) {
                if (c != CharPool.LOWER_CASE_U) {
                    return false;
                }
            }
            else if ((counter == 2) || (counter == 3)) {
                if (c != CharPool.LOWER_CASE_L) {
                    return false;
                }
            }

            counter++;
        }

        if ((counter == 0) || (counter == 4)) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if the string is not null, meaning
     * it is not a null reference, an empty string, whitespace, or
     * the string "null", with or without leading or trailing
     * whitespace.
     *
     * @param  s the string to check
     * @return <code>true</code> if the string is not <code>null</code>;
     *         <code>false</code> otherwise
     */
    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Returns {@code true} if the character is a special character in an
     * email address.
     *
     * @param  text the character to check
     * @return {@code true} if the character is a special character in an
     *         email address; {@code false} otherwise
     */
    public static boolean isTextSpecialChar(String text) {
        for (int i = 0 ; i < text.length();i++){
            char c = text.charAt(i);
            for (char specialChar : _SPECIAL_CHAR) {
                if (c == specialChar) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if the long number object is not
     * {@code null}, meaning it is neither a <code>null</code> reference or
     * zero.
     *
     * @param  l the long number object to check
     * @return {@code true} if the long number object is not
     *         {@code null}; {@code false} otherwise
     */
    public static boolean isNotNull(Long l) {
        return !isNull(l);
    }

    /**
     * Returns String without emoji if String contains emojis
     * 
     *
     * @param letters the original String include emojis
     * @return String
     */
    public static String isEmojiThenRemove(String letters){
        if (isNotNull(letters))
            return letters.replaceAll(emojiCharacterFilter, "");
        else
            return letters;
    }

    private static final char[] _SPECIAL_CHAR = {
            '.', '!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^',
            '_', '`', '{', '|', '}', '~'
    };

    private static final String emojiCharacterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
}
