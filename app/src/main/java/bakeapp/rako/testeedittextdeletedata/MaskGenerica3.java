package bakeapp.rako.testeedittextdeletedata;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by rako on 04/09/2018.
 */

public class MaskGenerica3 {

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
                String str = MaskGenerica3.unmask(s.toString());
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

    public static TextWatcher insertData(final String mask, final EditText et) {
        return new TextWatcher() {
            boolean isUpdating;
            String oldTxt = "";
            public void onTextChanged(
                    CharSequence s, int start, int before,int count) {
                String str = MaskGenerica.unmask(s.toString());
                String maskCurrent = "";
                if (isUpdating) {
                    oldTxt = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > oldTxt.length() || (m != '#' && str.length() <= oldTxt.length() && str.length() != i)) {
                        maskCurrent += m;
                        continue;
                    }
                    try {
                        maskCurrent += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;

                int tamText = maskCurrent.length();
                int cursor = getCursorDataNascimento(et.getSelectionEnd(), str, oldTxt, tamText);

                et.setText(maskCurrent);
                et.setSelection(cursor);
            }
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }

    public static TextWatcher cepWatcher(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating = false;
            String old = "";

            private int tamVelho = 0;
            private boolean editando = false;

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editando) {
                    String str =  editable.toString();

                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }

                    String cep = editText.getText().toString().replace("-", "").toString();
                    editando = !editando;
                    if (cep.length() == 5 && 5 > tamVelho) {

                        cep = cep + "-";
                    } else if (cep.length() >= 6) {

                        String parte1 = cep.substring(0, 5);
                        String parte2 = cep.substring(5);
                        cep = parte1 + "-" + parte2;
                    }

                    //#####-###
                    isUpdating = true;

                    boolean inclusao = false;

                    if (str.length() > old.length()) {
                        inclusao = true;
                    }
                    int cursor = editText.getSelectionEnd();
                    if (cursor == 5 || (cursor == 6 && inclusao)) {
                        cursor++;
                    }
                    int tamText = editText.getText().length();
                    tamVelho = tamText;

                    if (cursor > tamText) cursor = tamText;
                    editText.setText(cep);
                    editText.setSelection(cursor);

                    editando = !editando;
                }

            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        };
    }


    public static int getCursor11Digitos(int cursor, String str, String old, int tamText) {
        boolean inclusao = false;

        if (str.length() > old.length()) {
            inclusao = true;
        }

        if (cursor == 0 || cursor == 3 || cursor == 9 || ((cursor == 1 || cursor == 4 || cursor == 10) && inclusao)) {
            cursor++;
        }

        if (cursor > tamText) cursor = tamText;
        return cursor;
    }

    public static int getCursorDataNascimento(int cursor, String str, String old, int tamText) {
        boolean inclusao = false;

        if (str.length() > old.length()) {
            inclusao = true;
        }

        if (cursor == 2 || cursor == 5  || ((cursor == 3 || cursor == 6) && inclusao)) {
            cursor++;
        }

        if (cursor > tamText) cursor = tamText;
        return cursor;
    }
}