package cs309.travlender.ZXX;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs309.travelender.R;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zxx);

        btnSearch= (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_search:
                Intent i = new Intent(this,SearchActivity.class);
                startActivity(i);
                break;
        }
    }
}
