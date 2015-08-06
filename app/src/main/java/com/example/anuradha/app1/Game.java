package com.example.anuradha.app1;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.GameAI.Board;
import com.example.anuradha.app1.GameModes.AbstractGameMode;
import com.example.anuradha.app1.GameModes.HumanComputer;
import com.example.anuradha.app1.GameModes.HumanHuman;
import com.example.anuradha.app1.GameModes.HumanHumanNetwork;
import com.example.anuradha.app1.Network.BluetoothNetworkService;


public class Game extends Activity implements View.OnClickListener {
    //initial game board
    private Board board;
    //the abstract game event
    private AbstractGameMode new_game;

    private ImageButton btn00;
    private ImageButton btn01;
    private ImageButton btn02;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn20;
    private ImageButton btn21;
    private ImageButton btn22;

    private ImageView btnNewGame;
    private ImageView btnBack;
    private UImanager uimanager;

    public static int wins;
    public static int losses;
    public static int type;
    public static String m_Text;

    private BluetoothDevice selected;
    private BluetoothNetworkService networkService;
    private boolean netLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamewindow);


        losses = 0;
        wins = 0;
        m_Text = "";
        netLock = false;
        /*
        Generating the game board at the beginig to start a new game
         */
        board = new Board();

        btn00 = (ImageButton) findViewById(R.id.bt00);
        btn01 = (ImageButton) findViewById(R.id.bt01);
        btn02 = (ImageButton) findViewById(R.id.bt02);
        btn10 = (ImageButton) findViewById(R.id.bt10);
        btn11 = (ImageButton) findViewById(R.id.bt11);
        btn12 = (ImageButton) findViewById(R.id.bt12);
        btn20 = (ImageButton) findViewById(R.id.bt20);
        btn21 = (ImageButton) findViewById(R.id.bt21);
        btn22 = (ImageButton) findViewById(R.id.bt22);

        btn00.setOnClickListener(this);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);

        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);

        btnNewGame = (ImageView) findViewById(R.id.newGame);
        btnBack = (ImageView) findViewById(R.id.back);

        btnNewGame.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        uimanager = new UImanager(this);

        /*
        * Reading the bundled data and make the game instance
        * */
        Intent i = getIntent();
        type = i.getExtras().getInt("type");
        switch (type) {
            case 1:
                boolean firstPlay = i.getExtras().getBoolean("firstPlay");
                char cpu = i.getExtras().getChar("cpu");
                new_game = new HumanComputer(firstPlay, cpu, 1, uimanager, board, this);
                break;
            case 2:
                new_game = new HumanHuman(uimanager, board, this);
                break;
            case 3:
                new_game = new HumanHumanNetwork(uimanager, board, this);
                selected = (BluetoothDevice) getIntent().getExtras().get("btDevice");
                networkService = new BluetoothNetworkService(getApplicationContext(), mHandler);
                networkService.start();
                networkService.connect(selected, true);
                break;
            case 4:
                new_game = new HumanHumanNetwork(uimanager, board, this);
                networkService = new BluetoothNetworkService(getApplicationContext(), mHandler);
                networkService.start();
                break;
            default:
                boolean firstPlay1 = i.getExtras().getBoolean("firstPlay");
                char cpu1 = i.getExtras().getChar("cpu");
                new_game = new HumanComputer(firstPlay1, cpu1, 1, uimanager, board, this);
                break;
        }
        new_game.updateAll();
    }


    /*
        * ActionListener for Button clicks
        **/
    @Override
    public void onClick(View v) {
        //ending the activity when user presses back button
        if (v.getId() == R.id.back) {

            this.finish();
            return;
        }
        //starting the game again when user ask for a new game
        else if (v.getId() == R.id.newGame) {
            new_game.startOver();
            return;
        }

        //responses to buttons
        switch (v.getId()) {
            case R.id.bt00:
                new_game.move(0, 0);
                if (type == 3 || type == 4)
                    networkService.write("00".getBytes());
                break;
            case R.id.bt01:
                new_game.move(0, 1);
                if (type == 3 || type == 4)
                    networkService.write("01".getBytes());
                break;
            case R.id.bt02:
                new_game.move(0, 2);
                if (type == 3 || type == 4)
                    networkService.write("02".getBytes());
                break;
            case R.id.bt10:
                new_game.move(1, 0);
                if (type == 3 || type == 4)
                    networkService.write("10".getBytes());
                break;
            case R.id.bt11:
                new_game.move(1, 1);
                if (type == 3 || type == 4)
                    networkService.write("11".getBytes());
                break;
            case R.id.bt12:
                new_game.move(1, 2);
                if (type == 3 || type == 4)
                    networkService.write("12".getBytes());
                break;
            case R.id.bt20:
                new_game.move(2, 0);
                if (type == 3 || type == 4)
                    networkService.write("20".getBytes());
                break;
            case R.id.bt21:
                new_game.move(2, 1);
                if (type == 3 || type == 4)
                    networkService.write("21".getBytes());
                break;
            case R.id.bt22:
                new_game.move(2, 2);
                if (type == 3 || type == 4)
                    networkService.write("22".getBytes());
                break;
        }
    }

    public void setWin() {
        wins++;
    }

    public void setLoss() {
        losses++;
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    //Use the message to play the game
                    System.out.println("Read " + readMessage);
                    new_game.move(Integer.parseInt("" + readMessage.charAt(0)), Integer.parseInt("" + readMessage.charAt(1)));
                    break;

            }
        }
    };

}
