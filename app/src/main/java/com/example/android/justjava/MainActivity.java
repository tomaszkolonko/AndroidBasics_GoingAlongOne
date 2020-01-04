package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Create summary of the order
     *
     * @param price
     * @return
     */
    private String createOrderSummary(int price) {
        String summary = "Name: Tomasz Kolonko\n";
        summary += "Quantity: " + quantity + "\n";
        summary += "Total: $" + price + "\nThank You!";
        return summary;
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
     *
     *
     * @param view
     */
    public void onCheckboxClicked(View view) {
        // TODO implement this
    }

}
