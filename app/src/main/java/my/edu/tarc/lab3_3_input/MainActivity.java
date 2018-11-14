package my.edu.tarc.lab3_3_input;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;
    double premium = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = findViewById(R.id.spinnerAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = findViewById(R.id.checkBoxSmoker);
        textViewPremium = findViewById(R.id.textViewPremium);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.age_group,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view,
                               int position,
                               long id) {
        Toast.makeText(this, "Position=" + position,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        int postiton;

        postiton = spinnerAge.getSelectedItemPosition();
        switch (postiton){
            case 0:
                //TODO  calculate the basic premium
                premium = 50;
                break;
            case 1:
                premium = 55;
                break;
            case 2:
                premium = 60;
                break;
            case 3:
                premium = 70;
                break;
            case 4:
                premium = 120;
                break;
            case 5:
                premium = 160;
                break;
            case 6:
                premium = 200;
                break;
            default:
                premium = 250;
                break;

        }

        int gender;
        gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale) {
            //TODO calculate extra premium for male
            switch (postiton){
                case 2:
                case 5:
                    premium += 50;
                    break;
                case 3:
                case 4:
                    premium += 100;
                    break;
                default:
                    premium += 0;

            }

        }

        if(checkBoxSmoker.isChecked()){
            //TODO calculate extra premium for smoker
            switch (postiton){
                case 3:
                    premium += 100;
                    break;
                case 4:
                case 5:
                    premium += 150;
                    break;
                case 6:
                case 7:
                    premium += 250;
                    break;
                default:
                    premium += 0;
                    break;

            }

            Currency currency = Currency.getInstance(Locale.getDefault());
            String symbol =currency.getSymbol();
        }

        textViewPremium.setText(getString(R.string.premium)+
                " " + symbol + premium);
    }

    public void resetButton (View view){
        textViewPremium.setText(null);
        premium = 0;
        spinnerAge.setSelection(0);
        radioButtonMale.setChecked(false);
        radioButtonFemale.setChecked(false);
        checkBoxSmoker.setChecked(false);
    }
}
