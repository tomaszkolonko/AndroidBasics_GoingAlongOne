package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
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
        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        CheckBox chocolateCheckBox = findViewById(R.id.checkbox_chocolate);
        EditText editTextView = findViewById(R.id.plain_text_input);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String textInput = editTextView.getText().toString();
        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        Log.v("MainActivity", "Has chocolate: " + hasChocolate);
        Log.v("MainActivity", "Entered Text: " + textInput);

        int price = calculatePrice();
        displayMessage(createOrderSummary(textInput, price, hasWhippedCream));
    }

    /**
     * Create summary of the order
     *
     * @param price
     * @return
     */
    private String createOrderSummary(String textInput, int price, boolean hasWhippedCream) {
        String summary = "Name: " + textInput + "\n";
        summary += "Quantity: " + quantity + "\n";
        summary += "Add Whipped Cream: " + (hasWhippedCream ? "yes\n":"no\n") ;
        summary += "Add Chocolate: " + (addedChocolate ? "yes\n":"no\n") ;
        summary += "Total: $" + price + "\nThank You!";
        return summary;
    }

    /**
     * Rather pointless but hey.... it's a getter
     *
     * @return
     */
    private boolean hasChocolate() {
        return addedChocolate;
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
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
        displayQuantity(++quantity);
    }

    /**
     * This method decrements the value of quantity_text_view
     */
    public void decrement(View view) {
        displayQuantity(--quantity);
    }

    /**
     * DUPLICATION, CAN BE REMOVED AFTERWARDS
     *
     * @param view
     */
    public void onCreamCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            addedWhippedCream = true;
            price = 6;
        } else {
            addedWhippedCream = false;
            price = 5;
        }
        Log.i("MainActivity", "Price set to: " + price +
                " && addedWhippedCream set to: " + addedWhippedCream);
    }

    /**
     * DUPLICATION, CAN BE REMOVED AFTERWARDS
     *
     * @param view
     */
    public void onChocolateCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            addedChocolate = true;
            price = 6;
        } else {
            addedChocolate = false;
            price = 5;
        }
        Log.i("MainActivity", "Price set to: " + price +
                " && addedChocolate set to: " + addedChocolate);
    }

}
