//Android Assignment
//James Prendergast
//C13446122
//27_11_2015

//this is the name of the Package of the Assignment
package ie.dit.james.android_assignment_latest_version;


import android.media.AudioManager;


//The Ringerhelper class does not need to have extend anything
public class RingerHelper {

    ///this is private to prevent users from creating RingerHelper object
    private RingerHelper(){}

    public static void performToggle(AudioManager audioManager){
        //-----------Reference the Following code is from the book Android App Development for Dummies

       //this toggles the phone silent mode service
        audioManager.setRingerMode(isPhoneSilent(audioManager)
                        ? AudioManager.RINGER_MODE_NORMAL :AudioManager.RINGER_MODE_SILENT);

    }

    //this returns whether the phone is currently in silent mode
    public static boolean isPhoneSilent(AudioManager audioManager){
        return audioManager.getRingerMode()
                == AudioManager.RINGER_MODE_SILENT;
    }
    //----------Reference Complete
}
