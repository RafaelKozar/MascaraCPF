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

//    static Utilities utilities =  new Utilities();

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
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
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
                int k = editText.getSelectionStart();
                editText.setText(mascara);
                if(editText.getText().toString().contains("."))
                editText.setSelection(k);

                if(editText.getText().toString().length() >= 14){
//                    Drawable checked = editText.getResources().getDrawable(R.drawable.ic_check_verde);
//                    editText.setCompoundDrawablesWithIntrinsicBounds(null,null,checked,null);

//                    utilities.habilitaBotao(button);

                }else{
//                    Drawable unChecked = editText.getResources().getDrawable(R.drawable.ic_check_branco);
                    editText.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);

//                    utilities.desabilitaBotao(button);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}
