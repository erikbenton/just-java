/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{

    int quantity = 2;

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
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocloateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocloateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        String subject = "JustJava order for " + name;
        sendEmail(message, subject);
    }

    public void sendEmail(String message, String subject)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(emailIntent);
        }
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate)
    {
        int price = 5;

        if(hasWhippedCream)
        {
            price += 1;
        }

        if(hasChocolate)
        {
            price += 2;
        }

        return quantity * price;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate)
    {
        String orderSummary = getString(R.string.name_order) + name + "\n";
        orderSummary += getString(R.string.quantity_order) + quantity + "\n";
        orderSummary += getString(R.string.whipped_cream_order) + addWhippedCream + "\n";
        orderSummary += getString(R.string.chocolate_order) + addChocolate + "\n";
        orderSummary += getString(R.string.total_order) + price + "\n";
        orderSummary += getString(R.string.thank_you_order);
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
     * This method increments the quantity
     */
    public void increment(View view)
    {
        int maxCups = 100;
        if(quantity < maxCups)
        {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.cant_order_more) + maxCups + getString(R.string.cups_order);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View view)
    {
        int minCups = 1;
        if(quantity > minCups)
        {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.cant_order_less) + minCups + getString(R.string.cups_order);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}