package espol.example.personal.comunitarias.Denuncias;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by PC-JANINA on 10/2/2017.
 */

public abstract class TextValidator implements TextWatcher {
    private final EditText editText;

    public TextValidator(EditText editText) {
        this.editText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = editText.getText().toString();
        validate(editText, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { }
}
