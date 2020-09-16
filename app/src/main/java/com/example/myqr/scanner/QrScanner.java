package com.example.myqr.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myqr.R;

import java.util.List;

public class QrScanner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_activity_main);
        new FirebaseDatabaseHelper().readComponents(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataIsLoaded(List<Component> components, List<String> keys) {
                TableLayout components_table = findViewById(R.id.ComponentListTable);
                int count = components_table.getChildCount();
                for (int i = 1; i < count; i++) {
                    View child = components_table.getChildAt(i);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }
                for (Component component : components) {
                    TableRow tr = new TableRow(getApplicationContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    tr.setLayoutParams(lp);
                    TextView title = new TextView(getApplicationContext());
                    TextView vendor_code = new TextView(getApplicationContext());
                    title.setText(component.getTitle());
                    vendor_code.setText(component.getVendor_code());

                    String str = component.getVendor_code();
                    System.out.println(str);

                    tr.addView(title, 200, 100);
                    tr.addView(vendor_code, 200, 100);
                    components_table.addView(tr);
                }
            }

            @Override
            public void dataIsInserted() {

            }

            @Override
            public void dataIsUpdated() {

            }

            @Override
            public void dataIsDeleted() {

            }
        });

    }

    public void addComponentBtnListener(View view) {
        Intent intent = new Intent(this, AddComponent.class);
        startActivity(intent);
    }
}