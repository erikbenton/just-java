/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{

    int quantity = 2;
    boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        int price = calculatePrice();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        displayMessage(createOrderSummary(price, hasWhippedCream));
    }

    private int calculatePrice()
    {
        return quantity * 5;
    }

    private String createOrderSummary(int price, boolean addWhippedCream)
    {
        String orderSummary = "Name: Erik Benton\n";
        orderSummary += "Quantity: " + quantity + "\n";
        orderSummary += "Add whipped cream? " + addWhippedCream + "\n";
        orderSummary += "Total: $" + price + "\n";
        orderSummary += "Thank You!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String message)
    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method increments the quantity
     */
    public void increment(View view)
    {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View view)
    {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}