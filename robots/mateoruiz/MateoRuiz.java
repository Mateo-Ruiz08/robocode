package mateoruiz;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;


public class MateoRuiz extends Robot{
    public void run(){

        //Set color
        //Get to known position

        while(true){
            //This repeats until you die
            turnRadarRight(360);
            rando();
        }

    }



    public void onScannedRobot(ScannedRobotEvent e) {
		//Gets the absolute bearing (angle to shoot at) for enemy robot
        double abb = getHeading() + e.getBearing();
        //Moves closer to enemy
        getCloseToEnemy(e.getDistance(), abb);

        
        //Got this formula from Youtube (Professor Spencer), calculates the angle at which to turn the gun itself
        double g = Utils.normalRelativeAngleDegrees(abb - getGunHeading());
        //Moves gun to desired angle
        turnGunRight(g);

        //Fire with high power when enemy is close because a hit is more likely
        //Fire power decrease as distance to enemy decreases
        if(e.getDistance() > 300){
            fire(1);
        } else if(e.getDistance() > 150){
            fire(2);
        } else{
            fire(1);
        }
       

		
	}

    public void getCloseToEnemy(double distance, double ebearing){
        if(distance > 100){
            
            //Again, got this formula from Youtube, takes the enemies bearing and your bearing to find angle at which to turn and go
            double turnAngle = Utils.normalRelativeAngleDegrees(ebearing - getHeading());
            turnRight(turnAngle);
            ahead(75);

        }
        else{
            rando();
        }
        
    }

    public void rando(){
        
        //Selects a random angle a distance to move at
        //Inspired by code off a Youtube video explainging to how use random movement and methods in robocode
        double randomDis = 100 + Math.random() * 200;
        double randomAng = Math.random() * 360;
        ahead(randomDis);
    }

}