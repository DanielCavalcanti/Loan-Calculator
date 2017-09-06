package com.cavalcanti.daniel.loan_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText loanAmount_Etxt;
    private EditText termLoanYears_Etxt;
    private EditText yearRate_Etxt;

    private TextView res_monthlyPayment;
    private TextView res_totalPayment;
    private TextView res_totalInterest;

    private String loanAmount_str;
    private String termLoanYears_str;
    private String yearRate_str;

    private double var_loan_Amount;
    private double var_term_loan_years;
    private double var_year_rate;
    private double var_monthlyPayment;
    private double var_totalPayment;
    private double var_totalInterest;
    private double var_monthlyRate;

    private Toast warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        warningText = Toast.makeText(getApplicationContext(), R.string.warning,Toast.LENGTH_LONG);
        loanAmount_Etxt = (EditText)findViewById(R.id.loan_Input);
        termLoanYears_Etxt = (EditText)findViewById(R.id.term_Input);
        yearRate_Etxt = (EditText)findViewById(R.id.rate_Input);

        res_monthlyPayment = (TextView) findViewById(R.id.mPayment_result);
        res_totalInterest = (TextView) findViewById(R.id.total_Interest_Result);
        res_totalPayment = (TextView) findViewById(R.id.total_Payment_result);

    }
    public void loanResults(View view) {
        loanAmount_str = loanAmount_Etxt.getText().toString();
        yearRate_str = yearRate_Etxt.getText().toString();
        termLoanYears_str = termLoanYears_Etxt.getText().toString();

        if(TextUtils.isEmpty(loanAmount_str) || TextUtils.isEmpty(yearRate_str) || TextUtils.isEmpty(termLoanYears_str)){
            warningText.show();
        }else{
            var_loan_Amount = Double.parseDouble(loanAmount_str);
            var_year_rate = Double.parseDouble(yearRate_str);
            var_term_loan_years = Integer.parseInt(termLoanYears_str);

            var_monthlyRate = (var_year_rate/100)/12;

            var_monthlyPayment =
                    (var_loan_Amount*var_monthlyRate)/(1-Math.pow(1+var_monthlyRate, -(var_term_loan_years*12)));

            var_totalPayment = var_monthlyPayment * (var_term_loan_years*12);
            var_totalInterest = ((var_term_loan_years*12) * var_monthlyPayment)-var_loan_Amount;

            res_monthlyPayment.setText(new DecimalFormat("##.##").format(var_monthlyPayment));
            res_totalPayment.setText(new DecimalFormat("##.##").format(var_totalPayment));
            res_totalInterest.setText(new DecimalFormat("##.##").format(var_totalInterest));

        }
    }
    public void clearValues(View view){
        loanAmount_Etxt.setText("");
        yearRate_Etxt.setText("");
        termLoanYears_Etxt.setText("");
        res_monthlyPayment.setText("");
        res_totalInterest.setText("");
        res_totalPayment.setText("");
    }

}
