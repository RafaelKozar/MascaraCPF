package bakeapp.rako.testeedittextdeletedata;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by rako on 04/09/2018.
 */

public class MaskGenerica4 {

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

    public static TextWatcher insert(final String mask, final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = MaskGenerica4.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;

                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length() || (m != '#' && str.length() <= old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                //"(##)#####-####"
                //0  4  9

                isUpdating = true;

                int tamText = mascara.length();

                int cursor = getCursor11Digitos(editText.getSelectionEnd(), str, old, tamText);

                editText.setText(mascara);
                editText.setSelection(cursor);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }


    public static int getCursor11Digitos(int cursor, String str, String old, int tamText) {
        boolean inclusao = false;

        if (str.length() > old.length()) {
            inclusao = true;
        }

        //(##)####-####
        if (cursor == 0 || cursor == 3 || cursor == 8 || ((cursor == 1 || cursor == 4 || cursor == 9) && inclusao)) {
            cursor++;
        }

        if (cursor > tamText) cursor = tamText;
        return cursor;
    }
}