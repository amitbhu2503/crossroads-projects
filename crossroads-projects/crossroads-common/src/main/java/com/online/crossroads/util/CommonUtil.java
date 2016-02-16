package com.online.crossroads.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.online.crossroads.type.GenderType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.codec.Base64;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by lenovo on 12-02-2016.
 */
public class CommonUtil {

    /**
     * @param iterable
     * @return boolean
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        return (null == iterable || !iterable.iterator().hasNext());
    }

    /**
     * @param iterable
     * @return boolean
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return !isEmpty(iterable);
    }

    /**
     * Checks if the given collection is null or empty.
     *
     * @param collection
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (null == collection || collection.size() == 0) ? true : false;
    }

    /**
     * @param collection
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * @param string
     * @return boolean
     */
    public static boolean isEmpty(String string) {
        return Strings.isNullOrEmpty(string);
    }

    /**
     * @param string
     * @return boolean
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * @param object
     * @return boolean
     */
    public static boolean isNull(Object object) {
        return (null == object) ? true : false;
    }

    /**
     * @param object
     * @return boolean
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * Creates a single-quoted comma-separated string of the elements from the given collection. <br/>
     * <br/>
     * <b>Example:</b> <blockquote>
     * <table>
     * <tr>
     * <td><b>Input collection: </b></td>
     * <td>[abc, def, xyz]</td>
     * </tr>
     * <tr>
     * <td><b>Output String: </b></td>
     * <td>'abc', 'def', 'xyz'</td>
     * <tr>
     * </table>
     * </blockquote>
     *
     * @param collection
     * @return Single-quoted comma-separated {@link String}
     */
    public static String getSingleQuotedCommaSeparatedString(Collection<String> collection) {
        if (isEmpty(collection))
            return null;

        StringBuilder retString = new StringBuilder();
        for (String item : collection) {
            retString.append(quote(item, '\''));
            retString.append(',');
        }
        return retString.deleteCharAt(retString.length() - 1).toString();
    }

    /**
     * Calls the given getter method on every element in the given input collection and returns a list of the objects
     * returned.
     *
     * @param collection           Input Collection
     * @param inputCollectionClass {@link Class} of objects present inside the <code>collection</code>
     * @param getterName           Name of the method of <code>inputCollectionClass</code> which is to be called, to generate the
     *                             output list.
     * @return {@link List} of {@link Object}
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static List<Object> extract(Collection<?> collection, Class<?> inputCollectionClass, String getterName)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        List<Object> values = new ArrayList<Object>();
        Iterator<?> i = collection.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            Method m = inputCollectionClass.getMethod(getterName);
            Object value = m.invoke(o);
            values.add(value);
        }
        return values;
    }

    public static String getRandomUniqueString(Integer size) {
        SecureRandom random = new SecureRandom();
        byte[] newToken = new byte[size];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }

    /**
     * Surrounds the given string with the given quote character.
     *
     * @param string
     * @param quoteChar
     * @return
     */
    public static String quote(String string, char quoteChar) {
        if (!isEmpty(string)) {
            string = quoteChar + string + quoteChar;
        }
        return string;
    }

    /**
     * Removes the surrounding quotes from the given string. The method takes care of both single and double quotes.
     *
     * @param string
     * @return
     */
    public static String unquote(String string) {
        if (!isEmpty(string) && (string.startsWith("'") && string.endsWith("'"))
                || (string.startsWith("\"") && string.endsWith("\""))) {
            string = string.substring(1, string.length() - 1);
        }
        return string;
    }

    public static String parenthesize(String string) {
        if (!isEmpty(string)) {
            string = '(' + string + ')';
        }
        return string;
    }

    /**
     * Joins the given strings into one string delimited by given separator.
     *
     * @param separator
     * @param strings
     * @return
     */
    public static String joinStrings(String separator, String... strings) {
        List<String> stringList = Arrays.asList(strings);
        return StringUtils.join(stringList, separator);
    }

    /**
     * Joins the given strings into one string delimited by "," separator.
     *
     * @param list
     * @return {@link String}
     */
    public static String joinStrings(List<String> list) {
        return StringUtils.join(list, ",");
    }

    /**
     * Joins the given collection of objects (ignoring the null objects) into one string using the string representation
     * of each object and delimited by given separator.
     *
     * @param separator
     * @param
     * @return
     */
    public static String joinStrings(String separator, Collection<?> objects) {
        return Joiner.on(separator).skipNulls().join(objects);
    }

    /**
     * Checks if the given gender is supported or not.
     *
     * @param
     * @return
     */
    public static boolean isSupportedGender(String gender) {
        if (isEmpty(gender)) {
            return false;
        }
        for (GenderType genderType : GenderType.values()) {
            if (genderType.toString().equalsIgnoreCase(gender)) {
                return true;
            }
        }
        return false;
    }

}
