package bohac.matej.testovaciaapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HlavneOkno extends ActionBarActivity {

    private TextView _textViewTitle;
    private TextView _textViewSubtitle;

    private Button _buttonClickMe;
    private Button _buttonDoNotClickMe;
    private Button _buttonToastIt;
    private Button _buttonChangeName;

    private EditText _editTextToastIt;
    private EditText _editTextName;

    private ListView _listViewColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlavne_okno);

        _textViewTitle = (TextView)findViewById(R.id.textView_Title);
        _textViewSubtitle = (TextView) findViewById(R.id.textView_SubTitle);

        _buttonClickMe = (Button)findViewById(R.id.button_ClickMe);
        _buttonDoNotClickMe = (Button)findViewById(R.id.button_DoNotClickMe);
        _buttonChangeName = (Button)findViewById(R.id.button_changeName);

        _editTextToastIt = (EditText)findViewById(R.id.editText_typeText1);
        _editTextToastIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _editTextToastIt.setText("");
            }
        });
        _editTextToastIt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    _editTextToastIt.setText("");
                }
                else{
                    _editTextToastIt.setText(R.string.title);
                }
            }
        });

        _editTextName = (EditText)findViewById(R.id.editText_typeName);
        _editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    _editTextName.setText("Mess");
                }
                else{
                    _editTextName.setText(R.string.typeName);
                }
            }
        });
        _editTextName.setImeActionLabel("Name", KeyEvent.KEYCODE_ENTER);
        _editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    onClick_ChangeName(v);
                    _editTextName.clearFocus();
                    return true;
                }
                return false;
            }
        });

        generateListView();
    }

    private void hideKeyboard(EditText editText)
    {
        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void generateListView(){
        ArrayList<String> arrayOfColors = new ArrayList<String>();
        arrayOfColors.add("Red");
        arrayOfColors.add("Blue");
        arrayOfColors.add("Green");
        arrayOfColors.add("Black");
        arrayOfColors.add("White");
        
        _listViewColors = (ListView)findViewById(R.id.listView_colors);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayOfColors);
        _listViewColors.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hlavne_okno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick_ClickMe(View v){
        _textViewSubtitle.setText("Now i am a new subtitle.");
    }

    public void onClick_DoNotClickMe(View v){
        _textViewSubtitle.setText(R.string.subtitle);
        Button btn = (Button) v;
        btn.setText("Nah I am cool");
    }

    public void onClick_ToastIt(View v){
        _buttonToastIt = (Button)v;
        String toastText = _editTextToastIt.getText().toString();
        Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClick_ChangeName(View v){
        _textViewTitle.setText(getText(R.string.title) + " " + _editTextName.getText().toString());
        hideKeyboard(_editTextName);
    }
}
