package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    final int BASE_PRICE = 5;
    boolean addedWhippedCream = false;
    boolean addedChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        // Figure out for who the coffee is
        EditText nameField = findViewById(R.id.plain_text_input);
        String nameFiledAsString = nameField.getText().toString();

        int price = calculatePrice();
//        displayMessage(createOrderSummary(nameFiledAsString, price));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "tz.kolonko@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.java_order_email_subject)
                + nameFiledAsString);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(nameFiledAsString, price));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Create summary of the order
     *
     * @param price
     * @return
     */
    private String createOrderSummary(String textInput, int price) {
        String summary = getString(R.string.java_name) + textInput + getString(R.string.java_new_line);
        summary += getString(R.string.java_quantity) + quantity + getString(R.string.java_new_line);
        summary += getString(R.string.java_add_whipped_cream) + (addedWhippedCream ? "yes\n":"no\n") ;
        summary += getString(R.string.java_add_chocolate) + (addedChocolate ? "yes\n":"no\n") ;
        summary += getString(R.string.java_total) + price + getString(R.string.java_thank_you);
        return summary;
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = BASE_PRICE;

        // Add $1 to the price if Whipped Cream is selected
        if(addedWhippedCream) {
            price += 1;
        }

        // Add $2 to the price if Chocolate is selected
        if(addedChocolate) {
            price += 2;
        }

        // If the user selects a negative number of cups default to 0
        if(quantity < 0) {
            return 0;
        }

        return price * quantity;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method decrements the value of quantity_text_view
     */
    public void increment(View view) {
        if(quantity < 20) {
            displayQuantity(++quantity);
        } else {
            Toast.makeText(this, "You cannot order more than 20 cups of coffee",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method decrements the value of quantity_text_view
     */
    public void decrement(View view) {
        if(quantity > 1) {
            displayQuantity(--quantity);
        } else {
            Toast.makeText(this, "Yout cannot order less than 1 cup of coffee",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * DUPLICATION, CAN BE REMOVED AFTERWARDS
     *
     * @param view
     */
    public void onCreamCheckboxClicked(View view) {
        addedWhippedCream = ((CheckBox) view).isChecked();
    }

    /**
     * DUPLICATION, CAN BE REMOVED AFTERWARDS
     *
     * @param view
     */
    public void onChocolateCheckboxClicked(View view) {
        addedChocolate = ((CheckBox) view).isChecked();

    }

}
