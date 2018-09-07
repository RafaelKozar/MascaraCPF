package bakeapp.rako.testeedittextdeletedata;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by rako on 04/09/2018.
 */

public class MaskGenerica {

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
                String str = MaskGenerica.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;

                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) { //|| (m != '#' && str.length() <= old.length() && str.length() != i)) {
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
                int cursor = editText.getSelectionEnd();
                int tamText = mascara.length();
                String editTextString = editText.getText().toString();
                cursor = getCursor11Digitos(cursor, editTextString, str, old);

                if (cursor > tamText) cursor = tamText;
                editText.setText(mascara);
                editText.setSelection(cursor);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }


    public static int getCursor11Digitos(int cursor, String editTextString, String str, String old) {
        boolean inclusao = false;
        boolean withMask = false;

        if (str.length() > old.length()) {
            inclusao = true;
        }
        if (editTextString.contains("(")) {
            withMask = true;
        }

        if ((cursor == 1 || cursor == 3 || cursor == 4 || cursor == 8 || cursor == 9 || cursor == 10 ||
                ((cursor == 2 || cursor == 5 || cursor == 6 || cursor == 7 || cursor == 11) && !withMask)) && inclusao) {
            if (!withMask && cursor != 1) {
                if (cursor == 5 || cursor == 7 || cursor == 8 || cursor == 9 || cursor == 10 || cursor == 11)
                    cursor++;
                cursor++;
            }
            cursor++;

        }else if ((((cursor == 1 || cursor == 2 || cursor == 3 || cursor == 5 || cursor == 4 ||
                cursor == 8 || cursor == 9 || cursor == 10 || cursor == 11 || cursor == 12) && withMask))
                && !inclusao) {
            if(cursor == 12 || cursor == 11 || cursor == 10) cursor -= 3;
            else if (cursor == 9 || cursor == 8 || cursor == 5 || cursor == 4) cursor -= 2;
            else cursor--;
        }

        return cursor;
    }
}