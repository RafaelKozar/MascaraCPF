package bakeapp.rako.testeedittextdeletedata;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by rako on 04/09/2018.
 */

public class MaskGenerica2 {

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
                String str = MaskGenerica2.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                String mask;
                if (s.length() < 14) {
                    mask = "(##)####-####";
                } else {
                    mask = "(##)#####-####";
                }

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
                boolean inclusao = false;
                boolean withMask = false;

                if (str.length() > old.length()) {
                    inclusao = true;
                }
                if (editTextString.contains("(")) {
                    withMask = true;
                }


                /*if ((cursor == 1   || (cursor == 2 && !withMask) ||
                        cursor == 3 || cursor == 4 || (cursor == 6 && !withMask) ||
                        (cursor == 7 && !withMask)  || cursor == 8 || cursor == 9 ||
                        cursor == 10 || (cursor == 11 && !withMask)) && inclusao) {*/

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
                }/* else if (withMask && !inclusao) {
                    if(cursor == 2) cursor--;
                    if(cursor == 4) cursor -= 2;
                }*/

                if (cursor > tamText) cursor = tamText;
                editText.setText(mascara);
                editText.setSelection(cursor);

                /*Utilities utilities = new Utilities();
                //se for 11 digitos é porque é celular, entao verifico se o primeiro digito é 9
                if (ediTxt.getText().toString().length() == 14 && ediTxt.getText().toString().substring(4, 5).equals("9")) {
                    utilities.campoOk(ediTxt);
                } else if (ediTxt.getText().toString().length() == 13 && !celular) {
                    utilities.campoOk(ediTxt);
                } else if (mascara.length() >= qtdParaCheck) {
                    utilities.campoErro(ediTxt);
                } else {
                    utilities.campoNeutro(ediTxt);
                }*/

            /*}*//* else if (((cursor == 1 && withMask) ||  ((cursor == 3 || cursor == 5) && withMask))
                    && !inclusao) {
                if (cursor == 5) cursor--;
//                    if (cursor == 4) cursor--;
                cursor--;
            } else if (withMask && !inclusao) {
                if(cursor == 2) cursor--;
                if(cursor == 4) cursor -= 2;*/

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }
}