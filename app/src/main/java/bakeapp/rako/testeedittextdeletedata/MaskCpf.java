package bakeapp.rako.testeedittextdeletedata;

/**
 * Created by rako on 31/07/2018.
 */

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class MaskCpf {

    private static final String maskCPF = "###.###.###-##";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }



    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = MaskCpf.unmask(s.toString());
                String mask = maskCPF;
                String defaultMask = maskCPF;

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() <= old.length() && str.length() != i)) {
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
                isUpdating = true;
                int cursor = editText.getSelectionEnd();
                int tamText = mascara.length();
                boolean inclusao = false;

                if (str.length() > old.length()) {
                    inclusao = true;
                }
                if(cursor == 3 ||  cursor == 7 || cursor == 11 || ((cursor == 4 || cursor == 8 || cursor == 12) && inclusao))
                    cursor++;
                if(cursor >  tamText) cursor = tamText;
                editText.setText(mascara);
                editText.setSelection(cursor);
//                editText.setSelection(mascara.length());
                if(editText.getText().toString().length() >= 14){}else{}
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}

