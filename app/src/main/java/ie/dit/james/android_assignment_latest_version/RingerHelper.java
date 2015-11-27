package ie.dit.james.android_assignment_latest_version;

//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment



        import android.media.AudioManager;


//The Ringerhelper class does not need to have extend anything
public class RingerHelper {


    private RingerHelper(){}




    public static void performToggle(AudioManager audioManager){
        //-----------Reference the Following code is from the book Android App Development for Dummie
        audioManager.setRingerMode(
                isPhoneSilent(audioManager)
                        ? AudioManager.RINGER_MODE_NORMAL :AudioManager.RINGER_MODE_SILENT);


    }

    public static boolean isPhoneSilent(AudioManager audioManager){

        return audioManager.getRingerMode()
                == AudioManager.RINGER_MODE_SILENT;
    }
    //----------Reference Complete
}
