package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edit1, edit2;
    private Button[] numberButtons = new Button[10];
    private Button buttonPlus, buttonMinus, buttonMulti, buttonDivide;

    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        // 숫자 버튼 배열 초기화
        for (int i = 0; i < numberButtons.length; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numberButtons[i] = findViewById(resID);
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 현재 포커스 되어 있는 EditText 찾기
                    EditText focusedEditText = edit1.isFocused() ? edit1 : edit2.isFocused() ? edit2 : null;

                    // 포커스된 EditText에 숫자 추가
                    if (focusedEditText != null) {
                        String currentText = focusedEditText.getText().toString();
                        Button b = (Button)v;
                        focusedEditText.setText(currentText + b.getText().toString());
                    }
                }
            });
        }

        // 연산 버튼 초기화 및 클릭 리스너 설정
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonMulti = findViewById(R.id.button_multi);
        buttonDivide = findViewById(R.id.button_divide);

        textResult = findViewById(R.id.text_result);

        // 더하기 기능
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('+');
            }
        });

        // 빼기 기능
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('-');
            }
        });

        // 곱하기 기능
        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('*');
            }
        });

        // 나누기 기능
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('/');
            }
        });
    }

    // 연산을 수행하고 결과를 EditText에 표시하는 메소드
    private void performOperation(char operation) {
        String input1 = edit1.getText().toString();
        String input2 = edit2.getText().toString();
        double num1, num2, result = 0.0;
        boolean calculate = true;

        // 입력값 검증
        if (input1.equals("") || input2.equals("")) {
            calculate = false;
        } else {
            num1 = Double.parseDouble(input1);
            num2 = Double.parseDouble(input2);

            // 연산 수행
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        calculate = false;
                    }
                    break;
            }
        }

        // 결과 표시
        if (calculate) {
            edit1.setText("");
            edit2.setText("");
            textResult.setText("계산결과 : " + String.valueOf(result));
        } else {
            edit1.setText("");
            edit2.setText("");
            textResult.setText("계산결과 : Error");

        }
    }
}
