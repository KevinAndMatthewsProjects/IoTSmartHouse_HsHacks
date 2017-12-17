## IoT SmartHouse

Project for HS Hacks III <br>
:round_pushpin: 
Fremont, CA, USA<br>
:date: 3/25/17-3/26/17

### Inspiration

After observing my senile grandma's difficulty with controlling lights and other electronics around the house, we decided to create an application that could utilize new technologies such as the Internet of Things and Machine Learning to assist people with these kinds of tasks.

### What it does

The user has the ability to control a certain electronic in their home, such as a light or a fan, from their mobile device, using the Internet of Things. The electronic would power on or off, depending on its current state. The user has complete wireless control over the electronics in their home. Based on the user's daily patterns, the application utilizes Machine Learning to automatically and remotely control the electronics.

### How it works

The android app has many switches on it, which correspond to the electronics wired to the arduino board. When a switch is toggled, it publishes a messege to the IoT PubNub Server, or PubNub Block. Because of PubNubs infrastructure, this can be done quickly from anywhere in the world. We then have a running java program that has a callback registered to the PubNub block, and an event is fired whenever the Android app publishes. The java program then recodes this message so that the arduino can understand it, then sends it to the arduino over serial. The arduino finally decodes this message and tured on the corresponding lights or motors. The java server also analysis the android inputs using a nerual network, which gets trained to a users routine. When the automatic mode switch is chosen on the app, the java program then takes controll over the lights, and turns them on and off according to the routine.
<br>
<!--<img src="https://github.com/KevinAndMatthewsProjects/IoTSmartHouse_HsHacks/tree/master/img/iotsmarthouse.png" width="420" height="235">-->
<br>

<a href="http://imgur.com/2YIALEO">Demo</a>

### Roles
#### Kevin Palani:
* Arduino Programming
* PubNub Server Programming
* Neural network (Machine Learning)

 #### Matthew Pham:
* Arduino Board Wiring
* Data Analytics for Machine Learning
* Android Application
