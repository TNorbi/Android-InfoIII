package com.example.quizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


const val TAG_MAIN: String = "MainActivity"


class MainActivity : AppCompatActivity() {

    //private lateinit var userName: EditText
    //private lateinit var startButton : Button
    //private lateinit var  contactButton : Button

    //itt az onCreate fog eloszor meghivodni es majd csak az onStart

    override fun onStart() {
        super.onStart()
        Log.i(TAG_MAIN,"onStart() called")
    }

    //onCreate = ez 1 activity,ez 1 lifecycle (eletcilkus metodus) ,ez kotelezo modon itt kell legyen ahhoz hogy lassunk is valamit
    //ezeknek a lifecycle-nek ertelme egyreszt, hogy bizonyos adatokat le lehessen menteni abban az esetben ha valami hiba lep fel

    override fun onResume() {
        super.onResume()
        Log.i(TAG_MAIN,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG_MAIN,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG_MAIN,"onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG_MAIN,"onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG_MAIN,"onDestroy() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_MAIN,"onCreate() called")
        setContentView(R.layout.activity_main)
        //initializeView()
        //registerListeners()
    }

    /*private fun registerListeners() {
        startButton.setOnClickListener{
            //itt mondom meg ,hogy mit tortenjen amikor a user megnyomja a gombot!
            Toast.makeText(applicationContext,"Start button pressed",Toast.LENGTH_LONG).show() //ez csak letrehozza a toast-ot a megadott uzenettel majd megjeleniti
            Log.i(TAG_MAIN,userName.text.toString()) //logcat-be fog bekerulni a userName erteke az informacio panelnel

            //ezzel meg fogom hivni a SecondActivity activityt!
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, userName.text.toString()) //ezzel az utasitassal el fogom kuldeni a SecondActivitynek a userName EditText erteket(maga a jatekos nevet!)
            }

            startActivity(intent) //ezzel meghivom/elinditom a masodik activityt es az uzenetet(amit a putExtran adtam meg mint masodik parameterkent) meg fogja kapni!
        }

        val getContactList =registerForActivityResult(ActivityResultContracts.PickContact(),
            ActivityResultCallback {
                //le kell kezelni a Uri-t!

                val lista = listOf(ContactsContract.Contacts.DISPLAY_NAME).toTypedArray()

                val cursor = contentResolver.query(it,lista,null,null,null)
                if(cursor != null) {
                    if(cursor.moveToFirst()){
                        this.userName.setText(cursor.getString(0))
                    }
                    cursor.close()
                }
            })

        contactButton.setOnClickListener{
            //itt kene megnyitmi a kontakt listamat
            getContactList.launch(null)
        }
    }

    private fun initializeView() { //R.id.userName -> ez a viewban levo userName ID (xml fajlbol veszi ki)!
        userName = findViewById(R.id.userName) //ezzel lekerem a viewban levo Username erteket (plaintext erteket)
        startButton = findViewById(R.id.StartButton)
        contactButton = findViewById(R.id.contactButton)
    }*/
}
