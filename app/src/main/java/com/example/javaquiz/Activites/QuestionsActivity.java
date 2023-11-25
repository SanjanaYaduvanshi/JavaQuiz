package com.example.javaquiz.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.javaquiz.Models.QuestionModel;
import com.example.javaquiz.R;
import com.example.javaquiz.databinding.ActivityQuestionsBinding;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<QuestionModel> list= new ArrayList<>();
    private  int count = 0;
    private  int position = 0;
    private  int score = 0;
CountDownTimer timer;

    ActivityQuestionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getSupportActionBar().hide();

        resetTimer();

        timer.start();

        String setName =getIntent().getStringExtra("set");

        if (setName.equals("SET-1")){
            setOne();
            
        } else if (setName.equals("SET-2")) {
            setTwo();
            
        }else if (setName.equals("SET-3")) {
            setThree();

        }else if (setName.equals("SET-4")) {
            setFour();

        }else if (setName.equals("SET-5")) {
            setFive();

        }else if (setName.equals("SET-6")) {
            setSix();

        }
// option containers

        for (int i=0;i<4;i++) {

            binding.optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button)view);
                }
            });


        }

        playAnimation(binding.question,0,list.get(position).getQuestion());

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timer != null){
                    timer.cancel();
                }
                timer.start();


                binding.btnNext.setEnabled(false);
                binding.btnNext.setAlpha((float)0.3);
                enableOption(true);

                position++;
                if (position==list.size()){

                    Intent intent= new Intent(QuestionsActivity.this, ScoreActivity.class);
                    intent.putExtra("score",score);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    finish();
                    return;
                }

                count= 0;

                playAnimation(binding.question,0,list.get(position).getQuestion());


            }
        });

    }

    private void resetTimer() {

        timer= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {


                Dialog dialog = new Dialog(QuestionsActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(QuestionsActivity.this, SetsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();

            }
        };
    }


    private void playAnimation(View view, int value, String data) {

        view.animate().alpha(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                        if (value==0 && count <4){
                            String option ="";
                            if (count==0){

                                option= list.get(position).getOption1();
                            } else if (count==1) {

                                option =list.get(position).getOption2();
                                
                            }

                            else if (count==2) {

                                option =list.get(position).getOption3();

                            }

                            else if (count==3) {

                                option =list.get(position).getOption4();

                            }

                            playAnimation(binding.optionContainer.getChildAt(count),0,option);
                            count++;
                        }

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        if (value==0){

                            try {
                                ((TextView)view).setText(data);
                                binding.totalQuestion.setText(position+1+"/"+list.size());


                            } catch (Exception e){
                                ((Button)view).setText(data);                            }

                            binding.totalQuestion.setText(position+1+"/"+list.size());
                        }

                        view.setTag(data);
                        playAnimation(view,1,data);

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });


    }

    private void enableOption(boolean enable) {

        //option containers

        for (int i=0;i<4;i++){

            binding.optionContainer.getChildAt(i).setEnabled(enable);


            if (enable){

                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.btn_opt);
            }
        }





    }

    private void checkAnswer(Button selectedOption) {

        if (timer !=null){

            timer.cancel();
        }

        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);


        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){

            score++;
            selectedOption.setBackgroundResource(R.drawable.right_answer);

        }
        else {
            selectedOption.setBackgroundResource(R.drawable.wrong_answer);

            Button correctOption =(Button) binding.optionContainer.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundResource(R.drawable.right_answer);


        }


    }



    private void setSix() {
        list.add(new QuestionModel(" Which country was the first to legalize same-sex marriage?",
                "(A) The Netherlands",
                "(B) Belgium",
                "(C) Spain",
                "(D) Canada",
                "(A) The Netherlands"));

        list.add(new QuestionModel("Who was the first woman to be elected to the United States Congress? ",
                "(A) Jeannette Rankin",
                "(B) Shirley Chisholm",
                "(C) Hillary Clinton",
                "(D) Nancy Pelosi",
                "(A) Jeannette Rankin"));
        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?",
                "(A) Ancient Egypt",
                "(B) Ancient Rome",
                "(C) Ancient China",
                "(D) Ancient Greece",
                "(C) Ancient China"));

        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
        list.add(new QuestionModel("Who was the first president of the United States? ",
                "(A) George Washington",
                "(B) John Adams",
                "(C) Thomas Jefferson",
                "(D) James Madison",
                " (A) George Washington"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
    }


    private void setFive() {
        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?","(A) Ancient Egypt","(B) Ancient Rome",
                "(C) Ancient China","(D) Ancient Greece","(C) Ancient China"));

        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
        list.add(new QuestionModel("Who was the first president of the United States? ",
                "(A) George Washington",
                "(B) John Adams",
                "(C) Thomas Jefferson",
                "(D) James Madison",
                "(A) George Washington"));
        list.add(new QuestionModel("Who was the leader of the Soviet Union during the Cold War? ",
                "(A) Joseph Stalin",
                "(B) Nikita Khrushchev",
                "(C) Leonid Brezhnev",
                "(D) Yuri Andropov",
                "(A) Joseph Stalin"));
        list.add(new QuestionModel("Which country was the first to put a man in space? ",
                "(A) The United States",
                "(B) The Soviet Union",
                "(C) China",
                "(D) France",
                "(B) The Soviet Union"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
    }

    private void setFour() {
        list.add(new QuestionModel(" Which country was the first to legalize same-sex marriage?",
                "(A) The Netherlands",
                "(B) Belgium",
                "(C) Spain",
                "(D) Canada",
                "(A) The Netherlands"));

        list.add(new QuestionModel("Who was the first woman to be elected to the United States Congress? ",
                "(A) Jeannette Rankin",
                "(B) Shirley Chisholm",
                "(C) Hillary Clinton",
                "(D) Nancy Pelosi",
                "(A) Jeannette Rankin"));
        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?",
                "(A) Ancient Egypt",
                "(B) Ancient Rome",
                "(C) Ancient China",
                "(D) Ancient Greece",
                "(C) Ancient China"));

        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
        list.add(new QuestionModel("Who was the first president of the United States? ",
                "(A) George Washington",
                "(B) John Adams",
                "(C) Thomas Jefferson",
                "(D) James Madison",
                " (A) George Washington"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
    }
    private void setThree() {

        list.add(new QuestionModel("Who was the leader of the French Revolution? ",
                "(A) King Louis XVI",
                "(B) Marie Antoinette",
                "(C) Maximilien Robespierre",
                "(D) Napoleon Bonaparte",
                "(C) Maximilien Robespierre"));
        list.add(new QuestionModel(" Which country was the first to legalize same-sex marriage?",
                "(A) The Netherlands",
                "(B) Belgium",
                "(C) Spain",
                "(D) Canada",
                "(A) The Netherlands"));

        list.add(new QuestionModel("Who was the first woman to be elected to the United States Congress? ",
                "(A) Jeannette Rankin",
                "(B) Shirley Chisholm",
                "(C) Hillary Clinton",
                "(D) Nancy Pelosi",
                "(A) Jeannette Rankin"));
        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?",
                "(A) Ancient Egypt",
                "(B) Ancient Rome",
                "(C) Ancient China",
                "(D) Ancient Greece",
                "(C) Ancient China"));
        list.add(new QuestionModel(" Which country was the first to legalize same-sex marriage?",
                "(A) The Netherlands",
                "(B) Belgium",
                "(C) Spain",
                "(D) Canada",
                "(A) The Netherlands"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));

    }

    private void setTwo() {

        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?","(A) Ancient Egypt","(B) Ancient Rome",
                "(C) Ancient China","(D) Ancient Greece","(C) Ancient China"));

        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
        list.add(new QuestionModel("Who was the first president of the United States? ",
                "(A) George Washington",
                "(B) John Adams",
                "(C) Thomas Jefferson",
                "(D) James Madison",
                "(A) George Washington"));
        list.add(new QuestionModel("Who was the leader of the Soviet Union during the Cold War? ",
                "(A) Joseph Stalin",
                "(B) Nikita Khrushchev",
                "(C) Leonid Brezhnev",
                "(D) Yuri Andropov",
                "(A) Joseph Stalin"));
        list.add(new QuestionModel("Which country was the first to put a man in space? ",
                "(A) The United States",
                "(B) The Soviet Union",
                "(C) China",
                "(D) France",
                "(B) The Soviet Union"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
    }


    private void setOne() {

        list.add(new QuestionModel(" Which country was the first to legalize same-sex marriage?",
                "(A) The Netherlands",
                "(B) Belgium",
                "(C) Spain",
                "(D) Canada",
                "(A) The Netherlands"));

        list.add(new QuestionModel("Who was the first woman to be elected to the United States Congress? ",
                "(A) Jeannette Rankin",
                "(B) Shirley Chisholm",
                "(C) Hillary Clinton",
                "(D) Nancy Pelosi",
                "(A) Jeannette Rankin"));
        list.add(new QuestionModel(" Which ancient civilization built the Great Wall of China?",
                "(A) Ancient Egypt",
                "(B) Ancient Rome",
                "(C) Ancient China",
                "(D) Ancient Greece",
                "(C) Ancient China"));

        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));
        list.add(new QuestionModel("Who was the leader of the Soviet Union during the Cold War? ",
                "(A) Joseph Stalin",
                "(B) Nikita Khrushchev",
                "(C) Leonid Brezhnev",
                "(D) Yuri Andropov",
                "(A) Joseph Stalin"));
        list.add(new QuestionModel("Which event marked the beginning of World War II? ",
                "(A) The invasion of Poland by Germany",
                "(B) The attack on Pearl Harbor by Japan",
                "(C) The Battle of Britain",
                "(D) The D-Day landings",
                "(A) The invasion of Poland by Germany"));



    }
}