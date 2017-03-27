
#include <Servo.h>

const int buttonPin = 2;     // the number of the pushbutton pin
const int ledPin1 =  12;      // the number of the LED pin
const int ledPin2 =  8; 
boolean forward = true;
Servo servo;
int pos = 0;
// variables will change:
int buttonState = 0;         // variable for reading the pushbutton status

void setup() {
  // initialize the LED pin as an output:
  Serial.begin(9600);
  pinMode(ledPin1, OUTPUT);
  pinMode(ledPin2, OUTPUT);
  // initialize the pushbutton pin as an input:
  servo.attach(9);
  pinMode(buttonPin, INPUT);
  
}

void loop() {
  // read the state of the pushbutton value:
  buttonState = digitalRead(buttonPin);

  // check if the pushbutton is pressed.
  // if it is, the buttonState is HIGH
    int input;
    if(Serial.available()){
     input = Serial.read();
      if(input > 1) {
       // Serial.write("received " + ((char)(input)=='b'));
      }
    }

    if((char)(input) == 'b') {
      digitalWrite(ledPin1, HIGH);
    } else {
      digitalWrite(ledPin1, LOW);
    }

    if((char)(input) == 'd') {
      digitalWrite(ledPin2, HIGH);
    } else {
      digitalWrite(ledPin2, LOW);
    }

    if((char)(input) == 'f') {
      if(forward) {
        servo.write(pos++);
        if(pos > 170*2) {
          forward = false;
        }
      } else {
        servo.write(pos--);
        if(pos < 10/2) {
          forward = true;
        }
      }
    }
    

  
  
}
