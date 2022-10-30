package dxc.bookstore.bookstoreapp.util;

import dxc.bookstore.bookstoreapp.exception.BookStoreValidationException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationUtil {

    public static boolean idDateStrValid(String dateStr) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            df.parse(dateStr);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public static boolean isDateBeforeToday(String dateStr) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = df.parse(dateStr);
            Date today = new Date();
            if (today.compareTo(date) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException pe) {
            throw new BookStoreValidationException("Date could not be parsed");
        }
    }
}
