package cs309.travlender.ZSQ;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import cs309.travelender.R;
import cs309.travlender.ZXX.EventManager;
import cs309.travlender.ZXX.MainActivity;


/**
 * Created by Administrator on 2017/10/16.
 */

public class EventActivity extends Activity implements View.OnClickListener{

    private EditText etTitle,etStart,etEnd;
    private Button btnChange,btnAdd;
    private int id;
    private EventManager handler;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        //UI 部分
        etTitle= (EditText) findViewById(R.id.event_name);
        etStart= (EditText) findViewById(R.id.event_start);
        etEnd= (EditText) findViewById(R.id.event_end);
        btnChange= (Button) findViewById(R.id.btn_change);
        btnAdd= (Button) findViewById(R.id.btn_add_event);


        handler=new EventManager(this);
        //获取传递过来的intent
        intent=getIntent();

        //通过request判断，是通过那个Button点击进入的，之后隐藏或者显示相应的Button
        String request=intent.getStringExtra("request");
        switch (request){
            //点击添加按钮进入的，则只显示btnAdd
            case "Add":
                btnChange.setVisibility(View.GONE);
                Random random = new Random();
                etTitle.setText("test"+random.nextInt(1000));
                etStart.setText(random.nextInt(1000)+"");
                etEnd.setText(random.nextInt(1000)+"");
                btnAdd.setVisibility(View.VISIBLE);
                break;
            //通过ListView Item进入的
            case "Look":
                id=intent.getExtras().getInt("id");
                etTitle.setText(intent.getStringExtra("title"));
                etStart.setText(intent.getStringExtra("starttime"));
                etEnd.setText(intent.getStringExtra("endtime"));
                break;
        }
        btnAdd.setOnClickListener(this);
        btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_event:
                String addtime =String.valueOf(System.currentTimeMillis());
                ContentValues value = new ContentValues();
                value.put("addtime",addtime);
                value.put("title",etTitle.getText().toString());
                value.put("starttime",etStart.getText().toString());
                value.put("endtime",etEnd.getText().toString());
                Event newEvent=new Event(value);
                handler.addEvent(newEvent);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btn_change:
                addtime =String.valueOf(System.currentTimeMillis());
                Event event=new Event(etTitle.getText().toString(),addtime,etStart.getText().toString(),
                        etEnd.getText().toString());
                intent=getIntent();
                Event.setId(intent.getIntExtra("id",0));
                handler.editEvent(Event);
                //这里设置resultCode是为了区分是修改后返回主界面的还是删除后返回主界面的。
                setResult(2,intent);
                finish();
                break;
        }

    }
}
